// MESSAGE HWSTATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;
/**
* Status of key hardware
*/
public class msg_hwstatus extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_HWSTATUS = 165;
	public static final int MAVLINK_MSG_LENGTH = 3;
	private static final long serialVersionUID = MAVLINK_MSG_ID_HWSTATUS;
	

 	/**
	* board voltage (mV)
	*/
	public short Vcc; 
 	/**
	* I2C error count
	*/
	public byte I2Cerr; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(Vcc);
		packet.payload.putByte(I2Cerr);
		return packet;		
	}

    /**
     * Decode a hwstatus message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    Vcc = payload.getShort();
	    I2Cerr = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_hwstatus(int sysid){
		super(sysid);msgid = MAVLINK_MSG_ID_HWSTATUS;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_hwstatus(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "HWSTATUS");
        //Log.d("MAVLINK_MSG_ID_HWSTATUS", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_HWSTATUS -"+" Vcc:"+Vcc+" I2Cerr:"+I2Cerr+"";
    }
}
