package com.dronegcs.mavlink.core.connection;

import com.dronegcs.mavlink.is.connection.MavLinkConnectionListener;
import com.dronegcs.mavlink.is.connection.MavlinkVersions;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ApmModes;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.*;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_MODE_FLAG;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PROTOCOL_CAPABILITY;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_STATE;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_TYPE;
import com.generic_tools.logger.Logger;
import com.geo_tools.Coordinate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import static com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket.MAVLINK_1;
import static com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket.MAVLINK_2;

@Component
public class DroneUpdateListener implements MavLinkConnectionListener {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DroneUpdateListener.class);

	private static final byte SEVERITY_HIGH = 3;
    private static final byte SEVERITY_CRITICAL = 4;
	byte t_compid = 1;
	byte t_sysid = 1;

	@Autowired @NotNull(message = "Internal Error: Failed to get drone")
	private Drone drone;

	@Autowired @NotNull(message = "Internal Error: Failed to get logger")
	private Logger logger;
	
	static int called;
	@PostConstruct
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
	}
	
	@Override
	public void onConnect() {
		logger.LogGeneralMessege("Connected!");
		LOGGER.debug("On Connect!!");
	}

	@Override
	public void onReceiveMessage(MAVLinkMessage msg) {		
		if (msg == null) {
			logger.LogErrorMessege("Received empty message from quad, please restart the GCS");
			LOGGER.error("Received empty message from quad, please restart the GCS");
			System.exit(0);
		}
		
		//System.err.println("[RCV] " + msg.toString());
		LOGGER.trace("[RCV] {}", msg.toString());
		
		if (drone.getParameters().processMessage(msg)) {
			return;
		}
		
		String log_entry = Logger.generateDesignedMessege(msg.toString(), Logger.Type.INCOMING, false);
		logger.LogDesignedMessege(log_entry);

		drone.getWaypointManager().processMessage(msg);
//		drone.getCalibrationSetup().processMessage(msg);

		switch (msg.msgid) {
			case msg_attitude.MAVLINK_MSG_ID_ATTITUDE:
				msg_attitude m_att = (msg_attitude) msg;
				drone.getOrientation().setRollPitchYaw(m_att.roll * 180.0 / Math.PI,
						m_att.pitch * 180.0 / Math.PI, m_att.yaw * 180.0 / Math.PI);
				break;
			case msg_vfr_hud.MAVLINK_MSG_ID_VFR_HUD:
				msg_vfr_hud m_hud = (msg_vfr_hud) msg;
				drone.setAltitudeGroundAndAirSpeeds(m_hud.alt, m_hud.groundspeed, m_hud.airspeed,
						m_hud.climb);
				break;
			case msg_mission_current.MAVLINK_MSG_ID_MISSION_CURRENT:
				drone.getMissionStats().setWpno(((msg_mission_current) msg).seq);
				break;
			case msg_nav_controller_output.MAVLINK_MSG_ID_NAV_CONTROLLER_OUTPUT:
				msg_nav_controller_output m_nav = (msg_nav_controller_output) msg;
				drone.setDisttowpAndSpeedAltErrors(m_nav.wp_dist, m_nav.alt_error, m_nav.aspd_error);
				drone.getNavigation().setNavPitchRollYaw(m_nav.nav_pitch, m_nav.nav_roll,
						m_nav.nav_bearing);
				break;
	
			case msg_raw_imu.MAVLINK_MSG_ID_RAW_IMU:
				msg_raw_imu msg_imu = (msg_raw_imu) msg;
				drone.getMagnetometer().newData(msg_imu);
				break;

			case msg_scaled_imu.MAVLINK_MSG_ID_SCALED_IMU:
				msg_scaled_imu msg_scaled_imu = (msg_scaled_imu) msg;
				drone.getMagnetometer().newData(msg_scaled_imu);
				break;

			case msg_scaled_imu2.MAVLINK_MSG_ID_SCALED_IMU2:
				msg_scaled_imu2 msg_scaled_imu2 = (msg_scaled_imu2) msg;
				drone.getMagnetometer().newData(msg_scaled_imu2);
				break;
	
			case msg_heartbeat.MAVLINK_MSG_ID_HEARTBEAT:
				msg_heartbeat msg_heart = (msg_heartbeat) msg;
				drone.setType(MAV_TYPE.getType(msg_heart.type));
				drone.getState().setIsFlying(
						((msg_heartbeat) msg).system_status == MAV_STATE.MAV_STATE_ACTIVE);
				processState(msg_heart);
				ApmModes newMode = ApmModes.getMode(msg_heart.custom_mode, drone.getType().getDroneType());
				drone.getState().setMode(newMode);
				drone.onHeartbeat(msg_heart);
				return;
				//break;
	
			case msg_global_position_int.MAVLINK_MSG_ID_GLOBAL_POSITION_INT:
				Coordinate c = new Coordinate(((msg_global_position_int) msg).lat / 1E7,
						((msg_global_position_int) msg).lon / 1E7);
				drone.getGps().setPosition(c);
				drone.getPerimeter().setPosition(c);
				break;

			case msg_sys_status.MAVLINK_MSG_ID_SYS_STATUS:
				msg_sys_status m_sys = (msg_sys_status) msg;
				drone.getBattery().setBatteryState(m_sys.voltage_battery / 1000.0,
						m_sys.battery_remaining, m_sys.current_battery / 100.0);
				break;

			case msg_radio.MAVLINK_MSG_ID_RADIO:
				msg_radio m_radio = (msg_radio) msg;
				drone.getRadio().setRadioState(m_radio.rxerrors, m_radio.fixed, m_radio.rssi,
						m_radio.remrssi, m_radio.txbuf, m_radio.noise, m_radio.remnoise);
				return;
				//break;
			case msg_radio_status.MAVLINK_MSG_ID_RADIO_STATUS:
				msg_radio_status m_radio_status = (msg_radio_status) msg;
				drone.getRadio().setRadioState(m_radio_status.rxerrors, m_radio_status.fixed, m_radio_status.rssi,
						m_radio_status.remrssi, m_radio_status.txbuf, m_radio_status.noise, m_radio_status.remnoise);
				return;

			case msg_gps_raw_int.MAVLINK_MSG_ID_GPS_RAW_INT:
				drone.getGps().setGpsState(((msg_gps_raw_int) msg).fix_type,
						((msg_gps_raw_int) msg).satellites_visible, ((msg_gps_raw_int) msg).eph);
				break;

			case msg_rc_channels_raw.MAVLINK_MSG_ID_RC_CHANNELS_RAW:
				drone.getRC().setRcInputValues((msg_rc_channels_raw) msg);
				return;
				//break;

			case msg_servo_output_raw.MAVLINK_MSG_ID_SERVO_OUTPUT_RAW:
				drone.getRC().setRcOutputValues((msg_servo_output_raw) msg);
				break;

			case msg_statustext.MAVLINK_MSG_ID_STATUSTEXT:
				// These are any warnings sent from APM:Copter with
				// gcs_send_text_P()
				// This includes important thing like arm fails, prearm fails, low
				// battery, etc.
				// also less important things like "erasing logs" and
				// "calibrating barometer"
				msg_statustext msg_statustext = (msg_statustext) msg;
				String message = msg_statustext.getText();
				
//				System.err.println("Message status: " + message);
	
				if (msg_statustext.severity == SEVERITY_HIGH || msg_statustext.severity == SEVERITY_CRITICAL) {
					drone.getState().setWarning(message);
					break;
				} else if (message.equals("Low Battery!")) {
					drone.getState().setWarning(message);
					break;
				} else if (message.contains("ArduCopter")) {
					drone.setFirmwareVersion(message);
					break;
				} else if (message.contains("Calibration")) {
					drone.getCalibrateGyroOrientation().setCalibrating(false);
					break;
				}
				
				drone.getMessegeQueue().push(message);
				return;
				//break;
			case msg_camera_feedback.MAVLINK_MSG_ID_CAMERA_FEEDBACK:
				drone.getCameraFootprints().newImageLocation((msg_camera_feedback) msg);
				break;

			case msg_ping.MAVLINK_MSG_ID_PING:
				System.out.println("Got pinged back");
				break;

			case msg_autopilot_version.MAVLINK_MSG_ID_AUTOPILOT_VERSION:
				msg_autopilot_version msg_autopilot = (msg_autopilot_version) msg;
				boolean isMavlink2 = (msg_autopilot.capabilities & MAV_PROTOCOL_CAPABILITY.MAV_PROTOCOL_CAPABILITY_MAVLINK2) == MAV_PROTOCOL_CAPABILITY.MAV_PROTOCOL_CAPABILITY_MAVLINK2;
				//if (isMavlink2)
				//	System.out.println("Protocol Mavlink 2 (orig message of type " + msg_autopilot.version);
				//else
				//	System.out.println("Protocol Mavlink 1 (orig message of type " + msg_autopilot.version);
				break;

			case msg_protocol_version.MAVLINK_MSG_ID_PROTOCOL_VERSION:
				msg_protocol_version msg_protocol = (msg_protocol_version) msg;
				System.out.println("PROT " + msg_protocol);
				break;

			case msg_command_ack.MAVLINK_MSG_ID_COMMAND_ACK:
				msg_command_ack msg_ack = (msg_command_ack) msg;
				System.out.println("Command " + msg_ack.command + " end with " + msg_ack.result);
				break;

			default:
				break;
		}		
	}

	@Override
	public void onDisconnect() {
		LOGGER.debug("Disconnected!");
		//loggerDisplayerSvc.logError("Disconnected!");
	}

	@Override
	public void onComError(String errMsg) {
		LOGGER.error("Communication Error: {}", errMsg);
		logger.LogErrorMessege("Communication Error: " + errMsg);
		drone.getMessegeQueue().push(errMsg);
	}
	
	public void processState(msg_heartbeat msg_heart) {
		checkArmState(msg_heart);
		checkFailsafe(msg_heart);
	}

	private void checkFailsafe(msg_heartbeat msg_heart) {
		boolean failsafe2 = msg_heart.system_status == (byte) MAV_STATE.MAV_STATE_CRITICAL;
		if (failsafe2) {
			drone.getState().setWarning("Failsafe");
//			logger.LogErrorMessege("FailSafe procedure started!");
			LOGGER.error("FailSafe procedure started!");
		}
	}

	private void checkArmState(msg_heartbeat msg_heart) {
		drone.getState()
				.setArmed(
						(msg_heart.base_mode & (byte) MAV_MODE_FLAG.MAV_MODE_FLAG_SAFETY_ARMED) == (byte) MAV_MODE_FLAG.MAV_MODE_FLAG_SAFETY_ARMED);
	}

}
