package com.dronegcs.mavlink.is.drone.mission.waypoints;

import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.*;
import com.dronegcs.mavlink.is.drone.mission.waypoints.interfaces.MavlinkDelayable;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

public class MavlinkWaypoint extends SpatialCoordItemDrone implements MavlinkDelayable {

	private double delay;
	private double acceptanceRadius;
	private double yawAngle;
	private double orbitalRadius;
	private boolean orbitCCW;

	public MavlinkWaypoint(MavlinkWaypoint referenceItem) {
		super(referenceItem);
	}

	public MavlinkWaypoint(DroneMission droneMission, Coordinate coord) {
		super(droneMission, coord);
	}

	public MavlinkWaypoint(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = super.packMissionItem(drone);
		msg_mission_item mavMsg = list.get(0);
		mavMsg.command = MAV_CMD.MAV_CMD_NAV_WAYPOINT;
		mavMsg.param1 = (float) getDelay();
		mavMsg.param2 = (float) getAcceptanceRadius();
		mavMsg.param3 = (float) (isOrbitCCW() ? getOrbitalRadius() * -1.0 : getOrbitalRadius());
		mavMsg.param4 = (float) getYawAngle();
		return list;
	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
		setDelay(mavMsg.param1);
		setAcceptanceRadius(mavMsg.param2);
		setOrbitCCW(mavMsg.param3 < 0);
		setOrbitalRadius(Math.abs(mavMsg.param3));
		setYawAngle(mavMsg.param4);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.WAYPOINT;
	}

	@Override
	public double getDelay() {
		return delay;
	}

	@Override
	public void setDelay(double delay) {
		this.delay = delay;
	}

	public double getAcceptanceRadius() {
		return acceptanceRadius;
	}

	public void setAcceptanceRadius(double acceptanceRadius) {
		this.acceptanceRadius = acceptanceRadius;
	}

	public double getYawAngle() {
		return yawAngle;
	}

	public void setYawAngle(double yawAngle) {
		this.yawAngle = yawAngle;
	}

	public double getOrbitalRadius() {
		return orbitalRadius;
	}

	public void setOrbitalRadius(double orbitalRadius) {
		this.orbitalRadius = orbitalRadius;
	}

	public boolean isOrbitCCW() {
		return orbitCCW;
	}

	public void setOrbitCCW(boolean orbitCCW) {
		this.orbitCCW = orbitCCW;
	}

	@Override
	public MavlinkWaypoint clone(DroneMission droneMission) {
		MavlinkWaypoint mavlinkWaypoint = new MavlinkWaypoint(this);
		mavlinkWaypoint.setDroneMission(droneMission);
		return mavlinkWaypoint;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkWaypoint{" +
				super.toString() +
				"delay=" + delay +
				", acceptanceRadius=" + acceptanceRadius +
				", yawAngle=" + yawAngle +
				", orbitalRadius=" + orbitalRadius +
				", orbitCCW=" + orbitCCW +
				'}';
	}
}