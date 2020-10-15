package com.dronegcs.mavlink.is.drone.variables;

import com.dronegcs.mavlink.is.drone.parameters.Parameter;
import org.springframework.stereotype.Component;

import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_rc_channels_raw;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_servo_output_raw;

@Component
public class RC extends DroneVariable {

	public int in[] = new int[8];
	public int out[] = new int[8];

	private int inMin[] = new int[8];
	private int inMax[] = new int[8];
	private int inTrim[] = new int[8];

	public void setRcInputValues(msg_rc_channels_raw msg) {
		in[0] = msg.chan1_raw;
		in[1] = msg.chan2_raw;
		in[2] = msg.chan3_raw;
		in[3] = msg.chan4_raw;
		in[4] = msg.chan5_raw;
		in[5] = msg.chan6_raw;
		in[6] = msg.chan7_raw;
		in[7] = msg.chan8_raw;
		drone.notifyDroneEvent(DroneEventsType.RC_IN);
	}

	public void setRcOutputValues(msg_servo_output_raw msg) {
		out[0] = msg.servo1_raw;
		out[1] = msg.servo2_raw;
		out[2] = msg.servo3_raw;
		out[3] = msg.servo4_raw;
		out[4] = msg.servo5_raw;
		out[5] = msg.servo6_raw;
		out[6] = msg.servo7_raw;
		out[7] = msg.servo8_raw;
		drone.notifyDroneEvent(DroneEventsType.RC_OUT);
	}
	
	public int getAverageThrust() {
		int e1 = out[0];
		int e2 = out[1];
		int e3 = out[2];
		int e4 = out[3];
		
		int eAvg = (e1 + e2 + e3 + e4) / 4;
		
		return eAvg;
	}

	public int getThrustOut() {
		return out[2];
	}
	
	public int getThrustIn() {
		return in[2];
	}

	public int[] getInMin() {
		for (int i = 0; i < inMin.length ; i++) {
			Parameter param = drone.getParameters().getParameter("RC" + (i + 1) + "_MIN");
			inMin[i] = param.getValue().intValue();
		}
		return inMin;
	}

	public int[] getInMax() {
		for (int i = 0; i < inMax.length ; i++) {
			Parameter param = drone.getParameters().getParameter("RC" + (i + 1) + "_MAX");
			inMax[i] = param.getValue().intValue();
		}
		return inMax;
	}

	public int[] getInTrim() {
		for (int i = 0; i < inTrim.length ; i++) {
			Parameter param = drone.getParameters().getParameter("RC" + (i + 1) + "_TRIM");
			inTrim[i] = param.getValue().intValue();
		}
		return inTrim;
	}

}
