package com.dronegcs.mavlink.is.drone.mission;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.DroneVariable;
import com.dronegcs.mavlink.is.drone.DroneInterfaces.DroneEventsType;
import com.dronegcs.mavlink.is.drone.mission.commands.*;
import com.dronegcs.mavlink.is.drone.mission.commands.MavlinkTakeoff;
import com.dronegcs.mavlink.is.drone.mission.waypoints.*;
import com.dronegcs.mavlink.is.drone.mission.waypoints.MavlinkWaypoint;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_ack;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.dronegcs.mavlink.is.units.Speed;
import com.generic_tools.logger.Logger;
import com.generic_tools.Pair.Pair;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * This implements a com.dronegcs.mavlink.is.mavlink droneMission. A com.dronegcs.mavlink.is.mavlink droneMission is a set of
 * commands/droneMission items to be carried out by the drone. TODO: rename the
 * 'waypoint' method to 'missionItem' (i.e: addMissionItem)
 */

@Scope("prototype")
@Component
public class DroneMission extends DroneVariable implements Serializable {

	private static final long serialVersionUID = 8399081979944818494L;

	@Autowired @NotNull(message = "Internal Error: Failed to get application context")
	private ApplicationContext applicationContext;

	@Autowired @NotNull(message = "Internal Error: Failed to get com.dronegcs.gcsis.logger")
	private Logger logger;

	/**
	 * Stores the set of droneMission items belonging to this droneMission.
	 */
	private List<DroneMissionItem> items = new ArrayList<DroneMissionItem>();
	private double defaultAlt = 100.0;

	public DroneMission(){
		super();
	}

	public DroneMission(DroneMission droneMission) {
		super(droneMission);
		defaultAlt = droneMission.getDefaultAlt();
		for (DroneMissionItem mi : droneMission.getItems()) items.add((DroneMissionItem) mi.clone(this));
	}

	/**
	 * @return the droneMission's default altitude
	 */
	public double getDefaultAlt() {
		return defaultAlt;
	}

	/**
	 * Sets the droneMission default altitude.
	 * 
	 * @param newAltitude value
	 */
	public void setDefaultAlt(double newAltitude) {
		defaultAlt = newAltitude;
	}

	/**
	 * Removes a waypoint from the droneMission's set of droneMission items.
	 * 
	 * @param item
	 *            waypoint to remove
	 */
	public void removeWaypoint(DroneMissionItem item) {
		items.remove(item);
		notifyMissionUpdate();
	}

	/**
	 * Removes a list of waypoints from the droneMission's set of droneMission items.
	 * 
	 * @param toRemove
	 *            list of waypoints to remove
	 */
	public void removeWaypoints(List<DroneMissionItem> toRemove) {
		items.removeAll(toRemove);
		notifyMissionUpdate();
	}

	/**
	 * Add a list of waypoints to the droneMission's set of droneMission items.
	 * 
	 * @param droneMissionItems
	 *            list of waypoints to add
	 */
	public void addMissionItems(List<DroneMissionItem> droneMissionItems) {
		items.addAll(droneMissionItems);
		notifyMissionUpdate();
	}

	/**
	 * Add a waypoint to the droneMission's set of droneMission item.
	 * 
	 * @param droneMissionItem
	 *            waypoint to add
	 */
	public void addMissionItem(DroneMissionItem droneMissionItem) {
		items.add(droneMissionItem);
		notifyMissionUpdate();
	}

	public void addMissionItem(int index, DroneMissionItem droneMissionItem) {
		items.add(index, droneMissionItem);
		notifyMissionUpdate();
	}

	/**
	 * Signals that this droneMission object was updated. //TODO: maybe move outside
	 * of this class
	 */
	public void notifyMissionUpdate() {
		drone.notifyDroneEvent(DroneEventsType.MISSION_UPDATE);
	}

	/**
	 * @return the altitude of the last added droneMission item.
	 */
	public double getLastAltitude() {
		double alt;
		try {
			SpatialCoordItemDrone lastItem = (SpatialCoordItemDrone) items.get(items.size() - 1);
			alt = lastItem.getCoordinate().getAltitude();
		} catch (Exception e) {
			alt = defaultAlt;
		}
		return alt;
	}

