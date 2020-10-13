// MESSAGE DATA_TRANSMISSION_HANDSHAKE PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* 
*/
public class msg_data_transmission_handshake extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE = 130;
	public static final int MAVLINK_MSG_LENGTH = 13;
	private static final long serialVersionUID = MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE;
	

 	/**
	* total data size in bytes (set on ACK only)
	*/
	public int size; 
 	/**
	* Width of a matrix or image
	*/
	public short width; 
 	/**
	* Height of a matrix or image
	*/
	public short height; 
 	/**
	* number of packets beeing sent (set on ACK only)
	*/
	public short packets; 
 	/**
	* type of requested/acknowledged data (as defined in ENUM DATA_TYPES in com.dronegcs.mavlink.is.mavlink/include/mavlink_types.h)
	*/
	public byte type; 
 	/**
	* payload size per packet (normally 253 byte, see DATA field size in message ENCAPSULATED_DATA) (set on ACK only)
	*/
	public byte payload; 
 	/**
	* JPEG quality out of [1,100]
	*/
	public byte jpg_quality; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putInt(size);
		packet.payload.putShort(width);
		packet.payload.putShort(height);
		packet.payload.putShort(packets);
		packet.payload.putByte(type);
		packet.payload.putByte(payload);
		packet.payload.putByte(jpg_quality);
		return packet;		
	}

    /**
     * Decode a data_transmission_handshake message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    size = payload.getInt();
	    width = payload.getShort();
	    height = payload.getShort();
	    packets = payload.getShort();
	    type = payload.getByte();
	    //payload = payload.getByte(); TODO fix this message
	    jpg_quality = payload.getByte();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_data_transmission_handshake(int sysid){
		super(sysid);
		msgid = MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_data_transmission_handshake(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "DATA_TRANSMISSION_HANDSHAKE");
        //Log.d("MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE", toString());
    }
    
              
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_DATA_TRANSMISSION_HANDSHAKE -"+" size:"+size+" width:"+width+" height:"+height+" packets:"+packets+" type:"+type+" payload:"+payload+" jpg_quality:"+jpg_quality+"";
    }
}
