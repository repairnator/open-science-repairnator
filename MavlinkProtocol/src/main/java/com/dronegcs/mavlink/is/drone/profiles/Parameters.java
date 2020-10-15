package com.dronegcs.mavlink.is.drone.profiles;

import java.util.*;

import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_COPTER;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_I;
import com.generic_tools.Pair.Pair;
import com.generic_tools.logger.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneInterfaces;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.Handler;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.OnDroneListener;
import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_param_value;
import com.dronegcs.mavlink.is.protocol.msgbuilder.MavLinkParameters;

import javax.validation.constraints.NotNull;

/**
 * Class to manage the communication of parameters to the MAV.
 *
 * Should be initialized with a MAVLink Object, so the manager can send messages
 * via the MAV link. The function processMessage must be called with every new
 * MAV Message.
 *
 */
@Component
public class Parameters extends DroneVariable implements OnDroneListener {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Parameters.class);

	public static final int UNINDEX_PARAM = -1;

	private static final int TIMEOUT = 2000; //TALMA original is 1000;

	private int expectedParams;

	private final HashMap<Integer, Parameter> parameters = new HashMap<Integer, Parameter>();

	private Set<DroneInterfaces.OnParameterManagerListener> parameterListeners;

	@Autowired @NotNull(message = "Internal Error: Failed to get handler")
	public Handler handler;

	@Autowired @NotNull(message = "Internal Error: Failed to get logger")
	private Logger logger;

	public Runnable watchdogCallback = () -> onParameterStreamStopped();

	public final ArrayList<Parameter> parameterList = new ArrayList<Parameter>();

	private boolean fetchOnConnect = false;

	static int called;
	public void init() {
		if (called++ > 1)
			throw new RuntimeException("Not a Singleton");
		parameterListeners = new HashSet<DroneInterfaces.OnParameterManagerListener>();
		drone.addDroneListener(this);
	}

	public void refreshParameters() {
		parameters.clear();
		parameterList.clear();

		if (parameterListeners == null) {
			LOGGER.error("Error: There are not listeners signed");
			return;
		}

		for (DroneInterfaces.OnParameterManagerListener parameterListener : parameterListeners)
			parameterListener.onBeginReceivingParameters();

		MavLinkParameters.requestParametersList(drone);
		resetWatchdog();
	}

	public List<Parameter> getParametersList(){
		return parameterList;
	}

	public int getLoadedDownloadedParameters() {
		return parameters.size();
	}

	/**
	 * Try to process a Mavlink message if it is a parameter related message
	 *
	 * @param msg
	 *            Mavlink message to process
	 * @return Returns true if the message has been processed
	 */
	public boolean processMessage(MAVLinkMessage msg) {
		if (msg.msgid == msg_param_value.MAVLINK_MSG_ID_PARAM_VALUE) {
			processReceivedParam((msg_param_value) msg);
			return true;
		}
		return false;
	}

	public List<Parameter> getParametersMetadata() {
		List<Parameter> parameters = new ArrayList<>();
		for (Map.Entry<String, MAV_PARAM_I> val : drone.getVehicleProfile().getParametersMetadataMap().entrySet()) {
			parameters.add(new Parameter(
					val.getKey(),
					val.getValue().getGroup().getName(),
					val.getValue().getDefaultValue(),
					val.getValue().getDefaultValue(),
					val.getValue().getUnit().toString(),
					-1,
					val.getValue().isReadOnly(),
					val.getValue().getOptions(),
					val.getValue().getTitle(),
					val.getValue().getDescription()
			));
		}

		return parameters;
	}

	private void processReceivedParam(msg_param_value m_value) {
		// collect params in parameter list
		MAV_PARAM_I parameterDetail = drone.getVehicleProfile().getParametersMetadataMap().get(m_value.getParam_Id());
		Parameter param = null;
		if (parameterDetail != null) {
			if (parameterDetail.getOptions() != null)
				param = new Parameter(
						m_value.getParam_Id(),
						parameterDetail.getGroup().getName(),
						m_value.param_value,
						parameterDetail.getDefaultValue(),
						parameterDetail.getUnit().toString(),
						m_value.param_type,
						parameterDetail.isReadOnly(),
						parameterDetail.getOptions(),
						parameterDetail.getTitle(),
						parameterDetail.getDescription());
			else
				param = new Parameter(
						m_value.getParam_Id(),
						parameterDetail.getGroup().getName(),
						m_value.param_value,
						parameterDetail.getDefaultValue(),
						parameterDetail.getUnit().toString(),
						m_value.param_type,
						parameterDetail.isReadOnly(),
						parameterDetail.getRange(),
						parameterDetail.getTitle(),
						parameterDetail.getDescription());
			LOGGER.debug("Received parameter update {}", param);
		}
		else {
			param = new Parameter(
					m_value.getParam_Id(),
					"unknown",
					m_value.param_value,
					0,
					"unknown",
					m_value.param_type,
					false,
					"unknown",
					"unknown"
			);
			LOGGER.debug("Received un-known parameter update {}", param);
		}

		if (m_value.param_index == UNINDEX_PARAM) { // Unique value that represent an updated parameter
			Parameter currentParam = getParameter(param.getName());
			if (currentParam == null) {
				LOGGER.error("Unfamiliar Parameter {}", param);
				return;
			}

			currentParam.setValue(param.getValue());
		}
		else {
			parameters.put((int) m_value.param_index, param);
		}

		expectedParams = m_value.param_count;

		// update listener
		if (parameterListeners != null)
			for (DroneInterfaces.OnParameterManagerListener parameterListener : parameterListeners)
				parameterListener.onParameterReceived(param, m_value.param_index, m_value.param_count);

		// Are all parameters here? Notify the listener with the parameters
		if (parameters.size() >= m_value.param_count) {
			LOGGER.debug("All parameters arrived, notify listeners");
			parameterList.clear();
			for (int key : parameters.keySet()) {
				parameterList.add(parameters.get(key));
			}
			killWatchdog();
			logger.LogGeneralMessege("Parameters finished!");

			if (parameterListeners != null) {
				for (DroneInterfaces.OnParameterManagerListener parameterListener : parameterListeners)
					parameterListener.onEndReceivingParameters(parameterList);
			}
		} else {
			resetWatchdog();
		}
		drone.notifyDroneEvent(DroneEventsType.PARAMETER);
	}

	private void reRequestMissingParams(int howManyParams) {
		for (int i = 0; i < howManyParams; i++) {
			if (!parameters.containsKey(i)) {
				MavLinkParameters.readParameter(drone, i);
			}
		}
	}

	public void sendParameter(Parameter parameter) {
		MavLinkParameters.sendParameter(drone, parameter);
	}

	public void ReadParameter(String name) {
		MavLinkParameters.readParameter(drone, name);
	}

	public Parameter getParameter(String name) {
		for (int key : parameters.keySet()) {
			if (parameters.get(key).getName().equalsIgnoreCase(name))
				return parameters.get(key);
		}
		return null;
	}

	public Parameter getLastParameter() {
		if (parameters.size() > 0)
			return parameters.get(parameters.size() - 1);

		return null;
	}

	private void onParameterStreamStopped() {
		reRequestMissingParams(expectedParams);
		resetWatchdog();
	}

	private void resetWatchdog() {
		handler.removeCallbacks(watchdogCallback);
		handler.postDelayed(watchdogCallback, TIMEOUT);
	}

	private void killWatchdog() {
		handler.removeCallbacks(watchdogCallback);
	}

	@Override
	public void onDroneEvent(DroneEventsType event, Drone drone) {
		switch (event) {
			case HEARTBEAT_FIRST:
				if (!drone.getState().isFlying()) {
					LOGGER.debug("First HB Packet");
					//refreshParameters();
				}
				break;
			case DISCONNECTED:
			case HEARTBEAT_TIMEOUT:
				killWatchdog();
				break;
			case PROTOCOL_IDENTIFIED:
				if (this.isFetchOnConnect()) {
					LOGGER.debug("Fetching parameters automatically");
					handler.postDelayed(() -> refreshParameters(), 2000);
				}
				break;
			default:
				break;

		}
	}

	public int getExpectedParameterAmount() {
		return expectedParams;
	}

	public void addParameterListener(DroneInterfaces.OnParameterManagerListener parameterListener) {
		LOGGER.debug("Register listener {} for parameters events", parameterListener.getClass());
		this.parameterListeners.add(parameterListener);
	}

	public int getPercentageComplete() {
		return (int) (((double) (getLoadedDownloadedParameters()) / getExpectedParameterAmount()) * 100);
	}

	public void setAutoFetch(boolean shouldFetch) {
		this.fetchOnConnect = shouldFetch;
	}

	public boolean isFetchOnConnect() {
		return fetchOnConnect;
	}
}