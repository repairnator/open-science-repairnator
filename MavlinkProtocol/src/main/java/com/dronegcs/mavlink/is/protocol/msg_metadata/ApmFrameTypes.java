package com.dronegcs.mavlink.is.protocol.msg_metadata;

import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME_TYPE;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;

import java.util.ArrayList;
import java.util.List;

public enum ApmFrameTypes {

	TRI ("Tri", MAV_FRAME_TYPE.NONE_MULTI_COPTER, MAV_TYPE.MAV_TYPE_TRICOPTER, FirmwareType.ARDU_COPTER),

	QUAD_PLUS ("QuadPlus", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_PLUS, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),
	QUAD_X ("QuadX", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_X, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),
	QUAD_H ("QuadH", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_H, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),
	QUAD_V ("QuadV", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_V, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),
	QUAD_V_TAIL ("Quad-V-Tail", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_V_TAIL, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),
	QUAD_A_TAIL ("Quad-A-Tail", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_A_TAIL, MAV_TYPE.MAV_TYPE_QUADROTOR, FirmwareType.ARDU_COPTER),

	HEX_PLUS ("HexPlus", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_PLUS, MAV_TYPE.MAV_TYPE_HEXAROTOR, FirmwareType.ARDU_COPTER),
	HEX_X ("HexPlus", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_X, MAV_TYPE.MAV_TYPE_HEXAROTOR, FirmwareType.ARDU_COPTER),
//	Y6A ("Y6A", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_Y_6_B, MAV_TYPE.MAV_TYPE_HEXAROTOR, FirmwareType.ARDU_COPTER),
	Y6B ("Y6B", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_Y_6_B, MAV_TYPE.MAV_TYPE_HEXAROTOR, FirmwareType.ARDU_COPTER),
	Y6F ("Y6F", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_Y_6_F, MAV_TYPE.MAV_TYPE_HEXAROTOR, FirmwareType.ARDU_COPTER),

	OCTO_PLUS ("OctaPlus", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_PLUS, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_X ("OctaX", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_X, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_H ("OctaH", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_H, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_V ("OctaV", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_V, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_QUAD_PLUS_8 ("QuadX8", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_PLUS, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_QUAD_X_8 ("QuadX8", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_X, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_QUAD_H_8 ("QuadH8", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_H, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),
	OCTO_QUAD_V_8 ("QuadV8", MAV_FRAME_TYPE.MULTICOPTER_FRAME_TYPE_V, MAV_TYPE.MAV_TYPE_OCTOROTOR, FirmwareType.ARDU_COPTER),

	FIXED_WING ("Plane", MAV_FRAME_TYPE.NONE_MULTI_COPTER, MAV_TYPE.MAV_TYPE_FIXED_WING, FirmwareType.ARDU_PLANE);

    private final String name;
	private final MAV_FRAME_TYPE frameType;
	private final MAV_TYPE droneType;
	private final FirmwareType firmwareType;

	ApmFrameTypes(String name, MAV_FRAME_TYPE frameType, MAV_TYPE droneType, FirmwareType firmwareType){
		this.name = name;
		this.frameType = frameType;
		this.droneType = droneType;
		this.firmwareType = firmwareType;
	}

	public String getName() {
		return name;
	}

	public FirmwareType getFirmwareType() {
		return firmwareType;
	}

	public MAV_TYPE getDroneType() {
		return droneType;
	}

	public MAV_FRAME_TYPE getFrameType() {
		return frameType;
	}

//	public static ApmFrameTypes getFrames(FirmwareType firmwareType, MAV_TYPE droneType) {
//		for (ApmFrameTypes type : ApmFrameTypes.values()) {
//			if (firmwareType == type.getFirmwareType() && droneType == type.getDroneType())
//				return type;
//		}
//
//		return null;
//	}
//
//	public static ApmFrameTypes getFrames(FirmwareType firmwareType) {
//		for (ApmFrameTypes type : ApmFrameTypes.values()) {
//			if (firmwareType == type.getFirmwareType())
//				return type;
//		}
//
//		return null;
//	}

	public static List<ApmFrameTypes> getFrameList(FirmwareType firmwareType, MAV_TYPE droneType) {
		List<ApmFrameTypes> list = new ArrayList<ApmFrameTypes>();

		for (ApmFrameTypes type : ApmFrameTypes.values()) {
			if (firmwareType == type.getFirmwareType() && droneType == type.getDroneType())
				list.add(type);
		}
		return list;
	}

	@Override
	public String toString() {
		return name;
	}

}
