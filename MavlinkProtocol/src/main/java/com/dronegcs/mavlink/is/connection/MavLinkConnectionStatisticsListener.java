package com.dronegcs.mavlink.is.connection;

/**
 * Created by taljmars on 6/9/2017.
 */
public interface MavLinkConnectionStatisticsListener {

    void onConnectionStatistics(ConnectionStatistics connectionStatistics);
}
