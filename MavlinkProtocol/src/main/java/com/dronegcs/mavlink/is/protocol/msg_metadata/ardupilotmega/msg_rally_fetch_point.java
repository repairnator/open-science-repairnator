// MESSAGE RALLY_FETCH_POINT PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Request a current rally point from MAV. MAV should respond with a RALLY_POINT message. MAV should not respond if the request is invalid.
*/
public class msg_rally_fetch_point extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_RALLY_FETCH_POINT = 176;
	public static final int MAVLINK_MSG_LENGTH = 3;
	private static final long serialVersionUID = MAVLINK_MSG_ID_RALLY_FETCH_POINT;
	

 	/**
	* System ID
	*/
	public byte target_system; 
 	/**
	* Component ID
	*/
	public byte target_component; 
 	/**
	* point index (first point is 0)
	*/
	public byte idx; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putByte(target_system);
		packet.payload.putByte(target_component);
		packet.payload.putByte(idx);
		return packet;		
	}

    /**
     * Decode a rally_fetch_point message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    target_system = payload.getByte();
	    target_component = payload.getByte();
	    idx = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_rally_fetch_point(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_RALLY_FETCH_POINT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_rally_fetch_point(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "RALLY_FETCH_POINT");
        //Log.d("MAVLINK_MSG_ID_RALLY_FETCH_POINT", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_RALLY_FETCH_POINT -"+" target_system:"+target_system+" target_component:"+target_component+" idx:"+idx+"";
    }
}
