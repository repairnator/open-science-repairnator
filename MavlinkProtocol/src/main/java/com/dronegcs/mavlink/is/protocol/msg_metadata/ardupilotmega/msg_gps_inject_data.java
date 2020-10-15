// MESSAGE GPS_INJECT_DATA PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* data for injecting into the onboard GPS (used for DGPS)
*/
public class msg_gps_inject_data extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_GPS_INJECT_DATA = 123;
	public static final int MAVLINK_MSG_LENGTH = 113;
	private static final long serialVersionUID = MAVLINK_MSG_ID_GPS_INJECT_DATA;
	

 	/**
	* System ID
	*/
	public byte target_system; 
 	/**
	* Component ID
	*/
	public byte target_component; 
 	/**
	* data length
	*/
	public byte len; 
 	/**
	* raw data (110 is enough for 12 satellites of RTCMv2)
	*/
	public byte data[] = new byte[110]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return com.dronegcs.mavlink.is.mavlink packet
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putByte(target_system);
		packet.payload.putByte(target_component);
		packet.payload.putByte(len);
		 for (int i = 0; i < data.length; i++) {
                        packet.payload.putByte(data[i]);
            }
		return packet;		
	}

    /**
     * Decode a gps_inject_data message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    target_system = payload.getByte();
	    target_component = payload.getByte();
	    len = payload.getByte();
	     for (int i = 0; i < data.length; i++) {
			data[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_gps_inject_data(int sysid){
		super(sysid);msgid = MAVLINK_MSG_ID_GPS_INJECT_DATA;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     * @param mavLinkPacket - com.dronegcs.mavlink.is.mavlink packet
     */
    public msg_gps_inject_data(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "GPS_INJECT_DATA");
        //Log.d("MAVLINK_MSG_ID_GPS_INJECT_DATA", toString());
    }
    
        
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_GPS_INJECT_DATA -"+" target_system:"+target_system+" target_component:"+target_component+" len:"+len+" data:"+data+"";
    }
}
