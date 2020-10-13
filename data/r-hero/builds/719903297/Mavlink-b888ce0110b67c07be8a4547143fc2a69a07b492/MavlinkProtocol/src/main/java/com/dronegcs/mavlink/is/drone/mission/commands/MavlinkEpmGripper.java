package com.dronegcs.mavlink.is.drone.mission.commands;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;

public class MavlinkEpmGripper extends DroneMissionCMD {

	// TODO Update com.dronegcs.mavlink.is.mavlink and use the correct enum here
	public final static short MAV_CMD_DO_GRIPPER = 211;
	public final static int GRIPPER_ACTION_RELEASE = 0;
	public final static int GRIPPER_ACTION_GRAB = 1;

	private boolean release = true;

	public MavlinkEpmGripper(MavlinkEpmGripper item) {
		super(item);
		this.release = item.release;
	}

	public MavlinkEpmGripper(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission);
		unpackMAVMessage(msg);
	}

	public MavlinkEpmGripper(DroneMission droneMission, boolean release) {
		super(droneMission);
		this.release = release;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD_DO_GRIPPER;
		mavMsg.param2 = release ? GRIPPER_ACTION_RELEASE : GRIPPER_ACTION_GRAB;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		if (mavMsg.param2 == GRIPPER_ACTION_GRAB) {
			release = false;
		} else if (mavMsg.param2 == GRIPPER_ACTION_RELEASE) {
			release = true;
		}
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.EPM_GRIPPER;
	}

	public boolean isRelease() {
		return release;
	}

	public void setAsRelease(boolean release) {
		this.release = release;
	}

	@Override
	public MavlinkEpmGripper clone(DroneMission droneMission) {
		MavlinkEpmGripper mavlinkEpmGripper = new MavlinkEpmGripper(this);
		mavlinkEpmGripper.setDroneMission(droneMission);
		return mavlinkEpmGripper;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}
}