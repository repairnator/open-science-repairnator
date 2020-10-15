
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Wind estimation
*/
public class msg_wind extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_WIND = 168;
	public static final int MAVLINK_MSG_LENGTH = 12;
	private static final long serialVersionUID = MAVLINK_MSG_ID_WIND;
	

 	/**
	* wind direction that wind is coming from (degrees)
	*/
	public float direction; 
 	/**
	* wind speed in ground plane (m/s)
	*/
	public float speed; 
 	/**
	* vertical wind speed (m/s)
	*/
	public float speed_z; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(direction);
		packet.payload.putFloat(speed);
		packet.payload.putFloat(speed_z);
		return packet;		
	}

    /**
     * Decode a wind message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    direction = payload.getFloat();
	    speed = payload.getFloat();
	    speed_z = payload.getFloat();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_wind(int sysid){ 		super(sysid);
    	msgid = MAVLINK_MSG_ID_WIND;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_wind(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "WIND");
        //Log.d("MAVLINK_MSG_ID_WIND", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_WIND -"+" direction:"+direction+" speed:"+speed+" speed_z:"+speed_z+"";
    }
}
