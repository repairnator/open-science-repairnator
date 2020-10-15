package com.dronegcs.mavlink.is.drone.mission.waypoints;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.drone.mission.waypoints.interfaces.MavlinkRadiusable;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

import java.util.List;

public class MavlinkLoiterTurns extends SpatialCoordItemDrone implements MavlinkRadiusable {

	private double radius = 10.0;
	private int turns = 1;

	public MavlinkLoiterTurns(MavlinkLoiterTurns mavlinkLoiterTurns) {
		super(mavlinkLoiterTurns);
		this.radius = mavlinkLoiterTurns.radius;
		this.turns = mavlinkLoiterTurns.turns;
	}

	public MavlinkLoiterTurns(DroneMission droneMission, Coordinate coord) {
		super(droneMission, coord);
	}

	public MavlinkLoiterTurns(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

    public int getTurns() {
        return turns;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = Math.abs(radius);
    }

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
        List<msg_mission_item> list = super.packMissionItem(drone);
        msg_mission_item mavMsg = list.get(0);
        mavMsg.command = MAV_CMD.MAV_CMD_NAV_LOITER_TURNS;
        mavMsg.param1 = Math.abs(turns);
        mavMsg.param3 = (float) radius;
        return list;
	}


	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
		setTurns((int) mavMsg.param1);
		setRadius(mavMsg.param3);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.LOITER_TURNS;
	}

	@Override
	public MavlinkLoiterTurns clone(DroneMission droneMission) {
		MavlinkLoiterTurns mavlinkLoiterTurns = new MavlinkLoiterTurns(this);
		mavlinkLoiterTurns.setDroneMission(droneMission);
		return mavlinkLoiterTurns;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkLoiterTurns{" +
				super.toString() +
				", radius=" + radius +
				", turns=" + turns +
				'}';
	}
}