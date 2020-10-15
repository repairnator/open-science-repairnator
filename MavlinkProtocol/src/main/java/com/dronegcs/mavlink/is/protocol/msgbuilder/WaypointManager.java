package com.dronegcs.mavlink.is.protocol.msgbuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import com.generic_tools.logger.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.OnTimeout;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.OnWaypointManagerListener;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_ack;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_count;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_current;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item_reached;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_request;

/**
 * Class to manage the communication of waypoints to the MAV.
 * 
 * Should be initialized with a MAVLink Object, so the manager can send messages
 * via the MAV link. The function processMessage must be called with every new
 * MAV Message.
 * 
 */
@Component
public class WaypointManager extends DroneVariable implements OnTimeout
{
	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WaypointManager.class);

	@Autowired @NotNull(message = "Internal Error: Failed to get com.dronegcs.gcsis.logger")
	private Logger logger;

	public static final int RETRIES_AMOUNT = 3;

	enum WaypointStates {
		IDLE, READ_REQUEST, READING_WP, WRITING_WP_COUNT, WRITING_WP, WAITING_WRITE_ACK
	}

	public enum WaypointEvent_Type {
		WP_UPLOAD, WP_DOWNLOAD, WP_RETRY, WP_CONTINUE, WP_TIMED_OUT
	}

	private int readIndex;
	private int writeIndex;
	private int retryIndex;
	private TimeOut timeOut;
	private Set<OnWaypointManagerListener> wpEventListeners;

	private WaypointStates state = WaypointStates.IDLE;

	public WaypointManager() {
		this.timeOut = new TimeOut(this);
	}

	public WaypointManager(TimeOut timeOut) {
		this.timeOut = timeOut;
	}
	
	private static int called;    
	@PostConstruct
	private void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		
		wpEventListeners = new HashSet<>();
	}

	public void addWaypointManagerListener(OnWaypointManagerListener wpEventListener) {
		LOGGER.debug("Register listener {} for waypoint manager", wpEventListener.getClass());
		this.wpEventListeners.add(wpEventListener);
	}

	/**
	 * Try to receive all waypoints from the MAV.
	 * 
	 * If all runs well the callback will return the list of waypoints.
	 */
	public void getWaypoints() {
		// ensure that WPManager is not doing anything else
		if (state != WaypointStates.IDLE)
			return;

		doBeginWaypointEvent(WaypointEvent_Type.WP_DOWNLOAD);
		readIndex = -1;
		timeOut.setTimeOutValue(3000);
		//timeOut.setTimeOutValue(10000); // TALMA
		timeOut.setTimeOutRetry(RETRIES_AMOUNT);
		state = WaypointStates.READ_REQUEST;
		timeOut.setTimeOut();
		logger.LogOutgoingMessage("Sending Sync MavlinkWaypoint request");
		MavLinkWaypoint.requestWaypointsList(drone);
	}

	/**
	 * Write a list of waypoints to the MAV.
	 * 
	 * The callback will return the status of this operation
	 * 
	 * @param data
	 *            waypoints to be written
	 */

	public void writeWaypoints(List<msg_mission_item> data) {
		// ensure that WPManager is not doing anything else
		if (state != WaypointStates.IDLE) {
			LOGGER.error("Waypoints manager is busy");
			return;
		}

		if (mission != null) {
			doBeginWaypointEvent(WaypointEvent_Type.WP_UPLOAD);
			updateMsgIndexes(data);
			mission.clear();
			mission.addAll(data);
			writeIndex = 0;
			timeOut.setTimeOutValue(6000); // TALMA
			//timeOut.setTimeOutValue(3000);
			timeOut.setTimeOutRetry(3);
			state = WaypointStates.WRITING_WP_COUNT;
			timeOut.setTimeOut();
			MavLinkWaypoint.sendWaypointCount(drone, mission.size());
		}
	}

	private void updateMsgIndexes(List<msg_mission_item> data) {
		short index = 0;
		for (msg_mission_item msg : data) {
			msg.seq = index++;
		}
	}

	/**
	 * Sets the current waypoint in the MAV
	 * 
	 * The callback will return the status of this operation
	 */
	public void setCurrentWaypoint(int i) {
		if ((mission != null)) {
			MavLinkWaypoint.sendSetCurrentWaypoint(drone, (short) i);
		}
	}

	/**
	 * Callback for when a waypoint has been reached
	 * 
	 * @param wpNumber
	 *            number of the completed waypoint
	 */
	public void onWaypointReached(int wpNumber) {
	}

	/**
	 * Callback for a change in the current waypoint the MAV is heading for
	 * 
	 * @param seq
	 *            number of the updated waypoint
	 */
	private void onCurrentWaypointUpdate(short seq) {
	}

	/**
	 * number of waypoints to be received, used when reading waypoints
	 */
	private short waypointCount;
	/**
	 * list of waypoints used when writing or receiving
	 */
	private List<msg_mission_item> mission = new ArrayList<msg_mission_item>();

	/**
	 * Try to process a Mavlink message if it is a droneMission related message
	 * 
	 * @param msg
	 *            Mavlink message to process
	 * @return Returns true if the message has been processed
	 */
	public boolean processMessage(MAVLinkMessage msg) {
		switch (state) {
		default:
		case IDLE:
			break;
		case READ_REQUEST:
			if (msg.msgid == msg_mission_count.MAVLINK_MSG_ID_MISSION_COUNT) {
				waypointCount = ((msg_mission_count) msg).count;
				mission.clear();
				timeOut.setTimeOut();
				MavLinkWaypoint.requestWayPoint(drone, mission.size());
				state = WaypointStates.READING_WP;
				return true;
			}
			break;
		case READING_WP:
			if (msg.msgid == msg_mission_item.MAVLINK_MSG_ID_MISSION_ITEM) {
				timeOut.setTimeOut();
				processReceivedWaypoint((msg_mission_item) msg);
				logger.LogIncomingMessage("Got point #" + (readIndex + 1));
				System.out.println(getClass() + " | MissionItem => " + msg);
				doWaypointEvent(WaypointEvent_Type.WP_DOWNLOAD, readIndex + 1, waypointCount);
				if (mission.size() < waypointCount) {
					MavLinkWaypoint.requestWayPoint(drone, mission.size());
				} else {
					timeOut.resetTimeOut();
					state = WaypointStates.IDLE;
					MavLinkWaypoint.sendAck(drone);
					drone.getDroneMission().onMissionReceived(mission);
					doEndWaypointEvent(WaypointEvent_Type.WP_DOWNLOAD);
				}
				return true;
			}
			break;
		case WRITING_WP_COUNT:
			state = WaypointStates.WRITING_WP;
		case WRITING_WP:
			if (msg.msgid == msg_mission_request.MAVLINK_MSG_ID_MISSION_REQUEST) {
				timeOut.setTimeOut();
				processWaypointToSend((msg_mission_request) msg);
				doWaypointEvent(WaypointEvent_Type.WP_UPLOAD, writeIndex + 1, mission.size());
				return true;
			}
			break;
		case WAITING_WRITE_ACK:
			if (msg.msgid == msg_mission_ack.MAVLINK_MSG_ID_MISSION_ACK) {
				timeOut.resetTimeOut();
				drone.getDroneMission().onWriteWaypoints((msg_mission_ack) msg);
				state = WaypointStates.IDLE;
				doEndWaypointEvent(WaypointEvent_Type.WP_UPLOAD);
				return true;
			}
			break;
		}

		if (msg.msgid == msg_mission_item_reached.MAVLINK_MSG_ID_MISSION_ITEM_REACHED) {
			onWaypointReached(((msg_mission_item_reached) msg).seq);
			return true;
		}
		if (msg.msgid == msg_mission_current.MAVLINK_MSG_ID_MISSION_CURRENT) {
			onCurrentWaypointUpdate(((msg_mission_current) msg).seq);
			return true;
		}
		return false;
	}

	@Override
	public void notifyTimeOut(int timeOutCount) {
		processTimeOut(timeOutCount);
	}

	public boolean processTimeOut(int mTimeOutCount) {

		// If max retry is reached, set state to IDLE. No more retry.
		if (mTimeOutCount >= timeOut.getTimeOutRetry()) {
			state = WaypointStates.IDLE;
			doWaypointEvent(WaypointEvent_Type.WP_TIMED_OUT, retryIndex, RETRIES_AMOUNT);
			return false;
		}

		retryIndex++;
		doWaypointEvent(WaypointEvent_Type.WP_RETRY, retryIndex, RETRIES_AMOUNT);

		timeOut.setTimeOut(false);

		switch (state) {
		default:
		case IDLE:
			break;
		case READ_REQUEST:
			MavLinkWaypoint.requestWaypointsList(drone);
			break;
		case READING_WP:
			if (mission.size() < waypointCount) { // request last lost WP
				MavLinkWaypoint.requestWayPoint(drone, mission.size());
			}
			break;
		case WRITING_WP_COUNT:
			MavLinkWaypoint.sendWaypointCount(drone, mission.size());
			break;
		case WRITING_WP:
			// Log.d("TIMEOUT", "re Write Msg: " + String.valueOf(writeIndex));
			if (writeIndex < mission.size()) {
				drone.getMavClient().sendMavPacket(mission.get(writeIndex).pack());
			}
			break;
		case WAITING_WRITE_ACK:
			drone.getMavClient().sendMavPacket(mission.get(mission.size() - 1).pack());
			break;
		}

		return true;
	}

	private void processWaypointToSend(msg_mission_request msg) {
		/*
		 * Log.d("TIMEOUT", "Write Msg: " + String.valueOf(msg.seq));
		 */
		writeIndex = msg.seq;
		drone.getMavClient().sendMavPacket(mission.get(writeIndex).pack());

		if (writeIndex + 1 >= mission.size()) {
			state = WaypointStates.WAITING_WRITE_ACK;
		}
	}

	private void processReceivedWaypoint(msg_mission_item msg) {
		/*
		 * Log.d("TIMEOUT", "Read Last/Curr: " + String.valueOf(readIndex) + "/"
		 * + String.valueOf(msg.seq));
		 */
		// in case of we receive the same WP again after retry
		if (msg.seq <= readIndex)
			return;

		readIndex = msg.seq;

		mission.add(msg);
	}

	private void doBeginWaypointEvent(WaypointEvent_Type wpEvent) {
		retryIndex = 0;

		if (wpEventListeners == null)
			return;

		for (OnWaypointManagerListener wpEventListener : wpEventListeners)
			wpEventListener.onBeginWaypointEvent(wpEvent);
	}

	private void doEndWaypointEvent(WaypointEvent_Type wpEvent) {
		if (retryIndex > 0)// if retry successful, notify that we now continue
			doWaypointEvent(WaypointEvent_Type.WP_CONTINUE, retryIndex, RETRIES_AMOUNT);

		retryIndex = 0;

		if (wpEventListeners == null)
			return;
		
		for (OnWaypointManagerListener wpEventListener : wpEventListeners)
			wpEventListener.onEndWaypointEvent(wpEvent);
	}

	private void doWaypointEvent(WaypointEvent_Type wpEvent, int index, int count) {
		retryIndex = 0;

		if (wpEventListeners == null)
			return;

		for (OnWaypointManagerListener wpEventListener : wpEventListeners)
			wpEventListener.onWaypointEvent(wpEvent, index, count);
	}

	private class TimeOut {

		private Timer timeOutTimer;
		private int timeOutCount;
		private long timeOut;
		private int timeOutRetry;
		private OnTimeout listener;

		public TimeOut(OnTimeout listener) {
			this.listener = listener;
		}

		public void setTimeOutValue(long timeout_ms) {
			this.timeOut = timeout_ms;
		}

		public void setTimeOutRetry(int timeout_retry) {
			this.timeOutRetry = timeout_retry;
		}

		public int getTimeOutRetry() {
			if (this.timeOutRetry <= 0)
				return RETRIES_AMOUNT; // default value

			return this.timeOutRetry;
		}

		public synchronized void resetTimeOut() {
			if (timeOutTimer != null) {
				timeOutTimer.cancel();
				timeOutTimer = null;
				/*
				 * Log.d("TIMEOUT", "reset " + String.valueOf(timeOutTimer));
				 */
			}
		}

		public void setTimeOut() {
			setTimeOut(this.timeOut, true);
		}

		public void setTimeOut(boolean resetTimeOutCount) {
			setTimeOut(this.timeOut, resetTimeOutCount);
		}

		public synchronized void setTimeOut(long timeout_ms, boolean resetTimeOutCount) {
			/*
			 * Log.d("TIMEOUT", "set " + String.valueOf(timeout_ms));
			 */
			resetTimeOut();
			if (resetTimeOutCount)
				timeOutCount = 0;

			if (timeOutTimer == null) {
				timeOutTimer = new Timer();
				timeOutTimer.schedule(new TimerTask() {
					@Override
					public void run() {
						if (timeOutTimer != null) {
							resetTimeOut();
							timeOutCount++;

							/*
							 * Log.d("TIMEOUT", "timed out");
							 */

							listener.notifyTimeOut(timeOutCount);
						}
					}
				}, timeout_ms); // delay in milliseconds
			}
		}

	}
}
