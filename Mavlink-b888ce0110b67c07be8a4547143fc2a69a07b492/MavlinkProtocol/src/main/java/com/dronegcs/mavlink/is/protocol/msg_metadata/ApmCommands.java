package com.dronegcs.mavlink.is.protocol.msg_metadata;

import java.util.ArrayList;
import java.util.List;

import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_CMD;

public enum ApmCommands {	

//	CMD_NAV_WAYPOINT ("MavlinkWaypoint",MAV_CMD.MAV_CMD_NAV_WAYPOINT,CommandType.NAVIGATION), /* Navigate to MISSION. |Hold time in decimal seconds. (ignored by fixed wing, time to stay at MISSION for rotary wing)| Acceptance radius in meters (if the sphere with this radius is hit, the MISSION counts as reached)| 0 to pass through the WP, if > 0 radius in meters to pass by WP. Positive value for clockwise orbit, negative value for counter-clockwise orbit. Allows trajectory control.| Desired yaw angle at MISSION (rotary wing)| Latitude| Longitude| Altitude|  */
//	CMD_NAV_LOITER_UNLIM("Loiter",MAV_CMD.MAV_CMD_NAV_LOITER_UNLIM ,CommandType.NAVIGATION), /* Loiter around this MISSION an unlimited amount of time |Empty| Empty| Radius around MISSION, in meters. If positive loiter clockwise, else counter-clockwise| Desired yaw angle.| Latitude| Longitude| Altitude|  */
//	CMD_NAV_LOITER_TURNS("LoiterN",MAV_CMD.MAV_CMD_NAV_LOITER_TURNS ,CommandType.NAVIGATION), /* Loiter around this MISSION for X turns |Turns| Empty| Radius around MISSION, in meters. If positive loiter clockwise, else counter-clockwise| Desired yaw angle.| Latitude| Longitude| Altitude|  */
//	CMD_NAV_LOITER_TIME ("LoiterT",MAV_CMD.MAV_CMD_NAV_LOITER_TIME ,CommandType.NAVIGATION), /* Loiter around this MISSION for X seconds |Seconds (decimal)| Empty| Radius around MISSION, in meters. If positive loiter clockwise, else counter-clockwise| Desired yaw angle.| Latitude| Longitude| Altitude|  */
//	CMD_NAV_RETURN_TO_LAUNCH("RTL",MAV_CMD.MAV_CMD_NAV_RETURN_TO_LAUNCH,CommandType.COMMAND), /* Return to launch location |Empty| Empty| Empty| Empty| Empty| Empty| Empty|  */
//	CMD_NAV_LAND("MavlinkLand",MAV_CMD.MAV_CMD_NAV_LAND,CommandType.NAVIGATION), /* MavlinkLand at location |Empty| Empty| Empty| Desired yaw angle.| Latitude| Longitude| Altitude|  */
//	CMD_NAV_TAKEOFF("MavlinkTakeoff",MAV_CMD.MAV_CMD_NAV_TAKEOFF,CommandType.NAVIGATION), /* MavlinkTakeoff from ground / hand |Minimum pitch (if airspeed sensor present), desired pitch without sensor| Empty| Empty| Yaw angle (if magnetometer present), ignored without magnetometer| Latitude| Longitude| Altitude|  */
//	CMD_NAV_ROI("ROI",MAV_CMD.MAV_CMD_NAV_ROI,CommandType.COMMAND_WITH_TARGET), /* Sets the region of interest (ROI) for a sensor set or the vehicle itself. This can then be used by the vehicles control system to control the vehicle attitude and the attitude of various sensors such as cameras. |Region of intereset mode. (see MAV_ROI enum)| MISSION index/ target ID. (see MAV_ROI enum)| ROI index (allows a vehicle to manage multiple ROI's)| Empty| x the location of the fixed ROI (see MAV_FRAME)| y| z|  */
//	CMD_NAV_PATHPLANNING("Path",MAV_CMD.MAV_CMD_NAV_PATHPLANNING,CommandType.COMMAND), /* Control autonomous path planning on the MAV. |0: Disable local obstacle avoidance / local path planning (without resetting map), 1: Enable local path planning, 2: Enable and reset local path planning| 0: Disable full path planning (without resetting map), 1: Enable, 2: Enable and reset map/occupancy grid, 3: Enable and reset planned route, but not occupancy grid| Empty| Yaw angle at goal, in compass degrees, [0..360]| Latitude/X of goal| Longitude/Y of goal| Altitude/Z of goal|  */
//	CMD_DO_JUMP("Do Jump",MAV_CMD.MAV_CMD_DO_JUMP,CommandType.COMMAND), /* Jump to the desired command in the droneMission list.  Repeat this action only the specified number of times |Sequence number| Repeat count| Empty| Empty| Empty| Empty| Empty|  */
//	CMD_DO_SET_HOME("Set Home",MAV_CMD.MAV_CMD_DO_SET_HOME,CommandType.COMMAND_WITH_TARGET), /*	Changes the home location either to the current location or a specified location.| Use current (1=use current location, 0=use specified location) | Empty	| Empty	| Empty | Latitude | Longitude | Altitude*/
//	CMD_DO_CHANGE_SPEED("Set Speed",MAV_CMD.MAV_CMD_DO_CHANGE_SPEED,CommandType.COMMAND), /*	Change speed and/or throttle set points.| Speed type (0=Airspeed, 1=Ground Speed) | Speed (m/s, -1 indicates no change)	| Throttle ( Percent, -1 indicates no change) | Empty | Empty | Empty | Empty*/
//	CMD_CONDITION_CHANGE_ALT("Set Alt",MAV_CMD.MAV_CMD_CONDITION_CHANGE_ALT,CommandType.NAVIGATION),/*Ascend/descend at rate. Delay droneMission state machine until desired altitude reached.| Descent / Ascend rate (m/s) |	Empty |	Empty |	Empty | Empty | Empty | Finish Altitude | */
//	CMD_CONDITION_DISTANCE("Set Distance",MAV_CMD.MAV_CMD_CONDITION_DISTANCE,CommandType.COMMAND),	/*Delay droneMission state machine until within desired distance of next NAV point.| Distance (meters) | Empty | Empty | Empty | Empty | Empty | Empty*/
//	CMD_CONDITION_YAW("Yaw to",MAV_CMD.MAV_CMD_CONDITION_YAW,CommandType.COMMAND),  /* Yaw to heading while executing next waypoint.  |  Target angle: [0-360], 0 is north. |    speed during yaw change:[deg per second] |      direction: negative: counter clockwise, positive: clockwise [-1,1] | relative offset or absolute angle: [ 1,0] | Empty| Empty| Empty|  */
//	CMD_DO_SET_RELAY("Set Relay",MAV_CMD.MAV_CMD_DO_SET_RELAY,CommandType.COMMAND),/*	Set a relay to a condition.| Relay number	| Setting (1=on, 0=off, others possible depending on system hardware) | Empty | Empty | Empty	| Empty | Empty*/
//	CMD_DO_REPEAT_RELAY("Repeat Relay",MAV_CMD.MAV_CMD_DO_REPEAT_RELAY,CommandType.COMMAND),/*	Cycle a relay on and off for a desired number of cyles with a desired period.	| Relay number	| Cycle count	| Cycle time (seconds, decimal)	| Empty	| Empty	| Empty	| Empty	*/