	/**
	 * Updates a droneMission item
	 * 
	 * @param oldItem
	 *            droneMission item to update
	 * @param newItem
	 *            new droneMission item
	 */
	public void replace(DroneMissionItem oldItem, DroneMissionItem newItem) {
		final int index = items.indexOf(oldItem);
		if (index == -1) {
			return;
		}

		items.remove(index);
		items.add(index, newItem);
		notifyMissionUpdate();
	}

	public void replaceAll(List<Pair<DroneMissionItem, DroneMissionItem>> updatesList) {
		if (updatesList == null || updatesList.isEmpty()) {
			return;
		}

		boolean wasUpdated = false;
		for (Pair<DroneMissionItem, DroneMissionItem> updatePair : updatesList) {
			final DroneMissionItem oldItem = updatePair.getFirst();
			final int index = items.indexOf(oldItem);
			if (index == -1) {
				continue;
			}

			final DroneMissionItem newItem = updatePair.getSecond();
			items.remove(index);
			items.add(index, newItem);

			wasUpdated = true;
		}

		if (wasUpdated) {
			notifyMissionUpdate();
		}
	}

	/**
	 * Reverse the order of the droneMission items.
	 */
	public void reverse() {
		Collections.reverse(items);
		notifyMissionUpdate();
	}

	public void onWriteWaypoints(msg_mission_ack msg) {
		drone.notifyDroneEvent(DroneEventsType.MISSION_SENT);
	}

	public List<DroneMissionItem> getItems() {
		return items;
	}

	public int getOrder(DroneMissionItem waypoint) {
		return items.indexOf(waypoint) + 1; // plus one to account for the fact
											// that this is an index
	}

	public double getAltitudeDiffFromPreviousItem(SpatialCoordItemDrone waypoint)
			throws IllegalArgumentException {
		int i = items.indexOf(waypoint);
		if (i > 0) {
			DroneMissionItem previous = items.get(i - 1);
			if (previous instanceof SpatialCoordItemDrone) {
				double aa = ((SpatialCoordItemDrone) previous).getCoordinate().getAltitude();
				return waypoint.getCoordinate().getAltitude() - aa;
			}
		}
		throw new IllegalArgumentException("Last waypoint doesn't have an altitude");
	}

	public double getDistanceFromLastWaypoint(SpatialCoordItemDrone waypoint)
			throws IllegalArgumentException {
		int i = items.indexOf(waypoint);
		if (i > 0) {
			DroneMissionItem previous = items.get(i - 1);
			if (previous instanceof SpatialCoordItemDrone) {
				return GeoTools.getDistance(waypoint.getCoordinate(),
						((SpatialCoordItemDrone) previous).getCoordinate());
			}
		}
		throw new IllegalArgumentException("Last waypoint doesn't have a coordinate");
	}

	public boolean hasItem(DroneMissionItem item) {
		return items.contains(item);
	}

	public void onMissionReceived(List<msg_mission_item> msgs) {
		if (msgs != null) {
			drone.getHome().setHome(msgs.get(0));
			msgs.remove(0); // Remove Home waypoint
			items.clear();
			items.addAll(processMavLinkMessages(msgs));
			drone.notifyDroneEvent(DroneEventsType.MISSION_RECEIVED);
			notifyMissionUpdate();
		}
	}

	public void onMissionLoaded(List<msg_mission_item> msgs) {
		if (msgs != null) {
			drone.getHome().setHome(msgs.get(0));
			msgs.remove(0); // Remove Home waypoint
			items.clear();
			items.addAll(processMavLinkMessages(msgs));
			drone.notifyDroneEvent(DroneEventsType.MISSION_RECEIVED);
			notifyMissionUpdate();
		}
	}

