// MESSAGE HWSTATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Set the droneMission item with sequence number seq as current item. This means that the MAV will continue to this droneMission item on the shortest path (not following the droneMission items in-between).
*/
public class msg_mission_set_current extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_MISSION_SET_CURRENT = 41;
	public static final int MAVLINK_MSG_LENGTH = 4;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_SET_CURRENT;
	

 	/**
	* Sequence
	*/
	public short seq; 
 	/**
	* System ID
	*/
	public byte target_system; 
 	/**
	* Component ID
	*/
	public byte target_component; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(seq);
		packet.payload.putByte(target_system);
		packet.payload.putByte(target_component);
		return packet;		
	}

    /**
     * Decode a mission_set_current message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    seq = payload.getShort();
	    target_system = payload.getByte();
	    target_component = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_mission_set_current(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_MISSION_SET_CURRENT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_mission_set_current(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "MISSION_SET_CURRENT");
        //Log.d("MAVLINK_MSG_ID_MISSION_SET_CURRENT", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_MISSION_SET_CURRENT -"+" seq:"+seq+" target_system:"+target_system+" target_component:"+target_component+"";
    }
}
