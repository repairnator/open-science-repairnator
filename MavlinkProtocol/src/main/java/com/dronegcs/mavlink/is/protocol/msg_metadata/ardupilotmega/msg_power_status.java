// MESSAGE POWER_STATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Power supply status
*/
public class msg_power_status extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_POWER_STATUS = 125;
	public static final int MAVLINK_MSG_LENGTH = 6;
	private static final long serialVersionUID = MAVLINK_MSG_ID_POWER_STATUS;
	

 	/**
	* 5V rail voltage in millivolts
	*/
	public short Vcc; 
 	/**
	* servo rail voltage in millivolts
	*/
	public short Vservo; 
 	/**
	* power supply status flags (see MAV_POWER_STATUS enum)
	*/
	public short flags; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(Vcc);
		packet.payload.putShort(Vservo);
		packet.payload.putShort(flags);
		return packet;		
	}

    /**
     * Decode a power_status message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    Vcc = payload.getShort();
	    Vservo = payload.getShort();
	    flags = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_power_status(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_POWER_STATUS;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_power_status(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "POWER_STATUS");
        //Log.d("MAVLINK_MSG_ID_POWER_STATUS", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_POWER_STATUS -"+" Vcc:"+Vcc+" Vservo:"+Vservo+" flags:"+flags+"";
    }
}
