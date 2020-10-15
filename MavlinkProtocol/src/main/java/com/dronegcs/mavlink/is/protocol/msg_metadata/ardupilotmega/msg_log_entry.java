// MESSAGE HWSTATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Reply to LOG_REQUEST_LIST
*/
public class msg_log_entry extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_LOG_ENTRY = 118;
	public static final int MAVLINK_MSG_LENGTH = 14;
	private static final long serialVersionUID = MAVLINK_MSG_ID_LOG_ENTRY;
	

 	/**
	* UTC timestamp of log in seconds since 1970, or 0 if not available
	*/
	public int time_utc; 
 	/**
	* Size of the log (may be approximate) in bytes
	*/
	public int size; 
 	/**
	* Log id
	*/
	public short id; 
 	/**
	* Total number of logs
	*/
	public short num_logs; 
 	/**
	* High log number
	*/
	public short last_log_num; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putInt(time_utc);
		packet.payload.putInt(size);
		packet.payload.putShort(id);
		packet.payload.putShort(num_logs);
		packet.payload.putShort(last_log_num);
		return packet;		
	}

    /**
     * Decode a log_entry message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    time_utc = payload.getInt();
	    size = payload.getInt();
	    id = payload.getShort();
	    num_logs = payload.getShort();
	    last_log_num = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_log_entry(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_LOG_ENTRY;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_log_entry(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "LOG_ENTRY");
        //Log.d("MAVLINK_MSG_ID_LOG_ENTRY", toString());
    }
    
          
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_LOG_ENTRY -"+" time_utc:"+time_utc+" size:"+size+" id:"+id+" num_logs:"+num_logs+" last_log_num:"+last_log_num+"";
    }
}
