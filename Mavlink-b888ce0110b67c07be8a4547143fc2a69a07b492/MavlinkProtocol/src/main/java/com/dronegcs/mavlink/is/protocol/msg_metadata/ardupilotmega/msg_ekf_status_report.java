package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;

public class msg_ekf_status_report extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_EKF_STATUS_REPORT = 193;
    public static final int MAVLINK_MSG_LENGTH = 28;
    private static final long serialVersionUID = MAVLINK_MSG_ID_EKF_STATUS_REPORT;


    private int flags;

    private float velocityVariance;

    private float posHorizVariance;

    private float posVertVariance;

    private float compassVariance;

    private float terrainAltVariance;

    private float airspeedVariance;

    /**
     * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
     * @return com.dronegcs.mavlink.is.mavlink packet
     */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
        packet.payload.putInt(flags);
        packet.payload.putFloat(velocityVariance);
        packet.payload.putFloat(posHorizVariance);
        packet.payload.putFloat(posVertVariance);
        packet.payload.putFloat(compassVariance);
        packet.payload.putFloat(terrainAltVariance);
        packet.payload.putFloat(airspeedVariance);
        return packet;
    }

    /**
     * Decode a gps_rtk message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
        flags = payload.getInt();
        velocityVariance = payload.getInt();
        posHorizVariance = payload.getFloat();
        posVertVariance = payload.getFloat();
        compassVariance = payload.getFloat();
        terrainAltVariance = payload.getFloat();
        airspeedVariance = payload.getFloat();
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_ekf_status_report(int sysid){
        super(sysid);
        msgid = MAVLINK_MSG_ID_EKF_STATUS_REPORT;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     *
     * @param mavLinkPacket - com.dronegcs.mavlink.is.mavlink packet
     */
    public msg_ekf_status_report(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "GPS_RTK");
        //Log.d("MAVLINK_MSG_ID_GPS_RTK", toString());
    }


    @Override
    public String toString() {
        return "msg_ekf_status_report{" +
                "flags=" + flags +
                ", velocityVariance=" + velocityVariance +
                ", posHorizVariance=" + posHorizVariance +
                ", posVertVariance=" + posVertVariance +
                ", compassVariance=" + compassVariance +
                ", terrainAltVariance=" + terrainAltVariance +
                ", airspeedVariance=" + airspeedVariance +
                '}';
    }
}
