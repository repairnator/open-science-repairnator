package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

public enum MAV_PARAM_UNIT {

    // Fields,
    MILLIGAUSS("milligauss"),
    MILLIGAUSS_PER_AMPER("milligauss/amper"),

    // Power,
    VOLT("volt"),
    PWM("PWM"),
    MILLIAMPER_PER_HOUR("mAh"),
    AMPER_PER_VOLT("amper/volt"),

    // Size,
    CENTIMETER("cm"),
    METER("meter"),
    RADIANS("radian"),
    PASCAL("pascal"),
    DEGREE("degree"),
    CENTIDEGREE("centidegree"),

    // Time,
    MILLISECOND("millisecond"),
    SECOND("second"),
    DECISECOND("decisecond"),

    // Temp
    DEGREE_CELSIUS("degrees-celsius"),

    // Speed,
    HERTZ("Hz"),
    RADIAN_PER_SECOND("rad/s"),
    DEGREE_PER_SECOND("deg/s"),
    METER_PER_SECOND("m/s"),
    METER_PER_SECOND_PER_SECOND("m/s/s"),
    CENTIMETER_PER_SECOND("cm/s"),
    CENTIMETER_PER_SECOND_PER_SECOND("cm/s/s"),
    CENTIMETER_PER_SECOND_PER_SECOND_PER_SECOND("cm/s/s/s"),
    CENTIDEGREE_PER_SECOND("centidegrees/s"),
    CENTIDEGREE_PER_SECOND_PER_SECOND("centidegrees/s/s"),
    METER_PER_VOLT("m/volt"),
    PWM_PER_MICROSECOND("PWM/ms"),
    PERCENT_PER_SECOND("percent/s"),

    // General
    PERCENT("percent"),
    UNKNOWN("unknown"),
    NONE(""),
    FLAGS("list"),
    BITMASK("bitmask"),
    ;

    private final String name;

    MAV_PARAM_UNIT(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
