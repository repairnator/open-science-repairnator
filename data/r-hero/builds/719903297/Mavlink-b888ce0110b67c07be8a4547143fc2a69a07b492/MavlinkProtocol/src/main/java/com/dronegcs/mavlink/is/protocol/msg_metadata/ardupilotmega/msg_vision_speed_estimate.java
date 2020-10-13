// MESSAGE VISION_SPEED_ESTIMATE PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* 
*/
public class msg_vision_speed_estimate extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE = 103;
	public static final int MAVLINK_MSG_LENGTH = 20;
	private static final long serialVersionUID = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;
	

 	/**
	* Timestamp (microseconds, synced to UNIX time or since system boot)
	*/
	public long usec; 
 	/**
	* Global X speed
	*/
	public float x; 
 	/**
	* Global Y speed
	*/
	public float y; 
 	/**
	* Global Z speed
	*/
	public float z; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(usec);
		packet.payload.putFloat(x);
		packet.payload.putFloat(y);
		packet.payload.putFloat(z);
		return packet;		
	}

    /**
     * Decode a vision_speed_estimate message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    usec = payload.getLong();
	    x = payload.getFloat();
	    y = payload.getFloat();
	    z = payload.getFloat();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_vision_speed_estimate(int sysid){ 		super(sysid);
msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_vision_speed_estimate(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "VISION_SPEED_ESTIMATE");
        //Log.d("MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE", toString());
    }
    
        
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE -"+" usec:"+usec+" x:"+x+" y:"+y+" z:"+z+"";
    }
}
