package com.dronegcs.mavlink.is.drone.calibration;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.Preferences;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkCalibration;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkStreamRates;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class CalibrateCompass extends DroneVariable implements DroneInterfaces.OnDroneListener, Calibration {

    private int expectedAmount;
    private double averageX;
    private double averageY;
    private double averageZ;

    class Measurement {
        private final double x;
        private final double y;
        private final double z;

        public Measurement(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Measurement that = (Measurement) o;
            return Double.compare(Math.round(that.x * 10)/10, Math.round(x * 10)/10) == 0 &&
                    Double.compare(Math.round(that.y * 10)/10, Math.round(y * 10)/10) == 0 &&
                    Double.compare(Math.round(that.z * 10)/10, Math.round(z * 10)/10) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(Math.round(x * 10)/10, Math.round(y * 10)/10, Math.round(z * 10)/10);
        }
    }

    private static final int MEASUREMENT_AMOUNT = 150;

    private boolean calibrating;
    private Preferences.Rates originalRates;
    private Set<Measurement> rawImuSet;
    private int rawImuAmount;

    static int called;
    public void init() {
        if (called++ > 1)
            throw new RuntimeException("Not a Singleton");
        drone.addDroneListener(this);
    }

    @Override
    public boolean start() {
        return startCompassCalibration(MEASUREMENT_AMOUNT);
    }

    public boolean startCompassCalibration(int measurement) {
        if(drone.getState().isFlying()) {
            this.calibrating = false;
            return false;
        }

        this.calibrating = true;
        drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_MAGNETOMETER_START);

        Parameter compassLearnStatus = drone.getParameters().getParameter("COMPASS_LEARN");
        compassLearnStatus.setValue(1);
        drone.getParameters().sendParameter(compassLearnStatus);

        this.expectedAmount = measurement;
        this.rawImuSet = new HashSet<>();
        this.rawImuAmount = 0;
        this.originalRates = drone.getPreferences().getRates();
        MavLinkStreamRates.setupStreamRates(drone, 0,
                0, 0, 0, 0, 0, 50, 0);
        MavLinkCalibration.sendStartMagnometerCalibrationMessage(drone);
        return true;
    }

    @Override
    public boolean stop() {
        MavLinkStreamRates.setupStreamRates(drone, originalRates.extendedStatus,
                originalRates.extra1, originalRates.extra2, originalRates.extra3, originalRates.position, originalRates.rcChannels,
                originalRates.rawSensors, originalRates.rawController);
        this.calibrating = false;

        Parameter compassLearnStatus = drone.getParameters().getParameter("COMPASS_LEARN");
        compassLearnStatus.setValue(0);
        drone.getParameters().sendParameter(compassLearnStatus);

        Parameter paramX = drone.getParameters().getParameter("COMPASS_OFS_X");
        paramX.setValue(averageX);
        drone.getParameters().sendParameter(paramX);

        Parameter paramY = drone.getParameters().getParameter("COMPASS_OFS_Y");
        paramY .setValue(averageY);
        drone.getParameters().sendParameter(paramY);

        Parameter paramZ = drone.getParameters().getParameter("COMPASS_OFS_Z");
        paramZ.setValue(averageZ);
        drone.getParameters().sendParameter(paramZ);

        drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_MAGNETOMETER_FINISH);
        drone.getMessegeQueue().push("New Compass Offsets x:" + averageX + " y:" + averageY + " z:" + averageZ);

        return true;
    }

    @Override
    public void onDroneEvent(DroneInterfaces.DroneEventsType event, Drone drone) {
        switch (event) {
            case MAGNETOMETER:
                if (!this.calibrating)
                    return;

                this.rawImuSet.add(new Measurement(drone.getMagnetometer().getX(),
                        drone.getMagnetometer().getY(),drone.getMagnetometer().getZ()));
                this.averageX = (this.averageX * this.rawImuAmount + drone.getMagnetometer().getX()) / (this.rawImuAmount + 1);
                this.averageY = (this.averageY * this.rawImuAmount + drone.getMagnetometer().getY()) / (this.rawImuAmount + 1);
                this.averageZ = (this.averageZ * this.rawImuAmount + drone.getMagnetometer().getZ()) / (this.rawImuAmount + 1);
                this.rawImuAmount++;
                if (this.rawImuSet.size() == this.expectedAmount) {
                    drone.getMessegeQueue().push("Compass Calibration Finished (" + Math.round(this.rawImuAmount/this.rawImuSet.size()*100.0) + "%)");
                    this.stop();
                }
                else {
                    this.drone.notifyDroneEvent(DroneInterfaces.DroneEventsType.EXT_CALIB_MAGNETOMETER_PROGRESS);
                }
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

    public double[] getCurrentCenter() {
        return new double[]{this.averageX,this.averageY,this.averageY};
    }
}
