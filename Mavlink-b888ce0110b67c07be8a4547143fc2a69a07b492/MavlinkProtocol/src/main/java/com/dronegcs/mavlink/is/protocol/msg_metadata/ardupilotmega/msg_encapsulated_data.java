// MESSAGE ENCAPSULATED_DATA PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* 
*/
public class msg_encapsulated_data extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_ENCAPSULATED_DATA = 131;
	public static final int MAVLINK_MSG_LENGTH = 255;
	private static final long serialVersionUID = MAVLINK_MSG_ID_ENCAPSULATED_DATA;
	

 	/**
	* sequence number (starting with 0 on every transmission)
	*/
	public short seqnr; 
 	/**
	* image data bytes
	*/
	public byte data[] = new byte[253]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(seqnr);
		 for (int i = 0; i < data.length; i++) {
                        packet.payload.putByte(data[i]);
            }
		return packet;		
	}

    /**
     * Decode a encapsulated_data message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    seqnr = payload.getShort();
	     for (int i = 0; i < data.length; i++) {
			data[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_encapsulated_data(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_encapsulated_data(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "ENCAPSULATED_DATA");
        //Log.d("MAVLINK_MSG_ID_ENCAPSULATED_DATA", toString());
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_ENCAPSULATED_DATA -"+" seqnr:"+seqnr+" data:"+data+"";
    }
}
