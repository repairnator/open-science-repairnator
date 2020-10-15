
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* The system time is the time of the master clock, typically the computer clock of the main onboard computer.
*/
public class msg_time_sync extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_TIME_SYNC = 111;
	public static final int MAVLINK_MSG_LENGTH = 16;
	private static final long serialVersionUID = MAVLINK_MSG_ID_TIME_SYNC;


 	/**
	* TTime sync timestamp 1 in microseconds since UNIX epoch.
	*/
	public long tc1;
 	/**
	* Time sync timestamp 2 time in milliseconds.
	*/
	public long ts1;

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(tc1);
		packet.payload.putLong(ts1);
		return packet;
	}

    /**
     * Decode a system_time message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    tc1 = payload.getLong();
		ts1 = payload.getLong();
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_time_sync(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_TIME_SYNC;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     *
     */
    public msg_time_sync(MAVLinkPacket mavLinkPacket){
		this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SYSTEM_TIME");
        //Log.d("MAVLINK_MSG_ID_SYSTEM_TIME", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_TIME_SYNC -"+" tc1:"+tc1+" ts1:"+ts1+"";
    }
}
