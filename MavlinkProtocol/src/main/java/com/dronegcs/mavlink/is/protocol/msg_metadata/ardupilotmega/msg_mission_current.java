// MESSAGE HWSTATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Message that announces the sequence number of the current active droneMission item. The MAV will fly towards this droneMission item.
*/
public class msg_mission_current extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_MISSION_CURRENT = 42;
	public static final int MAVLINK_MSG_LENGTH = 2;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_CURRENT;
	

 	/**
	* Sequence
	*/
	public short seq; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(seq);
		return packet;		
	}

    /**
     * Decode a mission_current message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    seq = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_mission_current(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_MISSION_CURRENT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_mission_current(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "MISSION_CURRENT");
        //Log.d("MAVLINK_MSG_ID_MISSION_CURRENT", toString());
    }
    
  
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_MISSION_CURRENT -"+" seq:"+seq+"";
    }
}
