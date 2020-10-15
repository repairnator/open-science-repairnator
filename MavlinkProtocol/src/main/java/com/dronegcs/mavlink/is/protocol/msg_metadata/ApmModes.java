package com.dronegcs.mavlink.is.protocol.msg_metadata;

import java.util.ArrayList;
import java.util.List;

import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;

public enum ApmModes {
	FIXED_WING_MANUAL (0,"Manual", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_CIRCLE (1,"MavlinkCircle", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_STABILIZE (2,"Stabilize", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_TRAINING (3,"Training", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_FLY_BY_WIRE_A (5,"FBW A", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_FLY_BY_WIRE_B (6,"FBW B", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_AUTO (10,"Auto", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_RTL (11,"RTL", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_LOITER (12,"Loiter", false, MAV_TYPE.MAV_TYPE_FIXED_WING),
	FIXED_WING_GUIDED (15,"Guided", false, MAV_TYPE.MAV_TYPE_FIXED_WING),

	ROTOR_STABILIZE(0, "Stabilize", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_ACRO(1,"Acro", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_ALT_HOLD(2, "Alt Hold", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_AUTO(3, "Auto", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_GUIDED(4, "Guided", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_LOITER(5, "Loiter", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_RTL(6, "RTL", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_CIRCLE(7, "Circle", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_LAND(9, "Land", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_TOY(11, "Drift", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_SPORT(13, "Sport", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_FLIP(14, "Flip", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_AUTOTUNE(15, "Autotune", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_POSHOLD(16, "PosHold", true, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_BRAKE(17, "Brake", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_THROW(18, "Throw", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_AVOID_ADSB(19, "Avoid_ADSB", false, MAV_TYPE.MAV_TYPE_QUADROTOR),
	ROTOR_GUIDED_NOGPS(20, "Guided_NoGPS", false, MAV_TYPE.MAV_TYPE_QUADROTOR),

	UNKNOWN(-1, "Unknown", false, MAV_TYPE.MAV_TYPE_GENERIC);

	private final int number;
    private final String name;
	private final boolean superSimpleOrSimpleModeAvailable;
	private final MAV_TYPE type;

	ApmModes(int number,String name,
			 boolean superSimpleOrSimpleModeAvailable, MAV_TYPE type){
		this.number = number;
		this.name = name;
		this.superSimpleOrSimpleModeAvailable = superSimpleOrSimpleModeAvailable;
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public boolean isSuperSimpleOrSimpleModeAvailable() {
		return superSimpleOrSimpleModeAvailable;
	}

	public MAV_TYPE getType() {
		return type;
	}

	public static ApmModes getMode(int i, MAV_TYPE type) {
        if (isCopter(type)) {
            type = MAV_TYPE.MAV_TYPE_QUADROTOR;
        }

		for (ApmModes mode : ApmModes.values()) {
			if (i == mode.getNumber() & type == mode.getType()) {
				return mode;
			}
		}
		return UNKNOWN;
	}

	public static ApmModes getMode(String str, MAV_TYPE type) {
        if (isCopter(type)) {
            type = MAV_TYPE.MAV_TYPE_QUADROTOR;
        }

		for (ApmModes mode : ApmModes.values()) {
			if (str.equals(mode.getName()) & type == mode.getType()) {
				return mode;
			}
		}
		return UNKNOWN;
	}

	public static List<ApmModes> getModeList(MAV_TYPE type) {
		List<ApmModes> modeList = new ArrayList<ApmModes>();

		if (isCopter(type)) {
			type = MAV_TYPE.MAV_TYPE_QUADROTOR;
		}

		for (ApmModes mode : ApmModes.values()) {
			if (mode.getType() == type) {
				modeList.add(mode);
			}
		}
		return modeList;
	}

	public static boolean isValid(ApmModes mode) {
		return mode!=ApmModes.UNKNOWN;
	}


	public static boolean isCopter(MAV_TYPE type){
		switch (type) {
		case MAV_TYPE_TRICOPTER:
		case MAV_TYPE_QUADROTOR:
		case MAV_TYPE_HEXAROTOR:
		case MAV_TYPE_OCTOROTOR:
		case MAV_TYPE_HELICOPTER:
			return true;

		default:
			return false;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}

}
