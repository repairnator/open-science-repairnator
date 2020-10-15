package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

import com.dronegcs.mavlink.is.units.Range;

import java.util.Map;

public interface MAV_PARAM_I {

    MAV_PARAM_GROUP_I getGroup();

    String getName();

    Number getDefaultValue();

    Number getIncrement();

    MAV_PARAM_UNIT getUnit();

    Range getRange();

    Map<Number, String> getOptions();

    boolean isReadOnly();

    String getTitle();

    String getDescription();
}
