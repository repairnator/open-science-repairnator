package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_ping;

public class MavlinkPing {

    private static int pingSeq = 0;

    public static void sendPing(Drone drone) {
        msg_ping msg = new msg_ping(drone.getGCS().getId());
        msg.time_usec = System.currentTimeMillis();
        msg.seq = pingSeq;
        msg.target_system = 0;
        msg.target_component = 0;
        pingSeq++;
        drone.getMavClient().sendMavPacket(msg.pack());
    }
}
