package com.dronegcs.mavlink.is.drone.calibration;

public interface Calibration {

    boolean start();

    boolean stop();

    boolean isCalibrating();
}
