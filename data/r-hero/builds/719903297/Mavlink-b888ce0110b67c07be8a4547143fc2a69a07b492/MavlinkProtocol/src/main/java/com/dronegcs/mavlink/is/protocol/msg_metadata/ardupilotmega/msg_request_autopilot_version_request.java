
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

/**
* The system time is the time of the master clock, typically the computer clock of the main onboard computer.
*/
public class msg_request_autopilot_version_request extends MAVLinkMessage {

	public static final int MAVLINK_MSG_ID_AUTOPILOT_VERSION_REQUEST = 183;
	public static final int MAVLINK_MSG_LENGTH = 2;
	private static final long serialVersionUID = MAVLINK_MSG_ID_AUTOPILOT_VERSION_REQUEST;

    /**
     * System ID
     */
    public byte target_system = 1;
    /**
     * Component ID
     */
    public byte target_component = 1;

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_request_autopilot_version_request(int sysid){
        super(sysid);
        msgid = MAVLINK_MSG_ID_AUTOPILOT_VERSION_REQUEST;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     *
     */
    public msg_request_autopilot_version_request(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
    }
    
    
    /**
     * Returns a string with the MSG name and data
     */
    @Override
    public String toString() {
        return "MAVLINK_MSG_ID_AUTOPILOT_VERSION_REQUEST{" +
                "target_system=" + target_system +
                ", target_component=" + target_component +
                '}';
    }

    /**
     * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
     * @return
     */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
        packet.payload.putByte(target_system);
        packet.payload.putByte(target_component);
        return packet;
    }

    /**
     * Decode a command_long message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        this.target_system = payload.getByte();
        this.target_component = payload.getByte();
//        param1 = payload.getFloat();
//        param2 = payload.getFloat();
//        param3 = payload.getFloat();
//        param4 = payload.getFloat();
//        param5 = payload.getFloat();
//        param6 = payload.getFloat();
//        param7 = payload.getFloat();
//        command = payload.getShort();
//        target_system = payload.getByte();
//        target_component = payload.getByte();
//        confirmation = payload.getByte();
    }
}
