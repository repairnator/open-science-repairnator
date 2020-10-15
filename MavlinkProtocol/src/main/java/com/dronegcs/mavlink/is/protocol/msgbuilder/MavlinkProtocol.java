package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_request_autopilot_version_request;

public class MavlinkProtocol {

    public static void getSupportedProtocol(Drone drone) {
        msg_request_autopilot_version_request msg = new msg_request_autopilot_version_request(drone.getGCS().getId());
        MAVLinkPacket a = msg.pack();
        drone.getMavClient().sendMavPacket(a);
    }
}