	private List<DroneMissionItem> processMavLinkMessages(List<msg_mission_item> msgs) {
		List<DroneMissionItem> received = new ArrayList<DroneMissionItem>();

		for (msg_mission_item msg : msgs) {
			switch (msg.command) {
			case MAV_CMD.MAV_CMD_NAV_WAYPOINT:
				received.add(new MavlinkWaypoint(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_SPLINE_WAYPOINT:
				received.add(new MavlinkSplineWaypoint(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_LAND:
				received.add(new MavlinkLand(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_TAKEOFF:
				received.add(new MavlinkTakeoff(msg, this));
				break;
			case MAV_CMD.MAV_CMD_DO_CHANGE_SPEED:
				received.add(new MavlinkChangeSpeed(msg, this));
				break;
			case MAV_CMD.MAV_CMD_DO_SET_CAM_TRIGG_DIST:
				received.add(new MavlinkCameraTrigger(msg,this));
				break;
			case MavlinkEpmGripper.MAV_CMD_DO_GRIPPER:
				received.add(new MavlinkEpmGripper(msg, this));
				break;
			case MAV_CMD.MAV_CMD_DO_SET_ROI:
				received.add(new MavlinkRegionOfInterest(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_RETURN_TO_LAUNCH:
				received.add(new MavlinkReturnToHome(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_LOITER_UNLIM:
				received.add(new MavlinkLoiterUnlimited(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_LOITER_TURNS:
				received.add(new MavlinkLoiterTurns(msg, this));
				break;
			case MAV_CMD.MAV_CMD_NAV_LOITER_TIME:
				received.add(new MavlinkLoiterTime(msg, this));
				break;
			default:
				logger.LogAlertMessage("Found unexpected mission item: " + msg);
				break;
			}
		}
		return received;
	}

	/**
	 * Sends the droneMission to the drone using the com.dronegcs.mavlink.is.mavlink protocol.
	 */
	public void sendMissionToAPM() {
		drone.getWaypointManager().writeWaypoints(getMsgMissionItems());
	}

	public List<msg_mission_item> getMsgMissionItems() {
		final List<msg_mission_item> data = new ArrayList<msg_mission_item>();
		data.add(drone.getHome().packMavlink()); //TALMA automatically add go home at the begging
		for (DroneMissionItem item : items) {
			data.addAll(item.packMissionItem(drone));
		}
		return data;
	}

	/**
	 * Create and upload a dronie droneMission to the drone
	 * 
	 * @return the bearing in degrees the drone trajectory will take.
	 */
	public double makeAndUploadDronie() {
		Coordinate currentPosition = drone.getGps().getPosition();
		if (currentPosition == null || drone.getGps().getSatCount() <= 5) {
			drone.notifyDroneEvent(DroneEventsType.WARNING_NO_GPS);
			return -1;
		}

		final double bearing = 180 + drone.getOrientation().getYaw();
		items.clear();
		items.addAll(createDronie(currentPosition,
				GeoTools.newCoordFromBearingAndDistance(currentPosition, bearing, 50.0)));
		sendMissionToAPM();
		notifyMissionUpdate();

		return bearing;
	}

	public List<DroneMissionItem> createDronie(Coordinate start, Coordinate end) {
		final int startAltitude = 4;
		final int roiDistance = -8;
		Coordinate slowDownPoint = GeoTools.pointAlongTheLine(start, end, 5);

		Speed defaultSpeed = drone.getSpeed().getSpeedParameter();
		if (defaultSpeed == null) {
			defaultSpeed = new Speed(5);
		}

		List<DroneMissionItem> dronieItems = new ArrayList<DroneMissionItem>();
		
		dronieItems.add(new MavlinkTakeoff(this, 1.0 * startAltitude));

		dronieItems.add(new MavlinkChangeSpeed(this, new Speed(1.0)));
		dronieItems.add(new MavlinkChangeSpeed(this, new Speed(2.0)));
		dronieItems.add(new MavlinkRegionOfInterest(this, new Coordinate(GeoTools.pointAlongTheLine(start, end, roiDistance), 1)));
		dronieItems.add(new MavlinkWaypoint(this, new Coordinate(end, startAltitude + GeoTools.getDistance(start, end)/ 2.0)));
		dronieItems.add(new MavlinkWaypoint(this, new Coordinate(slowDownPoint, startAltitude + GeoTools.getDistance(start, slowDownPoint) / 2.0)));
		dronieItems.add(new MavlinkChangeSpeed(this, new Speed(1.0)));
		dronieItems.add(new MavlinkWaypoint(this, new Coordinate(start, startAltitude)));
		dronieItems.add(new MavlinkChangeSpeed(this, defaultSpeed));

		dronieItems.add(new MavlinkLand(this, start));
		return dronieItems;
	}

	public DroneMission duplicate() {
		//DroneMission ans = new DroneMission();
		DroneMission ans = applicationContext.getBean(DroneMission.class);
		ans.setDrone(drone);
		Iterator<DroneMissionItem> it = this.items.iterator();
		while (it.hasNext()) {
			ans.addMissionItem(it.next());
		}
		return ans;
	}

	public Drone getDrone() {
		return drone;
	}
	
	public boolean equals(DroneMission droneMission) {
		if (droneMission == null)
			return false;
		
		return droneMission.getItems().equals(this.getItems());
	}
}
