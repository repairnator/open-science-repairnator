// MESSAGE AUTH_KEY PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;
/**
* Emit an encrypted signature / key identifying this system. PLEASE NOTE: This protocol has been kept simple, so transmitting the key requires an encrypted channel for true safety.
*/
public class msg_auth_key extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_AUTH_KEY = 7;
	public static final int MAVLINK_MSG_LENGTH = 32;
	private static final long serialVersionUID = MAVLINK_MSG_ID_AUTH_KEY;
	

 	/**
	* key
	*/
	public byte key[] = new byte[32]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return com.dronegcs.mavlink.is.mavlink packet
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		 for (int i = 0; i < key.length; i++) {
                        packet.payload.putByte(key[i]);
            }
		return packet;		
	}

    /**
     * Decode a auth_key message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	     for (int i = 0; i < key.length; i++) {
			key[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_auth_key(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_AUTH_KEY;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     * @param mavLinkPacket
     */
    public msg_auth_key(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "AUTH_KEY");
        //Log.d("MAVLINK_MSG_ID_AUTH_KEY", toString());
    }
    
 /**
     * Sets the buffer of this message with a string, adds the necessary padding
     */    
    public void setKey(String str) {
      int len = Math.min(str.length(), 32);
      for (int i=0; i<len; i++) {
        key[i] = (byte) str.charAt(i);
      }
      for (int i=len; i<32; i++) {			// padding for the rest of the buffer
        key[i] = 0;
      }
    }
    
    /**
	 * Gets the message, formated as a string
	 * 
	 * @return authkey string
	 */
	public String getKey() {
		String result = "";
		for (int i = 0; i < 32; i++) {
			if (key[i] != 0)
				result = result + (char) key[i];
			else
				break;
		}
		return result;
		
	} 
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_AUTH_KEY -"+" key:"+key+"";
    }
}
