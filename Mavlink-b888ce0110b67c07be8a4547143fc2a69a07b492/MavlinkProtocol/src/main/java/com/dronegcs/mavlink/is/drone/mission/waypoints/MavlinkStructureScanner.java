package com.dronegcs.mavlink.is.drone.mission.waypoints;

import java.util.ArrayList;
import java.util.List;

import com.dronegcs.mavlink.is.drone.Drone;
import com.dronegcs.mavlink.is.drone.mission.ConvertMavlinkVisitor;
import com.dronegcs.mavlink.is.drone.mission.DroneMission;
import com.dronegcs.mavlink.is.drone.mission.MavlinkConvertionException;
import com.dronegcs.mavlink.is.drone.mission.survey.MavlinkSurvey;
import com.dronegcs.mavlink.is.drone.mission.MissionItemType;
import com.dronegcs.mavlink.is.drone.mission.survey.CameraInfo;
import com.dronegcs.mavlink.is.drone.mission.survey.SurveyData;
import com.dronegcs.mavlink.is.drone.mission.survey.grid.GridBuilder;
import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_mission_item;
import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;
import com.geo_tools.Coordinate;
import com.geo_tools.GeoTools;
import com.geo_tools.shapes.Polygon;

public class MavlinkStructureScanner extends SpatialCoordItemDrone {
	
	private double radius = 10.0;
	private double heightStep = 5;
	private int numberOfSteps = 2;
	private boolean crossHatch = false;
	SurveyData survey = new SurveyData();

	public MavlinkStructureScanner(DroneMission droneMission, Coordinate coord) {
		super(droneMission,coord);
	}

	public MavlinkStructureScanner(MavlinkStructureScanner mavlinkStructureScanner) {
		super(mavlinkStructureScanner);
		this.radius = mavlinkStructureScanner.radius;
		this.heightStep = mavlinkStructureScanner.heightStep;
		this.numberOfSteps = mavlinkStructureScanner.numberOfSteps;
		this.crossHatch = mavlinkStructureScanner.crossHatch;
		this.survey = new SurveyData(survey);
	}

	@Override
	public List<msg_mission_item> packMissionItem(Drone drone) {
		List<msg_mission_item> list = new ArrayList<msg_mission_item>();
		packROI(list);
		packCircles(list);
		if (crossHatch) {
			packHatch(drone, list);
		}
		return list;
	}

	private void packROI(List<msg_mission_item> list) {
		MavlinkRegionOfInterest roi = new MavlinkRegionOfInterest(droneMission, new Coordinate(coordinate, 0.0));
		list.addAll(roi.packMissionItem(droneMission.getDrone()));
	}

	private void packCircles(List<msg_mission_item> list) {
		for (double altitude = coordinate.getAltitude(); altitude <= getTopHeight(); altitude += heightStep) {
			MavlinkLoiterTurns mavlinkLoiterTurns = new MavlinkLoiterTurns(droneMission, new Coordinate(coordinate,	altitude));
			mavlinkLoiterTurns.setRadius(radius);
			list.addAll(mavlinkLoiterTurns.packMissionItem(droneMission.getDrone()));
		}
	}

	private void packHatch(Drone drone, List<msg_mission_item> list) {
		Polygon polygon = new Polygon();
		for (double angle = 0; angle <= 360; angle += 10) {
			polygon.addPoint(GeoTools.newCoordFromBearingAndDistance(coordinate, angle, radius));
		}

		Coordinate corner = GeoTools.newCoordFromBearingAndDistance(coordinate, -45, radius * 2);
		
		survey.setAltitude(getTopHeight());
		
		try {
			survey.update(0.0, survey.getAltitude(), survey.getOverlap(), survey.getSidelap());
			GridBuilder grid = new GridBuilder(polygon, survey, corner);
			for (Coordinate point : grid.generate(false).gridPoints) {
				list.add(MavlinkSurvey.packSurveyPoint(drone, point, getTopHeight()));
			}
			
			survey.update(90.0, survey.getAltitude(), survey.getOverlap(), survey.getSidelap());
			GridBuilder grid2 = new GridBuilder(polygon, survey, corner);
			for (Coordinate point : grid2.generate(false).gridPoints) {
				list.add(MavlinkSurvey.packSurveyPoint(drone, point, getTopHeight()));
			}
		} catch (Exception e) { // Should never fail, since it has good polygons
		}

	}

	public List<Coordinate> getPath() {
		List<Coordinate> path = new ArrayList<Coordinate>();
		for (msg_mission_item msg_mission_item : packMissionItem(droneMission.getDrone())) {
			if (msg_mission_item.command == MAV_CMD.MAV_CMD_NAV_WAYPOINT) {
				path.add(new Coordinate(msg_mission_item.x, msg_mission_item.y));
			}
			if (msg_mission_item.command == MAV_CMD.MAV_CMD_NAV_LOITER_TURNS) {
				for (double angle = 0; angle <= 360; angle += 12) {
					path.add(GeoTools.newCoordFromBearingAndDistance(coordinate,angle, radius));
				}
			}
			
		}
		return path;

	}

	@Override
	public void unpackMAVMessage(msg_mission_item mavMsg) {
	}

	@Override
	public MissionItemType getType() {
		return MissionItemType.CYLINDRICAL_SURVEY;
	}
	


	private double getTopHeight() {
		return coordinate.getAltitude() + (numberOfSteps - 1) * heightStep;
	}

	public double getEndAltitude() {
		return heightStep;
	}

	public int getNumberOfSteps() {
		return numberOfSteps;
	}

	public double getRadius() {
		return radius;
	}

	public Coordinate getCenter() {
		return coordinate;
	}

	public void setRadius(int newValue) {
		radius = newValue;
	}

	public void enableCrossHatch(boolean isEnabled) {
		crossHatch = isEnabled;
	}

	public boolean isCrossHatchEnabled() {
		return crossHatch;
	}

	public void setAltitudeStep(int newValue) {
		heightStep = newValue;		
	}

	public void setNumberOfSteps(int newValue) {
		numberOfSteps = newValue;	
	}

	public void setCamera(CameraInfo cameraInfo) {
		survey.setCameraInfo(cameraInfo);
	}

	public String getCamera() {
		return survey.getCameraName();
	}
	
	@Override
	public MavlinkStructureScanner clone(DroneMission droneMission) {
		MavlinkStructureScanner mavlinkStructureScanner = new MavlinkStructureScanner(this);
		mavlinkStructureScanner.setDroneMission(droneMission);
		return mavlinkStructureScanner;
	}

	@Override
	public void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException {
		convertMavlinkVisitor.visit(this);
	}

	@Override
	public String toString() {
		return "MavlinkStructureScanner{" +
				super.toString() +
				", radius=" + radius +
				", heightStep=" + heightStep +
				", numberOfSteps=" + numberOfSteps +
				", crossHatch=" + crossHatch +
				", survey=" + survey +
				'}';
	}
}
