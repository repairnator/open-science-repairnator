// MESSAGE FENCE_FETCH_POINT PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Request a current fence point from MAV
*/
public class msg_fence_fetch_point extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_FENCE_FETCH_POINT = 161;
	public static final int MAVLINK_MSG_LENGTH = 3;
	private static final long serialVersionUID = MAVLINK_MSG_ID_FENCE_FETCH_POINT;
	

 	/**
	* System ID
	*/
	public byte target_system; 
 	/**
	* Component ID
	*/
	public byte target_component; 
 	/**
	* point index (first point is 1, 0 is for return point)
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
     * Decode a fence_fetch_point message into this class fields
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
    public msg_fence_fetch_point(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_FENCE_FETCH_POINT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_fence_fetch_point(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "FENCE_FETCH_POINT");
        //Log.d("MAVLINK_MSG_ID_FENCE_FETCH_POINT", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_FENCE_FETCH_POINT -"+" target_system:"+target_system+" target_component:"+target_component+" idx:"+idx+"";
    }
}
