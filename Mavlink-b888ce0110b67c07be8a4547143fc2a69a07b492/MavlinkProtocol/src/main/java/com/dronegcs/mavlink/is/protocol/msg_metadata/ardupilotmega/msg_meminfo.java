// MESSAGE MEMINFO PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* state of APM memory
*/
public class msg_meminfo extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_MEMINFO = 152;
	public static final int MAVLINK_MSG_LENGTH = 4;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MEMINFO;
	

 	/**
	* heap top
	*/
	public short brkval; 
 	/**
	* free memory
	*/
	public short freemem; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(brkval);
		packet.payload.putShort(freemem);
		return packet;		
	}

    /**
     * Decode a meminfo message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    brkval = payload.getShort();
	    freemem = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_meminfo(int sysid){
		super(sysid);msgid = MAVLINK_MSG_ID_MEMINFO;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_meminfo(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "MEMINFO");
        //Log.d("MAVLINK_MSG_ID_MEMINFO", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_MEMINFO -"+" brkval:"+brkval+" freemem:"+freemem+"";
    }
}
