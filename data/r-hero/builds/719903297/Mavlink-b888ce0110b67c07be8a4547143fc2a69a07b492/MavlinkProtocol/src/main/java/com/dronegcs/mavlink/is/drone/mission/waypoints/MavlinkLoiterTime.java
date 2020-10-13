package com.dronegcs.mavlink.is.drone.mission.waypoints;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;

import java.util.List;

public class MavlinkLoiterTime extends SpatialCoordItemDrone {

    private int seconds = 1;

	public MavlinkLoiterTime(MavlinkLoiterTime mavlinkLoiterTime) {
		super(mavlinkLoiterTime);
		this.seconds = mavlinkLoiterTime.seconds;
	}

	public MavlinkLoiterTime(DroneMission droneMission, Coordinate coord) {
		super(droneMission, coord);
	}

	public MavlinkLoiterTime(msg_mission_item msg, DroneMission droneMission) {
		super(droneMission, null);
		unpackMAVMessage(msg);
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getSeconds() {
		return seconds;
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
        List<msg_mission_item> list = super.packMissionItem(drone);
        msg_mission_item mavMsg = list.get(0);
        mavMsg.command = MAV_CMD.MAV_CMD_NAV_LOITER_TIME;
        mavMsg.param1 = seconds;
        return list;
	}


	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
		super.unpackMAVMessage(mavMsg);
		setSeconds((int) mavMsg.param1);
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.LOITER_TIME;
	}

	@Override
	public MavlinkLoiterTime clone(DroneMission droneMission) {
		MavlinkLoiterTime mavlinkLoiterTime = new MavlinkLoiterTime(this);
		mavlinkLoiterTime.setDroneMission(droneMission);
		return mavlinkLoiterTime;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

    @Override
    public String toString() {
        return "MavlinkLoiterTime{" +
                super.toString() +
                ", seconds=" + seconds +
                '}';
    }

}