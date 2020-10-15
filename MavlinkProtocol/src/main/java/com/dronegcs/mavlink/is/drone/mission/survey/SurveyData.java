package com.dronegcs.mavlink.is.drone.mission.survey;

import java.util.Locale;

public class SurveyData {
	private double altitude = 50.0;
	private Double angle = 0.0;
	private Double overlap = 50.0;
	private Double sidelap = 60.0;
	private CameraInfo camera = new CameraInfo();
	
	public SurveyData() {
		
	}

	public SurveyData(SurveyData survey) {
		this.altitude = survey.altitude;
		this.angle = survey.angle;
		this.overlap = survey.overlap;
		this.sidelap = survey.sidelap;
		this.camera = new CameraInfo(survey.camera);
	}

	public void update(double angle, double altitude, double overlap, double sidelap) {
		this.angle = angle;
		this.altitude = altitude;
		this.overlap = overlap;
		this.sidelap = sidelap;
	}

	public double getLateralFootPrint() {
		return altitude * camera.getSensorLateralSize() / camera.focalLength;

	}

	public double getLongitudinalFootPrint() {
		return altitude * camera.getSensorLongitudinalSize() / camera.focalLength;
	}

	public double getGroundResolution() {
		return ((altitude
						* camera.getSensorLateralSize()
						/ camera.focalLength
						* (altitude * camera.getSensorLongitudinalSize() / camera.focalLength) / (camera.sensorResolution * 1000))) / 10000;
	}

	public double getLongitudinalPictureDistance() {
		return getLongitudinalFootPrint() * (1 - overlap * .01);
	}

	public double getLateralPictureDistance() {
		return getLateralFootPrint() * (1 - sidelap * .01);
	}

	public void setCameraInfo(CameraInfo info) {
		this.camera = info;
		tryToLoadOverlapFromCamera();
	}

	private void tryToLoadOverlapFromCamera() {
		if (camera.overlap != null) {
			this.overlap = camera.overlap;
		}
		if (camera.sidelap != null) {
			this.sidelap = camera.sidelap;
		}
	}

	public double getAltitude() {
		return altitude;
	}

	public Double getAngle() {
		return angle;
	}

	public double getSidelap() {
		return sidelap;
	}

	public double getOverlap() {
		return overlap;
	}

	public String getCameraName() {
		return camera.name;
	}

	@Override
	public String toString() {
		return String.format(Locale.US, "Altitude: %f Angle %f Overlap: %f Sidelap: %f", altitude,
				angle, overlap, sidelap);
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

}