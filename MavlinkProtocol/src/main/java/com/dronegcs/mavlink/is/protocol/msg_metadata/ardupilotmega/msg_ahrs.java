// MESSAGE AHRS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Status of DCM attitude estimator
*/
public class msg_ahrs extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_AHRS = 163;
	public static final int MAVLINK_MSG_LENGTH = 28;
	private static final long serialVersionUID = MAVLINK_MSG_ID_AHRS;
	

 	/**
	* X gyro drift estimate rad/s
	*/
	public float omegaIx; 
 	/**
	* Y gyro drift estimate rad/s
	*/
	public float omegaIy; 
 	/**
	* Z gyro drift estimate rad/s
	*/
	public float omegaIz; 
 	/**
	* average accel_weight
	*/
	public float accel_weight; 
 	/**
	* average renormalisation value
	*/
	public float renorm_val; 
 	/**
	* average error_roll_pitch value
	*/
	public float error_rp; 
 	/**
	* average error_yaw value
	*/
	public float error_yaw; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putFloat(omegaIx);
		packet.payload.putFloat(omegaIy);
		packet.payload.putFloat(omegaIz);
		packet.payload.putFloat(accel_weight);
		packet.payload.putFloat(renorm_val);
		packet.payload.putFloat(error_rp);
		packet.payload.putFloat(error_yaw);
		return packet;		
	}

    /**
     * Decode a ahrs message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    omegaIx = payload.getFloat();
	    omegaIy = payload.getFloat();
	    omegaIz = payload.getFloat();
	    accel_weight = payload.getFloat();
	    renorm_val = payload.getFloat();
	    error_rp = payload.getFloat();
	    error_yaw = payload.getFloat();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_ahrs(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_AHRS;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_ahrs(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "AHRS");
        //Log.d("MAVLINK_MSG_ID_AHRS", toString());
    }
    
              
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_AHRS -"+" omegaIx:"+omegaIx+" omegaIy:"+omegaIy+" omegaIz:"+omegaIz+" accel_weight:"+accel_weight+" renorm_val:"+renorm_val+" error_rp:"+error_rp+" error_yaw:"+error_yaw+"";
    }
}
