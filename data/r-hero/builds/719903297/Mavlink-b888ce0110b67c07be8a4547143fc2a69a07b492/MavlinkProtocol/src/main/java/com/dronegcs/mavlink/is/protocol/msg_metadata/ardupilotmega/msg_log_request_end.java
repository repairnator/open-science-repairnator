// MESSAGE LOG_REQUEST_END PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Stop log transfer and resume normal logging
*/
public class msg_log_request_end extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_LOG_REQUEST_END = 122;
	public static final int MAVLINK_MSG_LENGTH = 2;
	private static final long serialVersionUID = MAVLINK_MSG_ID_LOG_REQUEST_END;
	

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
		packet.payload.putByte(target_system);
		packet.payload.putByte(target_component);
		return packet;		
	}

    /**
     * Decode a log_request_end message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    target_system = payload.getByte();
	    target_component = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_log_request_end(int sysid){ 		super(sysid);
    	msgid = MAVLINK_MSG_ID_LOG_REQUEST_END;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_log_request_end(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "LOG_REQUEST_END");
        //Log.d("MAVLINK_MSG_ID_LOG_REQUEST_END", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_LOG_REQUEST_END -"+" target_system:"+target_system+" target_component:"+target_component+"";
    }
}
