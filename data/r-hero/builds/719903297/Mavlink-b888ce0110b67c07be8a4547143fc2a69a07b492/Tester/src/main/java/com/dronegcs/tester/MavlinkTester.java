package com.dronegcs.tester;

import com.dronegcs.mavlink.core.gcs.GCSHeartbeat;
import com.dronegcs.mavlink.is.connection.MavlinkVersions;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.dronegcs.mavlink.is.protocol.msgbuilder.*;
import com.generic_tools.devices.SerialConnection;
import com.generic_tools.environment.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Created by taljmars on 4/5/17.
 */
@Component
public class MavlinkTester implements DroneInterfaces.OnParameterManagerListener, DroneInterfaces.OnDroneListener, DroneInterfaces.OnWaypointManagerListener {

    @Autowired
    private GCSHeartbeat gcsHeartbeat;

    @Autowired
    private Drone drone;

    @Autowired
    private SerialConnection serialConnection;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() throws URISyntaxException {
        drone.addDroneListener(this);
        drone.getParameters().addParameterListener(this);
        drone.getWaypointManager().addWaypointManagerListener(this);
        environment.setBaseRunningDirectoryByClass(".");
    }

    enum Options {
        listports("listports"),
        connect("connect"),
        arm("arm"),
        disarm("disarm"),
        takeoff("takeoff"),
        land("land"),
        rc("rc"),
        rcfree("rcfree"),
        disconnect("disconnect"),
        sync("sync"),
        ver("ver"),
        setver("setver"),
        push("push"),
        fetch("fetch"),
        ping("ping"),
        hb("hb"),
        calib("calib"),
        exit("exit");

        final String stringVal;
        Options(String val) {
            this.stringVal = val;
        }
    }

    public void go() throws IOException {
        System.out.println("Start Mavlink Drone Tester");
        System.out.println("Options are: " + Arrays.asList(Options.values()).toString());
        byte[] buff = new byte[100];
        connect("4 115200");
        Scanner reader = new Scanner(System.in);
        while (reader.hasNextLine()) {
            try {
                String s = reader.nextLine();
                String params = null;
                System.out.println("Received '" + s + "' from user");
                int index = s.indexOf(" ");
                String cmd = s;
                if (index != -1) {
                    cmd = s.substring(0, index);
                    params = s.substring(index + 1);
                }
                switch (Options.valueOf(cmd.toLowerCase())) {
                    case listports:
                        listports();
                        break;
                    case connect:
                        connect(params);
                        break;
                    case arm:
                        arm_disarm(true);
                        break;
                    case disarm:
                        arm_disarm(false);
                        break;
                    case takeoff:
                        takeoff(params);
                        break;
                    case land:
                        land();
                        break;
                    case rc:
                        rc(params);
                        break;
                    case rcfree:
                        rcfree();
                        break;
                    case disconnect:
                        disconnect();
                        break;
                    case sync:
                        sync();
                        break;
                    case push:
                        pushWaypoints();
                        break;
                    case fetch:
                        fetchWaypoints();
                        break;
                    case ping:
                        ping();
                        break;
                    case setver:
                        setProtocol(params);
                        break;
                    case ver:
                        getProtocol();
                        break;
                    case hb:
                        sendHB();
                        break;
                    case calib:
                        calibMagnometer();
                        break;
                    case exit:
                        System.exit(0);
                    default:
                        System.out.println("Unrecognized input: '" + cmd + "' (" + s + ")");
                        System.out.println("Options are: " + Options.values().toString());
                }
            }
            catch (Exception e) {
                System.out.println("Error: '" + e.getMessage() + "'");
                System.out.println("Options are: " + Arrays.asList(Options.values()).toString());
            }
        }
    }

    private void calibMagnometer() {
        drone.getCalibrateCompass().startCompassCalibration();
    }

    private void sendHB() {
        MavLinkHeartbeat.sendMavHeartbeat(drone);
    }

    private void setProtocol(String param) {
        if (param == null || param.isEmpty()) {
            System.out.println("No version was supplied");
            return;
        }
        int v = Integer.parseInt(param);
        if (v == 1) {
            drone.getMavClient().setMavlinkVersion(MavlinkVersions.MAVLINK1);
            System.out.println("Mavlink version set to " + v);
            return;
        }

        if (v == 2) {
            drone.getMavClient().setMavlinkVersion(MavlinkVersions.MAVLINK2);
            System.out.println("Mavlink version set to " + v);
            return;
        }
    }

    private void getProtocol() {
        MavlinkProtocol.getSupportedProtocol(drone);
    }

    private void takeoff(String param) {
        if (param == null || param.isEmpty()) {
            System.out.println("No height was supplied");
            return;
        }
        System.out.println("Takeoff to " + param);
        int h = Integer.parseInt(param);
        if (h > 5) {
            System.out.println("Height greater than 5m is not supported");
            return;
        }

        MavLinkModes.changeFlightMode(drone, ApmModes.ROTOR_ALT_HOLD);
        drone.getState().doTakeoff(h);
    }

