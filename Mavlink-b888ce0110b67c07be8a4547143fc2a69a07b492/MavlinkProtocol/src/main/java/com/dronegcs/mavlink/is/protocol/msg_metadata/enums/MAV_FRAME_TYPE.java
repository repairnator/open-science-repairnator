/** 
*/
package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

public enum MAV_FRAME_TYPE {

	MULTICOPTER_FRAME_TYPE_PLUS(0),
	MULTICOPTER_FRAME_TYPE_X(1),
	MULTICOPTER_FRAME_TYPE_V(2),
	MULTICOPTER_FRAME_TYPE_H(3),
	MULTICOPTER_FRAME_TYPE_V_TAIL(4),
	MULTICOPTER_FRAME_TYPE_A_TAIL(5),
	MULTICOPTER_FRAME_TYPE_Y_6_B(10),
	MULTICOPTER_FRAME_TYPE_Y_6_F(11),

	NONE_MULTI_COPTER(999); // Bi-copter, Tri-copter, plane, baloon

	private final int type;

	MAV_FRAME_TYPE(int type){
		this.type = type;
	}

	public int getIndex() {
		return type;
	}

	public static MAV_FRAME_TYPE getFrameType(int id) {
		for (MAV_FRAME_TYPE mav_frame_type : MAV_FRAME_TYPE.values()) {
			if (mav_frame_type.type == id)
				return mav_frame_type;
		}
		return NONE_MULTI_COPTER;
	}
}
