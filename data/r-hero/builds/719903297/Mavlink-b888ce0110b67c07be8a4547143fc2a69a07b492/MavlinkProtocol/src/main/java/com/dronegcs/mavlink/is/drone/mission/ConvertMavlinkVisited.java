package com.dronegcs.mavlink.is.drone.mission;

/**
 * Created by taljmars on 3/18/17.
 */
public interface ConvertMavlinkVisited {

    void accept(ConvertMavlinkVisitor convertMavlinkVisitor) throws MavlinkConvertionException;
}
