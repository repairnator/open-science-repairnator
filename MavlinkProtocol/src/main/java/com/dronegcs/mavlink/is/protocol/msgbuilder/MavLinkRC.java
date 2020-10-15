package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_rc_channels_override;

public class MavLinkRC {
	public static void sendRcOverrideMsg(Drone drone, int[] rcOutputs) {
		msg_rc_channels_override msg = new msg_rc_channels_override(drone.getGCS().getId());
		msg.chan1_raw = (short) rcOutputs[0];
		msg.chan2_raw = (short) rcOutputs[1];
		msg.chan3_raw = (short) rcOutputs[2];
		msg.chan4_raw = (short) rcOutputs[3];
		msg.chan5_raw = (short) rcOutputs[4];
		msg.chan6_raw = (short) rcOutputs[5];
		msg.chan7_raw = (short) rcOutputs[6];
		msg.chan8_raw = (short) rcOutputs[7];
		msg.target_system = 1;
		msg.target_component = 1;
		drone.getMavClient().sendMavPacket(msg.pack());
	}
}
