// MESSAGE DATA16 PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Data packet, size 16
*/
public class msg_data16 extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_DATA16 = 169;
	public static final int MAVLINK_MSG_LENGTH = 18;
	private static final long serialVersionUID = MAVLINK_MSG_ID_DATA16;
	

 	/**
	* data type
	*/
	public byte type; 
 	/**
	* data length
	*/
	public byte len; 
 	/**
	* raw data
	*/
	public byte data[] = new byte[16]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putByte(type);
		packet.payload.putByte(len);
		 for (int i = 0; i < data.length; i++) {
                        packet.payload.putByte(data[i]);
            }
		return packet;		
	}

    /**
     * Decode a data16 message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    type = payload.getByte();
	    len = payload.getByte();
	     for (int i = 0; i < data.length; i++) {
			data[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_data16(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_DATA16;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_data16(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "DATA16");
        //Log.d("MAVLINK_MSG_ID_DATA16", toString());
    }
    
      
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_DATA16 -"+" type:"+type+" len:"+len+" data:"+data+"";
    }
}
