
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

import java.util.Arrays;

/**
 * The system time is the time of the master clock, typically the computer clock of the main onboard computer.
 */
public class msg_protocol_version extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_PROTOCOL_VERSION = 300;
	public static final int MAVLINK_MSG_LENGTH = 22;
	private static final long serialVersionUID = MAVLINK_MSG_ID_PROTOCOL_VERSION;

	/**
	 * Currently active MAVLink version number * 100: v1.0 is 100, v2.0 is 200, etc.
	 */
	public short version;

	/**
	 * Minimum MAVLink version supported
	 */
	public short min_version;

	/**
	 * Maximum MAVLink version supported (set to the same value as version by default)
	 */
	public short max_version;


	/**
	 * The first 8 bytes (not characters printed in hex!) of the git hash.
	 */
	public byte spec_version_hash[] = new byte[8];

	/**
	 * The first 8 bytes (not characters printed in hex!) of the git hash.
	 */
	public byte library_version_hash[] = new byte[8];

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(version);
		packet.payload.putShort(min_version);
		packet.payload.putShort(max_version);
		for (int i = 0; i < spec_version_hash.length; i++) {
			packet.payload.putByte(library_version_hash[i]);
		}
		for (int i = 0; i < library_version_hash.length; i++) {
			packet.payload.putByte(library_version_hash[i]);
		}
		return packet;
	}

	/**
	 * Decode a system_time message into this class fields
	 *
	 * @param payload The message to decode
	 */
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();
		version = payload.getShort();
		min_version = payload.getShort();
		max_version = payload.getShort();
		for (int i = 0; i < spec_version_hash.length; i++) {
			spec_version_hash[i] = payload.getByte();
		}
		for (int i = 0; i < library_version_hash.length; i++) {
			library_version_hash[i] = payload.getByte();
		}
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_protocol_version(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_PROTOCOL_VERSION;
	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a com.dronegcs.mavlink.is.mavlink packet
	 *
	 */
	public msg_protocol_version(MAVLinkPacket mavLinkPacket){
		this(mavLinkPacket.sysid);
		unpack(mavLinkPacket.payload);
		//Log.d("MAVLink", "SYSTEM_TIME");
		//Log.d("MAVLINK_MSG_ID_SYSTEM_TIME", toString());
	}


	/**
	 * Returns a string with the MSG name and data
	 */
	@Override
	public String toString() {
		return "msg_protocol_version{" +
				"version=" + version +
				", min_version=" + min_version +
				", max_version=" + max_version +
				", spec_version_hash=" + Arrays.toString(spec_version_hash) +
				", library_version_hash=" + Arrays.toString(library_version_hash) +
				'}';
	}
}
