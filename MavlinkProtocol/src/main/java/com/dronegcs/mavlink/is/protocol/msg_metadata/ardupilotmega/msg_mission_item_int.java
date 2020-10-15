// MESSAGE MISSION_ITEM_INT PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
 * Message encoding a droneMission item. This message is emitted to announce
 the presence of a droneMission item and to set a droneMission item on the system. The droneMission item can be either in x, y, z meters (type: LOCAL) or x:lat, y:lon, z:altitude. Local frame is Z-down, right handed (NED), global frame is Z-up, right handed (ENU). See alsohttp://qgroundcontrol.org/com.dronegcs.mavlink.is.mavlink/waypoint_protocol.
 */
public class msg_mission_item_int extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_MISSION_ITEM_INT = 73;
	public static final int MAVLINK_MSG_LENGTH = 37;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_ITEM_INT;


	/**
	 * PARAM1, see MAV_CMD enum
	 */
	public float param1;
	/**
	 * PARAM2, see MAV_CMD enum
	 */
	public float param2;
	/**
	 * PARAM3, see MAV_CMD enum
	 */
	public float param3;
	/**
	 * PARAM4, see MAV_CMD enum
	 */
	public float param4;
	/**
	 * PARAM5 / local: x position in meters * 1e4, global: latitude in degrees * 10^7
	 */
	public int x;
	/**
	 * PARAM6 / y position: local: x position in meters * 1e4, global: longitude in degrees *10^7
	 */
	public int y;
	/**
	 * PARAM7 / z position: global: altitude in meters (relative or absolute, depending on frame.
	 */
	public float z;
	/**
	 * MavlinkWaypoint ID (sequence number). Starts at zero. Increases monotonically for each waypoint, no gaps in the sequence (0,1,2,3,4).
	 */
	public short seq;
	/**
	 * The scheduled action for the MISSION. see MAV_CMD in common.xml MAVLink specs
	 */
	public short command;
	/**
	 * System ID
	 */
	public byte target_system;
	/**
	 * Component ID
	 */
	public byte target_component;
	/**
	 * The coordinate system of the MISSION. see MAV_FRAME in mavlink_types.h
	 */
	public byte frame;
	/**
	 * false:0, true:1
	 */
	public byte current;
	/**
	 * autocontinue to next wp
	 */
	public byte autocontinue;

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(param1);
		packet.payload.putFloat(param2);
		packet.payload.putFloat(param3);
		packet.payload.putFloat(param4);
		packet.payload.putInt(x);
		packet.payload.putInt(y);
		packet.payload.putFloat(z);
		packet.payload.putShort(seq);
		packet.payload.putShort(command);
		packet.payload.putByte(target_system);
		packet.payload.putByte(target_component);
		packet.payload.putByte(frame);
		packet.payload.putByte(current);
		packet.payload.putByte(autocontinue);
		return packet;
	}

	/**
	 * Decode a mission_item_int message into this class fields
	 *
	 * @param payload The message to decode
	 */
	public void unpack(MAVLinkPayload payload) {
		payload.resetIndex();
		param1 = payload.getFloat();
		param2 = payload.getFloat();
		param3 = payload.getFloat();
		param4 = payload.getFloat();
		x = payload.getInt();
		y = payload.getInt();
		z = payload.getFloat();
		seq = payload.getShort();
		command = payload.getShort();
		target_system = payload.getByte();
		target_component = payload.getByte();
		frame = payload.getByte();
		current = payload.getByte();
		autocontinue = payload.getByte();
	}

	/**
	 * Constructor for a new message, just initializes the msgid
	 */
	public msg_mission_item_int(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;
	}

	/**
	 * Constructor for a new message, initializes the message with the payload
	 * from a com.dronegcs.mavlink.is.mavlink packet
	 *
	 */
	public msg_mission_item_int(MAVLinkPacket mavLinkPacket){
		this(mavLinkPacket.sysid);
		unpack(mavLinkPacket.payload);
		//Log.d("MAVLink", "MISSION_ITEM_INT");
		//Log.d("MAVLINK_MSG_ID_MISSION_ITEM_INT", toString());
	}


	/**
	 * Returns a string with the MSG name and data
	 */
	public String toString(){
		return "MAVLINK_MSG_ID_MISSION_ITEM_INT -"+" param1:"+param1+" param2:"+param2+" param3:"+param3+" param4:"+param4+" x:"+x+" y:"+y+" z:"+z+" seq:"+seq+" command:"+command+" target_system:"+target_system+" target_component:"+target_component+" frame:"+frame+" current:"+current+" autocontinue:"+autocontinue+"";
	}
}
