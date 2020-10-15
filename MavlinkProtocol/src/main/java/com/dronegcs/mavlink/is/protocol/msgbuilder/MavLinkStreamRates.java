package com.dronegcs.mavlink.is.protocol.msgbuilder;

import com.dronegcs.mavlink.is.connection.MavLinkConnection;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_request_data_stream;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_DATA_STREAM;

public class MavLinkStreamRates {

	//public static void setupStreamRates(MAVLinkOutputStream MAVClient, int extendedStatus,
	public static void setupStreamRates(Drone drone, int extendedStatus,
										int extra1, int extra2, int extra3, int position, int rcChannels, int rawSensors,
										int rawControler) {
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTENDED_STATUS, extendedStatus);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA1, extra1);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA2, extra2);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_EXTRA3, extra3);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_POSITION, position);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_RAW_SENSORS, rawSensors);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_RAW_CONTROLLER, rawControler);
		requestMavlinkDataStream(drone, MAV_DATA_STREAM.MAV_DATA_STREAM_RC_CHANNELS, rcChannels);
	}

	//private static void requestMavlinkDataStream(MAVLinkOutputStream mAVClient, int stream_id,
	private static void requestMavlinkDataStream(Drone drone, int stream_id, int rate) {
		msg_request_data_stream msg = new msg_request_data_stream(drone.getGCS().getId());
		msg.target_system = 1;
		msg.target_component = 1;

		msg.req_message_rate = (short) rate;
		msg.req_stream_id = (byte) stream_id;

		if (rate > 0) {
			msg.start_stop = 1;
		} else {
			msg.start_stop = 0;
		}
		drone.getMavClient().sendMavPacket(msg.pack());
	}
}