    private void land() {
        System.out.println("Land");
        MavLinkModes.changeFlightMode(drone, ApmModes.ROTOR_LAND);
    }

    private void rcfree() {
        System.out.println("Release Keyboard control");
        int[] rcOutput = {0, 0, 0, 0, 0, 0, 0, 0};
        MavLinkRC.sendRcOverrideMsg(drone, rcOutput);
    }

    private void rc(String params) {
        System.out.println("Update flight instructions");
        //{Roll,Pitch,Thr,RC_Yaw,0,0,CAMERA_PITCH,CAMERA_ROLL}
        int[] rcOutput = {0, 0, 0, 0, 0, 0, 0, 0};
        String[] rcs = params.split(" ");
        if (rcs.length != 4) {
            System.out.println("Unexpected amount of RC values");
            return;
        }

        int i = 0;
        for (String rc : rcs)
            rcOutput[i++] = Integer.parseInt(rc);

        MavLinkRC.sendRcOverrideMsg(drone, rcOutput);
    }

    private void listports() {
        Object[] ports = serialConnection.listPorts();
        if (ports.length == 0) {
            System.out.println("No ports found");
            return;
        }

        System.out.println("Available ports:");
        for (int i = 0 ; i < ports.length ; i++) {
            System.out.println(i + ") " + ports[i]);
        }

        System.out.println("Supported Bauds: " + Arrays.asList(serialConnection.baudList()).toString());
        System.out.println("Default Baud: " + serialConnection.getDefaultBaud());
    }

    private void connect(String param) {
        Object[] ports = serialConnection.listPorts();
        if (ports.length == 0) {
            System.out.println("No ports found");
            return;
        }
        int baud = 56700;
//        int baud = 115200;
        int portIndex = 2;

        if (param != null) {
            String[] paramsList = param.split(" ");
            if ((portIndex = Integer.parseInt(paramsList[0])) >= ports.length) {
                System.out.println("Index out of bound!");
                return;
            }
            if (paramsList.length == 2) {
                baud = Integer.parseInt(paramsList[1]);
            }
        }

        Object portName = ports[portIndex];
        System.out.println("Connecting to port: " + portName + ", with baudRate: " + baud);
        serialConnection.setPortName(portName.toString());
        serialConnection.setBaud(baud);

        drone.getMavClient().connect();
    }

    private void disconnect() {
        drone.getMavClient().disconnect();
    }

    private void sync() {
        System.out.println("Start HB");
        gcsHeartbeat.setActive(true);
        System.out.println("Sync Parameters");
        drone.getParameters().refreshParameters();
    }

    private void fetchWaypoints() {
        System.out.println("Fetch Waypoints");
        drone.getWaypointManager().getWaypoints();
    }

    private void pushWaypoints() {
        System.out.println("Push Waypoints");
        if (drone.getDroneMission().makeAndUploadDronie() == -1) {
            System.out.println("Failed to build and upload mission");
        }
    }

    private void ping() {
        System.out.println("Ping Drone");
        MavlinkPing.sendPing(drone);
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void arm_disarm(boolean arm) {
        System.out.println("Arm/Disarm Drone");
        MavLinkArm.sendArmMessage(drone, arm);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Missing args: usage: <logdir> <confdir>");
            System.exit(-1);
        }
        System.setProperty("LOGS.DIR", args[0]);
        System.setProperty("CONF.DIR", args[1]);
        System.out.println("Logs directory set to: " + System.getProperty("LOGS.DIR"));
        System.out.println("Configuration directory set to: " + System.getProperty("CONF.DIR"));
        MavlinkTester mavlinkTester = AppConfig.context.getBean(MavlinkTester.class);
        mavlinkTester.go();
    }

    @Override
    public void onBeginReceivingParameters() {
        System.out.println("Start receiving parameters");
    }

    @Override
    public void onParameterReceived(Parameter parameter, int index, int count) {
        System.out.println("Received " + (index + 1) + "/" + count + " param=" + parameter.getName() + " description=" + parameter.getDescription());
    }

    @Override
    public void onEndReceivingParameters(List<Parameter> parameter) {
        System.out.println("Finish receiving parameters, amount=" + parameter.size());
    }

    @Override
    public void onDroneEvent(DroneInterfaces.DroneEventsType event, Drone drone) {
        switch (event) {
            case TEXT_MESSEGE:
                System.out.println("Received Event " + drone.getMessegeQueue().pop(this));
                break;
            case PROTOCOL_IDENTIFIED:
                System.out.println("Protocol Identified: " + drone.getMavClient().getMavlinkVersion());
                break;
        }
    }

    @Override
    public void onBeginWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent) {
        System.out.println("Start getting waypoints");
    }

    @Override
    public void onWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent, int index, int count) {
        System.out.println("Waypoint moved " + index + "/" + count);
    }

    @Override
    public void onEndWaypointEvent(WaypointManager.WaypointEvent_Type wpEvent) {
        System.out.println("Finish getting waypoints");
    }
}
