package com.dronegcs.mavlink.is.drone.mission.commands;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_FRAME;
import com.dronegcs.mavlink.is.units.Speed;

public class MavlinkChangeSpeed extends DroneMissionCMD {

	private Speed speed = new Speed(5);

	public MavlinkChangeSpeed(MavlinkChangeSpeed item) {
		super(item);
		this.speed = new Speed(item.speed);
	}

	public MavlinkChangeSpeed(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission);
		unpackMAVMessage(msg);
	}

	public MavlinkChangeSpeed(DroneMission droneMission, Speed speed) {
		super(droneMission);
		this.speed = speed;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_DO_CHANGE_SPEED;
		mavMsg.frame = MAV_FRAME.MAV_FRAME_GLOBAL_RELATIVE_ALT;
		mavMsg.param2 = (float) speed.valueInMetersPerSecond();
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		speed = new Speed(mavMsg.param2);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.CHANGE_SPEED;
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	@Override
	public MavlinkChangeSpeed clone(DroneMission droneMission) {
		MavlinkChangeSpeed mavlinkChangeSpeed = new MavlinkChangeSpeed(this);
		mavlinkChangeSpeed.setDroneMission(droneMission);
		return mavlinkChangeSpeed;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}
}