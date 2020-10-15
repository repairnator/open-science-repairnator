package com.dronegcs.mavlink.is.drone.mission.waypoints;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.*;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

public class MavlinkLand extends SpatialCoordItemDrone {

	public MavlinkLand(DroneMissionItem item) {
		super(item);
		setAltitude(0);
	}

	public MavlinkLand(DroneMission droneMission) {
		this(droneMission,new Coordinate(0,0));
	}

	public MavlinkLand(DroneMission mDroneMission, Coordinate coord) {
		super(mDroneMission, new Coordinate(coord, 0));
	}
	
	public MavlinkLand(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}


	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_LAND;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.LAND;
	}
	
	@Override
	public MavlinkLand clone(DroneMission droneMission) {
		MavlinkLand mavlinkLand = new MavlinkLand(this);
		mavlinkLand.setDroneMission(droneMission);
		return mavlinkLand;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkLand{" +
				super.toString() +
				"}";
	}
}