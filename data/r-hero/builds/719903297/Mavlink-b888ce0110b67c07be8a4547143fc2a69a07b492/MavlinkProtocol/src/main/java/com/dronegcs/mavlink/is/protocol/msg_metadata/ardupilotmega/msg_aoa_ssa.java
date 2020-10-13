package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

//Mavlink 2 - ardupilot
public class msg_aoa_ssa  extends MAVLinkMessage {

    public static final int MAVLINK_MSG_AOA_SSA = 11020;
    public static final int MAVLINK_MSG_LENGTH = 16;
    private static final long serialVersionUID = MAVLINK_MSG_AOA_SSA;


    public long timeUsec;

    public float aoa;

    public float ssa;

    /**
     * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
     * @return
     */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
        packet.payload.putLong(timeUsec);
        packet.payload.putFloat(aoa);
        packet.payload.putFloat(ssa);
        return packet;
    }

    /**
     * Decode a ap_adc message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        timeUsec = payload.getLong();
        aoa = payload.getFloat();
        ssa = payload.getFloat();
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_aoa_ssa(int sysid){
        super(sysid);
        msgid = MAVLINK_MSG_AOA_SSA;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     *
     */
    public msg_aoa_ssa(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "AP_ADC");
        //Log.d("MAVLINK_MSG_ID_AP_ADC", toString());
    }


    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
        return "MAVLINK_MSG_AOA_SSA -"+" timeUsec:"+timeUsec+" aoa:"+aoa+" ssa:"+ssa;
    }
}

