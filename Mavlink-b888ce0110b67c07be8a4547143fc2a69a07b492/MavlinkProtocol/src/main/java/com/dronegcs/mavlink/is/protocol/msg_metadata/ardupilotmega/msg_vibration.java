// MESSAGE VICON_POSITION_ESTIMATE PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
 *
 */
public class msg_vibration extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_VIBRATION = 241;
	public static final int MAVLINK_MSG_LENGTH = 32;
	private static final long serialVersionUID = MAVLINK_MSG_ID_VIBRATION;


	public long time_usec;
	public float vibration_x;
	public float longvibration_y;
	public float longvibration_z;
	public int longclipping_0;
	public int longclipping_1;
	public int longclipping_2;

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(time_usec);
		packet.payload.putFloat(vibration_x);
		packet.payload.putFloat(longvibration_y);
		packet.payload.putFloat(longvibration_z);
		packet.payload.putInt(longclipping_0);
		packet.payload.putInt(longclipping_1);
		packet.payload.putInt(longclipping_2);
		return packet;
	}

	/**
	 * Decode a vicon_position_estimate message into this class fields
	 *
	 * @param payload The message to decode
	 */
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();
		time_usec = payload.getLong();
		vibration_x = payload.getFloat();
		longvibration_y = payload.getFloat();
		longvibration_z = payload.getFloat();
		longclipping_0 = payload.getInt();
		longclipping_1 = payload.getInt();
		longclipping_2 = payload.getInt();
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_vibration(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_VIBRATION;
	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a com.dronegcs.mavlink.is.mavlink packet
	 *
	 */
	public msg_vibration(MAVLinkPacket mavLinkPacket){
		this(mavLinkPacket.sysid);
		unpack(mavLinkPacket.payload);
		//Log.d("MAVLink", "VICON_POSITION_ESTIMATE");
		//Log.d("MAVLINK_MSG_ID_VICON_POSITION_ESTIMATE", toString());
	}


	@Override
	public String toString() {
		return "msg_vibration{" +
				"time_usec=" + time_usec +
				", vibration_x=" + vibration_x +
				", longvibration_y=" + longvibration_y +
				", longvibration_z=" + longvibration_z +
				", longclipping_0=" + longclipping_0 +
				", longclipping_1=" + longclipping_1 +
				", longclipping_2=" + longclipping_2 +
				'}';
	}
}
