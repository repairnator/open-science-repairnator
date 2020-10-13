// MESSAGE TERRAIN_REQUEST PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Request for terrain data and terrain status
*/
public class msg_terrain_request extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_TERRAIN_REQUEST = 133;
	public static final int MAVLINK_MSG_LENGTH = 18;
	private static final long serialVersionUID = MAVLINK_MSG_ID_TERRAIN_REQUEST;
	

 	/**
	* Bitmask of requested 4x4 grids (row major 8x7 array of grids, 56 bits)
	*/
	public long mask; 
 	/**
	* Latitude of SW corner of first grid (degrees *10^7)
	*/
	public int lat; 
 	/**
	* Longitude of SW corner of first grid (in degrees *10^7)
	*/
	public int lon; 
 	/**
	* Grid spacing in meters
	*/
	public short grid_spacing; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(mask);
		packet.payload.putInt(lat);
		packet.payload.putInt(lon);
		packet.payload.putShort(grid_spacing);
		return packet;		
	}

    /**
     * Decode a terrain_request message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    mask = payload.getLong();
	    lat = payload.getInt();
	    lon = payload.getInt();
	    grid_spacing = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_terrain_request(int sysid){ 		super(sysid);
msgid = MAVLINK_MSG_ID_TERRAIN_REQUEST;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_terrain_request(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "TERRAIN_REQUEST");
        //Log.d("MAVLINK_MSG_ID_TERRAIN_REQUEST", toString());
    }
    
        
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_TERRAIN_REQUEST -"+" mask:"+mask+" lat:"+lat+" lon:"+lon+" grid_spacing:"+grid_spacing+"";
    }
}
