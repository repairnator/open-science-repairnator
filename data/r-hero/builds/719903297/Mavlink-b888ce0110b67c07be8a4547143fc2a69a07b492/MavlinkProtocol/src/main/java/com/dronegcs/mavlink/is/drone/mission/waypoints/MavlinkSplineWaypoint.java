package com.dronegcs.mavlink.is.drone.mission.waypoints;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

/**
 * Handle spline waypoint com.dronegcs.mavlink.is.mavlink packet generation.
 */
public class MavlinkSplineWaypoint extends SpatialCoordItemDrone {

	/**
	 * Hold time in decimal seconds. (ignored by fixed wing, time to stay at
	 * MISSION for rotary wing)
	 */
	private double delay;

	public MavlinkSplineWaypoint(MavlinkSplineWaypoint mavlinkSplineWaypoint) {
		super(mavlinkSplineWaypoint);
		this.delay = mavlinkSplineWaypoint.delay;
	}

	public MavlinkSplineWaypoint(DroneMission droneMission, Coordinate coord) {
		super(droneMission, coord);
	}

	public MavlinkSplineWaypoint(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_SPLINE_WAYPOINT;
		mavMsg.param1 = (float) delay;
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
		setDelay(mavMsg.param1);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.SPLINE_WAYPOINT;
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}
	
	@Override
	public MavlinkSplineWaypoint clone(DroneMission droneMission) {
		MavlinkSplineWaypoint mavlinkSplineWaypoint = new MavlinkSplineWaypoint(this);
		mavlinkSplineWaypoint.setDroneMission(droneMission);
		return mavlinkSplineWaypoint;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkSplineWaypoint{" +
				super.toString() +
				", delay=" + delay +
				'}';
	}
}
