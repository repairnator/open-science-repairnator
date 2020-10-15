
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* The system time is the time of the master clock, typically the computer clock of the main onboard computer.
*/
public class msg_system_time extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_SYSTEM_TIME = 2;
	public static final int MAVLINK_MSG_LENGTH = 12;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SYSTEM_TIME;
	

 	/**
	* Timestamp of the master clock in microseconds since UNIX epoch.
	*/
	public long time_unix_usec; 
 	/**
	* Timestamp of the component clock since boot time in milliseconds.
	*/
	public int time_boot_ms; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(time_unix_usec);
		packet.payload.putInt(time_boot_ms);
		return packet;		
	}

    /**
     * Decode a system_time message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    time_unix_usec = payload.getLong();
	    time_boot_ms = payload.getInt();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_system_time(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_SYSTEM_TIME;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_system_time(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SYSTEM_TIME");
        //Log.d("MAVLINK_MSG_ID_SYSTEM_TIME", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_SYSTEM_TIME -"+" time_unix_usec:"+time_unix_usec+" time_boot_ms:"+time_boot_ms+"";
    }
}
