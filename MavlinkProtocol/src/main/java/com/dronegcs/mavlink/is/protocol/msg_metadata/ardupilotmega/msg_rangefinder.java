
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Rangefinder reporting
*/
public class msg_rangefinder extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_RANGEFINDER = 173;
	public static final int MAVLINK_MSG_LENGTH = 8;
	private static final long serialVersionUID = MAVLINK_MSG_ID_RANGEFINDER;
	

 	/**
	* distance in meters
	*/
	public float distance; 
 	/**
	* raw voltage if available, zero otherwise
	*/
	public float voltage; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(distance);
		packet.payload.putFloat(voltage);
		return packet;		
	}

    /**
     * Decode a rangefinder message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    distance = payload.getFloat();
	    voltage = payload.getFloat();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_rangefinder(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_RANGEFINDER;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_rangefinder(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "RANGEFINDER");
        //Log.d("MAVLINK_MSG_ID_RANGEFINDER", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_RANGEFINDER -"+" distance:"+distance+" voltage:"+voltage+"";
    }
}
