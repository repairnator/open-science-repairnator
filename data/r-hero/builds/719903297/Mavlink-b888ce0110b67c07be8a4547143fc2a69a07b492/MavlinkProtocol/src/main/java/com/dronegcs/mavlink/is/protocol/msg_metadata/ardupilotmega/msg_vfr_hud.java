
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Metrics typically displayed on a HUD for fixed wing aircraft
*/
public class msg_vfr_hud extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_VFR_HUD = 74;
	public static final int MAVLINK_MSG_LENGTH = 20;
	private static final long serialVersionUID = MAVLINK_MSG_ID_VFR_HUD;
	

 	/**
	* Current airspeed in m/s
	*/
	public float airspeed; 
 	/**
	* Current ground speed in m/s
	*/
	public float groundspeed; 
 	/**
	* Current altitude (MSL), in meters
	*/
	public float alt; 
 	/**
	* Current climb rate in meters/second
	*/
	public float climb; 
 	/**
	* Current heading in degrees, in compass units (0..360, 0=north)
	*/
	public short heading; 
 	/**
	* Current throttle setting in integer percent, 0 to 100
	*/
	public short throttle; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(airspeed);
		packet.payload.putFloat(groundspeed);
		packet.payload.putFloat(alt);
		packet.payload.putFloat(climb);
		packet.payload.putShort(heading);
		packet.payload.putShort(throttle);
		return packet;		
	}

    /**
     * Decode a vfr_hud message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    airspeed = payload.getFloat();
	    groundspeed = payload.getFloat();
	    alt = payload.getFloat();
	    climb = payload.getFloat();
	    heading = payload.getShort();
	    throttle = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_vfr_hud(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_VFR_HUD;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_vfr_hud(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "VFR_HUD");
        //Log.d("MAVLINK_MSG_ID_VFR_HUD", toString());
    }
    
            
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_VFR_HUD -"+" airspeed:"+airspeed+" groundspeed:"+groundspeed+" alt:"+alt+" climb:"+climb+" heading:"+heading+" throttle:"+throttle+"";
    }
}