	// Commands for Channel 7 & 8
	CMD_DO_NOTHING("Do nothing",MAV_CMD.MAV_NOP,CommandType.COMMAND),
	CMD_FLIP("Flip",MAV_CMD.MAV_CMD_FLIP,CommandType.COMMAND),
	CMD_SIMPLE_MODE("Simple mode",MAV_CMD.MAV_CMD_SIMPLEMODE,CommandType.COMMAND),
	CMD_RTL("RTL",MAV_CMD.MAV_CMD_RTL,CommandType.COMMAND),
	CMD_SAVETRIM("Save Trim",MAV_CMD.MAV_CMD_SAVETRIM,CommandType.COMMAND),
	CMD_SAVEWB("Save WP",MAV_CMD.MAV_CMD_SAVEWP,CommandType.COMMAND),
	CMD_CAMERA_TRIGGER("Camera Trigger",MAV_CMD.MAV_CMD_CAMERATRIGGER,CommandType.COMMAND),
	CMD_RANGEFINDER("Range Finder",MAV_CMD.MAV_CMD_RANGEFINDER,CommandType.COMMAND),
	CMD_FETCE("Fence",MAV_CMD.MAV_CMD_FENCE,CommandType.COMMAND),
	CMD_SUPER_SIMPLEMODE("Super Simple Mode",MAV_CMD.MAV_CMD_SUPER_SIMPLE,CommandType.COMMAND),
	CMD_ACRO_TRAINER("Acro Trainer",MAV_CMD.MAV_CMD_AUTO_TRAINER,CommandType.COMMAND),
	CMD_SPAYAR("Sprayer",MAV_CMD.MAV_CMD_SPRAYER,CommandType.COMMAND),
	CMD_AUTO("Auto",MAV_CMD.MAV_CMD_AUTO,CommandType.COMMAND),
	CMD_AUTO_TUNE("Auto Tune",MAV_CMD.MAV_CMD_AUTOTUNE,CommandType.COMMAND),
	CMD_LAND("Land",MAV_CMD.MAV_CMD_LAND,CommandType.COMMAND),
	CMD_EPM_CARGO_GRIPPER("EPM Cargo Gripper",MAV_CMD.MAV_CMD_EPM_CARGO,CommandType.COMMAND),
	CMD_PARACHUTE_ENABLE("Parachute Enable",MAV_CMD.MAV_CMD_PARACHUTE_ENABLE,CommandType.COMMAND),
	CMD_PARACHUTE_RELEASE("Parachute Release",MAV_CMD.MAV_CMD_PARACHUTE_RELEASE,CommandType.COMMAND),
	CMD_PARACHUTE_3POS_SWITCH("Parachute 3POS switch",MAV_CMD.MAV_CMD_PARACHUTE_3POS,CommandType.COMMAND),
	CMD_MISSION_RESET("Mission Reset",MAV_CMD.MAV_CMD_MISSION_RESET,CommandType.COMMAND),
	CMD_ROLLPITCH_FFENABLE("Roll/Pitch FF enable",MAV_CMD.MAV_CMD_ROLL_PITCH_FFENABLE,CommandType.COMMAND),
	CMD_ACCEL_LIMIT_ENABLE("Accel Limiting Enable",MAV_CMD.MAV_CMD_ACCELLIMITON,CommandType.COMMAND),
	CMD_RETRACT_MOUNT("Retract Mount",MAV_CMD.MAV_CMD_RETRACTMOUNT,CommandType.COMMAND);

