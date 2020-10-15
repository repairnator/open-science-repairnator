package com.dronegcs.mavlink.is.drone.mission;

public enum MissionItemType {
	
	WAYPOINT("MavlinkWaypoint"),
	SPLINE_WAYPOINT("Spline MavlinkWaypoint"),
	TAKEOFF("MavlinkTakeoff"),
	RTL("Return to Launch"), 
	LAND("MavlinkLand"),
	ROI("Region of Interest"), 
	SURVEY("MavlinkSurvey"),
	CYLINDRICAL_SURVEY("Structure Scan"), 
	CHANGE_SPEED("Change Speed"), 
	CAMERA_TRIGGER("Camera Trigger"), 
	EPM_GRIPPER("EPM"),
	LOITER_UNLIMITED("Loiter Unlimited"),
	LOITER_TIME("Loiter Time"),
	LOITER_TURNS("Loiter Turns");

	private final String name;

	private MissionItemType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

//	public DroneMissionItem getNewItem(DroneMissionItem referenceItem) throws IllegalArgumentException {
//		switch (this) {
//		case WAYPOINT:
//			return new MavlinkWaypoint(referenceItem);
//		case SPLINE_WAYPOINT:
//			return new MavlinkSplineWaypoint(referenceItem);
//		case TAKEOFF:
//			return new MavlinkTakeoff(referenceItem);
//		case CHANGE_SPEED:
//			return new MavlinkChangeSpeed(referenceItem);
//		case CAMERA_TRIGGER:
//			return new MavlinkCameraTrigger(referenceItem);
//		case EPM_GRIPPER:
//			return new MavlinkEpmGripper(referenceItem);
//		case RTL:
//			return new MavlinkReturnToHome(referenceItem);
//		case LAND:
//			return new MavlinkLand(referenceItem);
//		case CIRCLE:
//			return new MavlinkCircle(referenceItem);
//		case ROI:
//			return new MavlinkRegionOfInterest(referenceItem);
//		case SURVEY:
//			return new MavlinkSurvey(referenceItem.getDroneMission(), Collections.<Coord2D> emptyList());
//		case CYLINDRICAL_SURVEY:
//			return new MavlinkStructureScanner(referenceItem);
//		default:
//			throw new IllegalArgumentException("Unrecognized droneMission item type (" + name + ")"
//					+ ".");
//		}
//	}
}
