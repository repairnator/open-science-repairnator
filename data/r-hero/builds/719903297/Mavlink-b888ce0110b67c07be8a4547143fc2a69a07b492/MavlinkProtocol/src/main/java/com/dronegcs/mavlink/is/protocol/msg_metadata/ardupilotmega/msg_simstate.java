
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Status of simulation com.dronegcs.gcsis.environment, if used
*/
public class msg_simstate extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_SIMSTATE = 164;
	public static final int MAVLINK_MSG_LENGTH = 44;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SIMSTATE;
	

 	/**
	* Roll angle (rad)
	*/
	public float roll; 
 	/**
	* Pitch angle (rad)
	*/
	public float pitch; 
 	/**
	* Yaw angle (rad)
	*/
	public float yaw; 
 	/**
	* X acceleration m/s/s
	*/
	public float xacc; 
 	/**
	* Y acceleration m/s/s
	*/
	public float yacc; 
 	/**
	* Z acceleration m/s/s
	*/
	public float zacc; 
 	/**
	* Angular speed around X axis rad/s
	*/
	public float xgyro; 
 	/**
	* Angular speed around Y axis rad/s
	*/
	public float ygyro; 
 	/**
	* Angular speed around Z axis rad/s
	*/
	public float zgyro; 
 	/**
	* Latitude in degrees * 1E7
	*/
	public int lat; 
 	/**
	* Longitude in degrees * 1E7
	*/
	public int lng; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(roll);
		packet.payload.putFloat(pitch);
		packet.payload.putFloat(yaw);
		packet.payload.putFloat(xacc);
		packet.payload.putFloat(yacc);
		packet.payload.putFloat(zacc);
		packet.payload.putFloat(xgyro);
		packet.payload.putFloat(ygyro);
		packet.payload.putFloat(zgyro);
		packet.payload.putInt(lat);
		packet.payload.putInt(lng);
		return packet;		
	}

    /**
     * Decode a simstate message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    roll = payload.getFloat();
	    pitch = payload.getFloat();
	    yaw = payload.getFloat();
	    xacc = payload.getFloat();
	    yacc = payload.getFloat();
	    zacc = payload.getFloat();
	    xgyro = payload.getFloat();
	    ygyro = payload.getFloat();
	    zgyro = payload.getFloat();
	    lat = payload.getInt();
	    lng = payload.getInt();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_simstate(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_SIMSTATE;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_simstate(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SIMSTATE");
        //Log.d("MAVLINK_MSG_ID_SIMSTATE", toString());
    }
    
                      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_SIMSTATE -"+" roll:"+roll+" pitch:"+pitch+" yaw:"+yaw+" xacc:"+xacc+" yacc:"+yacc+" zacc:"+zacc+" xgyro:"+xgyro+" ygyro:"+ygyro+" zgyro:"+zgyro+" lat:"+lat+" lng:"+lng+"";
    }
}
