// MESSAGE HWSTATUS PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Send raw controller memory. The use of this message is discouraged for normal packets, but a quite efficient way for testing new messages and getting experimental debug output.
*/
public class msg_memory_vect extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_MEMORY_VECT = 249;
	public static final int MAVLINK_MSG_LENGTH = 36;
	private static final long serialVersionUID = MAVLINK_MSG_ID_MEMORY_VECT;
	

 	/**
	* Starting address of the debug variables
	*/
	public short address; 
 	/**
	* Version code of the type variable. 0=unknown, type ignored and assumed int16_t. 1=as below
	*/
	public byte ver; 
 	/**
	* Type code of the memory variables. for ver = 1: 0=16 x int16_t, 1=16 x uint16_t, 2=16 x Q15, 3=16 x 1Q14
	*/
	public byte type; 
 	/**
	* Memory contents at specified address
	*/
	public byte value[] = new byte[32]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putShort(address);
		packet.payload.putByte(ver);
		packet.payload.putByte(type);
		 for (int i = 0; i < value.length; i++) {
                        packet.payload.putByte(value[i]);
            }
		return packet;		
	}

    /**
     * Decode a memory_vect message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    address = payload.getShort();
	    ver = payload.getByte();
	    type = payload.getByte();
	     for (int i = 0; i < value.length; i++) {
			value[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_memory_vect(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_MEMORY_VECT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_memory_vect(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "MEMORY_VECT");
        //Log.d("MAVLINK_MSG_ID_MEMORY_VECT", toString());
    }
    
        
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_MEMORY_VECT -"+" address:"+address+" ver:"+ver+" type:"+type+" value:"+value+"";
    }
}
