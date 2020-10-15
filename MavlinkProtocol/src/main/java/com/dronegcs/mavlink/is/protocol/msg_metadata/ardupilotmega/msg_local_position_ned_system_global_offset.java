// MESSAGE LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* The offset in X, Y, Z and yaw between the LOCAL_POSITION_NED messages of MAV X and the global coordinate frame in NED coordinates. Coordinate frame is right-handed, Z-axis down (aeronautical frame, NED / north-east-down convention)
*/
public class msg_local_position_ned_system_global_offset extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET = 89;
	public static final int MAVLINK_MSG_LENGTH = 28;
	private static final long serialVersionUID = MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET;
	

 	/**
	* Timestamp (milliseconds since system boot)
	*/
	public int time_boot_ms; 
 	/**
	* X Position
	*/
	public float x; 
 	/**
	* Y Position
	*/
	public float y; 
 	/**
	* Z Position
	*/
	public float z; 
 	/**
	* Roll
	*/
	public float roll; 
 	/**
	* Pitch
	*/
	public float pitch; 
 	/**
	* Yaw
	*/
	public float yaw; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putInt(time_boot_ms);
		packet.payload.putFloat(x);
		packet.payload.putFloat(y);
		packet.payload.putFloat(z);
		packet.payload.putFloat(roll);
		packet.payload.putFloat(pitch);
		packet.payload.putFloat(yaw);
		return packet;		
	}

    /**
     * Decode a local_position_ned_system_global_offset message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    time_boot_ms = payload.getInt();
	    x = payload.getFloat();
	    y = payload.getFloat();
	    z = payload.getFloat();
	    roll = payload.getFloat();
	    pitch = payload.getFloat();
	    yaw = payload.getFloat();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_local_position_ned_system_global_offset(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_local_position_ned_system_global_offset(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET");
        //Log.d("MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET", toString());
    }
    
              
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_LOCAL_POSITION_NED_SYSTEM_GLOBAL_OFFSET -"+" time_boot_ms:"+time_boot_ms+" x:"+x+" y:"+y+" z:"+z+" roll:"+roll+" pitch:"+pitch+" yaw:"+yaw+"";
    }
}
