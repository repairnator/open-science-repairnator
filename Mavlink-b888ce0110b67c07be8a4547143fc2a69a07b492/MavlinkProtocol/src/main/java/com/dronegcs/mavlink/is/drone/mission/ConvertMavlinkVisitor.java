package com.dronegcs.mavlink.is.drone.mission;

import com.dronegcs.mavlink.is.drone.mission.commands.*;
import com.dronegcs.mavlink.is.drone.mission.survey.MavlinkSurvey;
import com.dronegcs.mavlink.is.drone.mission.waypoints.*;

/**
 * Created by taljmars on 3/18/17.
 */
public interface ConvertMavlinkVisitor {

    void visit(MavlinkWaypoint mavlinkWaypoint) throws MavlinkConvertionException;

    void visit(MavlinkLand mavlinkLand) throws MavlinkConvertionException;

    void visit(MavlinkReturnToHome mavlinkReturnToHome) throws MavlinkConvertionException;

    void visit(MavlinkTakeoff mavlinkTakeoff) throws MavlinkConvertionException;

    void visit(MavlinkStructureScanner mavlinkStructureScanner) throws MavlinkConvertionException;

    void visit(MavlinkChangeSpeed mavlinkChangeSpeed) throws MavlinkConvertionException;

    void visit(MavlinkRegionOfInterest mavlinkRegionOfInterest) throws MavlinkConvertionException;

    void visit(MavlinkSurvey mavlinkSurvey) throws MavlinkConvertionException;

    void visit(MavlinkEpmGripper mavlinkEpmGripper) throws MavlinkConvertionException;

    void visit(MavlinkCameraTrigger mavlinkCameraTrigger) throws MavlinkConvertionException;

    void visit(MavlinkSplineWaypoint mavlinkSplineWaypoint) throws MavlinkConvertionException;

    void visit(MavlinkLoiterUnlimited mavlinkLoiterUnlimited) throws MavlinkConvertionException;

    void visit(MavlinkLoiterTime mavlinkLoiterTime) throws  MavlinkConvertionException;

    void visit(MavlinkLoiterTurns mavlinkLoiterTurns) throws MavlinkConvertionException;
}
