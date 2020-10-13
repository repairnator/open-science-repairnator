/** 
*/
package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

//Usage is not clear
public enum MAV_FRAME_CLASS {

	FRAME_CLASS_UNDEFINED(0),
	FRAME_CLASS_QUAD(1),
	FRAME_CLASS_HEXA(2),
	FRAME_CLASS_OCTA(3),
	FRAME_CLASS_OCTA_QUAD(4),
	FRAME_CLASS_Y6(5),
	FRAME_CLASS_HELI(6),
	FRAME_CLASS_TRI(7),
	FRAME_CLASS_SINGLE_COPTER(8),
	FRAME_CLASS_COAX_COPTER(9),
	FRAME_CLASS_BI_COPTER(10),
	FRAME_CLASS_HELI_DUAL(11),
	FRAME_CLASS_DODECA_HEXA(12),
	FRAME_CLASS_HELI_QUAD(13);

	private final int type;

	MAV_FRAME_CLASS(int type){
		this.type = type;
	}

}
