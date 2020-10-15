// MESSAGE SET_GPS_GLOBAL_ORIGIN PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* As local waypoints exist, the global MISSION reference allows to transform between the local coordinate frame and the global (GPS) coordinate frame. This can be necessary when e.g. in- and outdoor settings are connected and the MAV should move from in- to outdoor.
*/
public class msg_set_gps_global_origin extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN = 48;
	public static final int MAVLINK_MSG_LENGTH = 13;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;
	

 	/**
	* Latitude (WGS84), in degrees * 1E7
	*/
	public int latitude; 
 	/**
	* Longitude (WGS84, in degrees * 1E7
	*/
	public int longitude; 
 	/**
	* Altitude (WGS84), in meters * 1000 (positive for up)
	*/
	public int altitude; 
 	/**
	* System ID
	*/
	public byte target_system; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putInt(latitude);
		packet.payload.putInt(longitude);
		packet.payload.putInt(altitude);
		packet.payload.putByte(target_system);
		return packet;		
	}

    /**
     * Decode a set_gps_global_origin message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    latitude = payload.getInt();
	    longitude = payload.getInt();
	    altitude = payload.getInt();
	    target_system = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_set_gps_global_origin(int sysid){ 		super(sysid);
msgid = MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_set_gps_global_origin(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SET_GPS_GLOBAL_ORIGIN");
        //Log.d("MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN", toString());
    }
    
        
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_SET_GPS_GLOBAL_ORIGIN -"+" latitude:"+latitude+" longitude:"+longitude+" altitude:"+altitude+" target_system:"+target_system+"";
    }
}
