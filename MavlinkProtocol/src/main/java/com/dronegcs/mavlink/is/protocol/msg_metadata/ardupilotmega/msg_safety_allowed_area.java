// MESSAGE SAFETY_ALLOWED_AREA PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Read out the safety zone the MAV currently assumes.
*/
public class msg_safety_allowed_area extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA = 55;
	public static final int MAVLINK_MSG_LENGTH = 25;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA;
	

 	/**
	* x position 1 / Latitude 1
	*/
	public float p1x; 
 	/**
	* y position 1 / Longitude 1
	*/
	public float p1y; 
 	/**
	* z position 1 / Altitude 1
	*/
	public float p1z; 
 	/**
	* x position 2 / Latitude 2
	*/
	public float p2x; 
 	/**
	* y position 2 / Longitude 2
	*/
	public float p2y; 
 	/**
	* z position 2 / Altitude 2
	*/
	public float p2z; 
 	/**
	* Coordinate frame, as defined by MAV_FRAME enum in mavlink_types.h. Can be either global, GPS, right-handed with Z axis up or local, right handed, Z axis down.
	*/
	public byte frame; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(p1x);
		packet.payload.putFloat(p1y);
		packet.payload.putFloat(p1z);
		packet.payload.putFloat(p2x);
		packet.payload.putFloat(p2y);
		packet.payload.putFloat(p2z);
		packet.payload.putByte(frame);
		return packet;		
	}

    /**
     * Decode a safety_allowed_area message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    p1x = payload.getFloat();
	    p1y = payload.getFloat();
	    p1z = payload.getFloat();
	    p2x = payload.getFloat();
	    p2y = payload.getFloat();
	    p2z = payload.getFloat();
	    frame = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_safety_allowed_area(int sysid){ 		super(sysid);
msgid = MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_safety_allowed_area(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SAFETY_ALLOWED_AREA");
        //Log.d("MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA", toString());
    }
    
              
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_SAFETY_ALLOWED_AREA -"+" p1x:"+p1x+" p1y:"+p1y+" p1z:"+p1z+" p2x:"+p2x+" p2y:"+p2y+" p2z:"+p2z+" frame:"+frame+"";
    }
}
