package com.dronegcs.mavlink.core.firmware;

public enum FirmwareType {
	ARDU_PLANE("ArduPlane"), ARDU_COPTER("ArduCopter");

	private final String type;

	FirmwareType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}

	public static FirmwareType firmwareFromString(String str) {
		if (str.equalsIgnoreCase(ARDU_PLANE.type)) {
			return ARDU_PLANE;
		} else {
			return ARDU_COPTER;
		}
	}
}
