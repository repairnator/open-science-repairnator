package com.dronegcs.mavlink.is.drone.mission.survey;

public class CameraInfo {
	public String name = "Canon SX260";
	public Double sensorWidth = 6.12;
	public Double sensorHeight = 4.22;
	public Double sensorResolution = 12.1;
	public Double focalLength = 5.0;
	public Double overlap = 50.0;
	public Double sidelap = 60.0;
	public boolean isInLandscapeOrientation = true;
	
	public CameraInfo() {
	}

	public CameraInfo(CameraInfo camera) {
		this.name = camera.name;
		this.sensorWidth = camera.sensorWidth;
		this.sensorHeight = camera.sensorHeight;
		this.sensorResolution = camera.sensorResolution;
		this.focalLength = camera.focalLength;
		this.overlap = camera.overlap;
		this.sidelap = camera.sidelap;
		this.isInLandscapeOrientation = camera.isInLandscapeOrientation;
	}

	public Double getSensorLateralSize() {
		if (isInLandscapeOrientation) {
			return sensorWidth;
		} else {
			return sensorHeight;
		}
	}

	public Double getSensorLongitudinalSize() {
		if (isInLandscapeOrientation) {
			return sensorHeight;
		} else {
			return sensorWidth;
		}
	}

	@Override
	public String toString() {
		return "Camera:"+name+" ImageWidth:" + sensorWidth + " ImageHeight:" + sensorHeight + " FocalLength:"
				+ focalLength + " Overlap:" + overlap + " Sidelap:" + sidelap
				+ " isInLandscapeOrientation:" + isInLandscapeOrientation;

	}

}