	private final String name;
	private final int commandNumber;
	private final CommandType commandType;
    
	ApmCommands(String name, int number, CommandType showOnMap){
		this.name = name;
		this.commandNumber = number;
		this.commandType = showOnMap; 
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumber() {
		return commandNumber;
	}
	
	public boolean showOnMap(){
		switch (this.commandType) {
		case COMMAND:
			return false;
		default:
		case COMMAND_WITH_TARGET:
		case NAVIGATION:
			return true;
		}
	}
	
	public boolean isOnFligthPath(){
		switch (this.commandType) {
		default:
		case COMMAND:
		case COMMAND_WITH_TARGET:
			return false;
		case NAVIGATION:
			return true;
		}
	}
	
	public static ApmCommands getCmd(int type) {
		for (ApmCommands mode : ApmCommands.values()) {
			if (type == mode.getNumber()) {
				return mode;
			}
		}
		return null;
	}	
	
	public static ApmCommands getCmd(String str) {
		for (ApmCommands mode : ApmCommands.values()) {
			if (str.equals(mode.getName())) {
				return mode;
			}
		}
		return null;
	}

	public static List<ApmCommands> getCommandsList() {
		List<ApmCommands> list = new ArrayList<>();
		for (ApmCommands mode : ApmCommands.values())
			list.add(mode);

		return list;
	}

	public static ApmCommands getCommand(int id) {
		for (ApmCommands command : ApmCommands.values())
			if (command.commandNumber == id)
				return command;

		return ApmCommands.CMD_DO_NOTHING;
	}
	
	public static List<String> getNameList() {
		List<String> list = new ArrayList<String>();
		
		for (ApmCommands mode : ApmCommands.values()) {
				list.add(mode.getName());
		}
		return list;
	}
		

	private enum CommandType{
		NAVIGATION,
		COMMAND,
		COMMAND_WITH_TARGET
	}

	@Override
	public String toString() {
		return name;
	}
}
