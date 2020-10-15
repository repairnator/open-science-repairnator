package com.dronegcs.mavlink.is.drone.mission.commands;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;

public class MavlinkCameraTrigger extends DroneMissionCMD {

	private double distance = 0;

	public MavlinkCameraTrigger(MavlinkCameraTrigger item) {
		super(item);
		this.distance = item.distance;
	}

	public MavlinkCameraTrigger(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission);
		unpackMAVMessage(msg);
	}

	public MavlinkCameraTrigger(DroneMission droneMission, double triggerDistance) {
		super(droneMission);
		this.distance = triggerDistance;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_DO_SET_CAM_TRIGG_DIST;
		mavMsg.param1 = (float) distance;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		distance = mavMsg.param1;
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.CAMERA_TRIGGER;
	}

	public double getTriggerDistance() {
		return distance;
	}

	public void setTriggerDistance(double triggerDistance) {
		this.distance = triggerDistance;
	}

	@Override
	public MavlinkCameraTrigger clone(DroneMission droneMission) {
		MavlinkCameraTrigger mavlinkCameraTrigger = new MavlinkCameraTrigger(this);
		mavlinkCameraTrigger.setDroneMission(droneMission);
		return mavlinkCameraTrigger;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}
}