package com.dronegcs.mavlink.is.drone.mission.waypoints;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.drone.mission.waypoints.interfaces.MavlinkDelayable;
import com.dronegcs.mavlink.is.drone.mission.waypoints.interfaces.MavlinkRadiusable;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

import java.util.List;

public class MavlinkLoiterUnlimited extends SpatialCoordItemDrone {

	public MavlinkLoiterUnlimited(MavlinkLoiterUnlimited referenceItem) {
		super(referenceItem);
	}

	public MavlinkLoiterUnlimited(DroneMission droneMission, Coordinate coord) {
		super(droneMission, coord);
	}

	public MavlinkLoiterUnlimited(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_LOITER_UNLIM;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.LOITER_UNLIMITED;
	}

	@Override
	public MavlinkLoiterUnlimited clone(DroneMission droneMission) {
		MavlinkLoiterUnlimited mavlinkWaypoint = new MavlinkLoiterUnlimited(this);
		mavlinkWaypoint.setDroneMission(droneMission);
		return mavlinkWaypoint;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkLoiterUnlimited{" +
				super.toString() +
				"}";
	}
}