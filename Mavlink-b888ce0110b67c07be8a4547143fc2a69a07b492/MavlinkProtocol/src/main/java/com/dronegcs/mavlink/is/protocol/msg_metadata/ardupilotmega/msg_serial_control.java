// MESSAGE SERIAL_CONTROL PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* Control a serial port. This can be used for raw access to an onboard serial peripheral such as a GPS or telemetry radio. It is designed to make it possible to update the com.dronegcs.gcsis.devices firmware via MAVLink messages or change the com.dronegcs.gcsis.devices settings. A message with zero bytes can be used to change just the baudrate.
*/
public class msg_serial_control extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_SERIAL_CONTROL = 126;
	public static final int MAVLINK_MSG_LENGTH = 79;
	private static final long serialVersionUID = MAVLINK_MSG_ID_SERIAL_CONTROL;
	

 	/**
	* Baudrate of transfer. Zero means no change.
	*/
	public int baudrate; 
 	/**
	* Timeout for reply data in milliseconds
	*/
	public short timeout; 
 	/**
	* See SERIAL_CONTROL_DEV enum
	*/
	public byte device; 
 	/**
	* See SERIAL_CONTROL_FLAG enum
	*/
	public byte flags; 
 	/**
	* how many bytes in this transfer
	*/
	public byte count; 
 	/**
	* serial data
	*/
	public byte data[] = new byte[70]; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putInt(baudrate);
		packet.payload.putShort(timeout);
		packet.payload.putByte(device);
		packet.payload.putByte(flags);
		packet.payload.putByte(count);
		 for (int i = 0; i < data.length; i++) {
                        packet.payload.putByte(data[i]);
            }
		return packet;		
	}

    /**
     * Decode a serial_control message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    baudrate = payload.getInt();
	    timeout = payload.getShort();
	    device = payload.getByte();
	    flags = payload.getByte();
	    count = payload.getByte();
	     for (int i = 0; i < data.length; i++) {
			data[i] = payload.getByte();
		}    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_serial_control(int sysid){
    	super(sysid);
    	msgid = MAVLINK_MSG_ID_SERIAL_CONTROL;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_serial_control(MAVLinkPacket mavLinkPacket){
    	this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "SERIAL_CONTROL");
        //Log.d("MAVLINK_MSG_ID_SERIAL_CONTROL", toString());
        System.err.println("MAVLINK_MSG_ID_SERIAL_CONTROL" + toString());
    }
    
            
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_SERIAL_CONTROL -"+" baudrate:"+baudrate+" timeout:"+timeout+" device:"+device+" flags:"+flags+" count:"+count+" data:"+data+"";
    }
}
