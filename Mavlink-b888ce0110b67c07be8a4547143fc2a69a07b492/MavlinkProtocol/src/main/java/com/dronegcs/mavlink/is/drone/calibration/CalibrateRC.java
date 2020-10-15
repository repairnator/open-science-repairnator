package com.dronegcs.mavlink.is.drone.calibration;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.Preferences;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkCalibration;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkStreamRates;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class CalibrateRC extends DroneVariable implements DroneInterfaces.OnDroneListener, Calibration {

    private static final int MAX_CHANNELS = 16;

    private boolean calibrating;
    private Preferences.Rates originalRates;

    private int[] rcmin;
    private int[] rcmax;
    private int[] rctrim;

    static int called;
    public void init() {
        if (called++ > 1)
            throw new RuntimeException("Not a Singleton");
        drone.addDroneListener(this);
    }

    @Override
    public boolean start() {
        if(drone.getState().isFlying()) {
            this.calibrating = false;
            return false;
        }

        this.calibrating = true;
        drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_RC_START);

        rcmin = new int[MAX_CHANNELS];
        rcmax = new int[MAX_CHANNELS];
        rctrim = new int[MAX_CHANNELS];

        this.originalRates = drone.getPreferences().getRates();
        MavLinkStreamRates.setupStreamRates(drone, 0,
                0, 0, 0, 0, 10, 0, 0);
        return true;
    }

    @Override
    public boolean stop() {
        MavLinkStreamRates.setupStreamRates(drone, originalRates.extendedStatus,
                originalRates.extra1, originalRates.extra2, originalRates.extra3, originalRates.position, originalRates.rcChannels,
                originalRates.rawSensors, originalRates.rawController);
        this.calibrating = false;

        for (int i = 0 ; i < MAX_CHANNELS ; i++) {
            rctrim[i] = Math.min(Math.max(drone.getRC().in[i], rcmin[i]), rcmax[i]);

            Parameter min = drone.getParameters().getParameter("RC" + (i+1) + "_MIN");
            min.setValue(rcmin[i]);
            drone.getParameters().sendParameter(min);

            Parameter max = drone.getParameters().getParameter("RC" + (i+1) + "_MAX");
            max.setValue(rcmax[i]);
            drone.getParameters().sendParameter(max);

            Parameter trim = drone.getParameters().getParameter("RC" + (i+1) + "_TRIM");
            trim.setValue(rctrim[i]);
            drone.getParameters().sendParameter(trim);
        }

        drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_RC_FINISH);
        return true;
    }

    @Override
    public void onDroneEvent(DroneInterfaces.DroneEventsType event, Drone drone) {
        switch (event) {
            case RC_IN:
                if (!this.calibrating)
                    return;

                for (int i = 0 ; i < MAX_CHANNELS ; i++) {
                    rcmin[i] = Math.min(rcmin[i], drone.getRC().in[i]);
                    rcmax[i] = Math.max(rcmax[i], drone.getRC().in[i]);
                }
                drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_RC_PROGRESS);

                break;
            default:
                break;
        }
    }

    public void setCalibrating(boolean flag) {
        this.calibrating = flag;
    }

    @Override
    public boolean isCalibrating() {
        return this.calibrating;
    }

    public int[] getCurrentMin() {
        return rcmin;
    }

    public int[] getCurrentMax() {
        return rcmax;
    }

}
