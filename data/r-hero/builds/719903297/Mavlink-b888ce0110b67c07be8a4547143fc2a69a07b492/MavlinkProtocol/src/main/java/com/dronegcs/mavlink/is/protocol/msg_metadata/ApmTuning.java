package com.dronegcs.mavlink.is.protocol.msg_metadata;

import java.util.ArrayList;
import java.util.List;

import static com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_TYPE.MAV_PARAM_TYPE_REAL32;

/**
 * Created by taljmars on 6/24/2017.
 */
public enum ApmTuning {

    NONE(0,"NONE",MAV_PARAM_TYPE_REAL32),
    STAB_ROLLPITCH_KP(1,"Stab Roll/Pitch kP", MAV_PARAM_TYPE_REAL32),
    RATE_ROLLPITCH_KP(4,"Rate Roll/Pitch kP",MAV_PARAM_TYPE_REAL32),
    RATE_ROLLPITCH_KI(5,"Rate Roll/Pitch kI",MAV_PARAM_TYPE_REAL32),
    RATE_ROLLPITCH_KD(21,"Rate Roll/Pitch kD",MAV_PARAM_TYPE_REAL32),
    STAB_YAW_KP(3,"Stab Yaw kP",MAV_PARAM_TYPE_REAL32),
    RATE_YAW_KP(6,"Rate Yaw kP",MAV_PARAM_TYPE_REAL32),
    RATE_YAW_KD(26,"Rate Yaw kD",MAV_PARAM_TYPE_REAL32),
    RATE_YAW_FILTER(56,"Rate Yaw Filter",MAV_PARAM_TYPE_REAL32),
    MOTOR_YAW_HEADROOM(55,"Motor Yaw Headroom",MAV_PARAM_TYPE_REAL32),
    ALTHOLD_KP(14,"AltHold kP",MAV_PARAM_TYPE_REAL32),
    THROTTLE_RATE_KP(7,"Throttle Rate kP",MAV_PARAM_TYPE_REAL32),
    THROTTLE_ACCEL_KP(34,"Throttle Accel kP",MAV_PARAM_TYPE_REAL32),
    THROTTLE_ACCEL_KI(35,"Throttle Accel kI",MAV_PARAM_TYPE_REAL32),
    THROTTLE_ACCEL_KD(36,"Throttle Accel kD",MAV_PARAM_TYPE_REAL32),
    LOITER_POS_KP(12,"Loiter Pos kP",MAV_PARAM_TYPE_REAL32),
    VELOCITY_XM_KP(22,"Velocity XY kP",MAV_PARAM_TYPE_REAL32),
    VELOCITY_XM_KI(28,"Velocity XY kI",MAV_PARAM_TYPE_REAL32),
    WP_SPEED(10,"WP Speed",MAV_PARAM_TYPE_REAL32),
    ACRO_ROLLPITCH_KP(25,"Acro RollPitch kP",MAV_PARAM_TYPE_REAL32),
    ACRO_YAW_KP(40,"Acro Yaw kP",MAV_PARAM_TYPE_REAL32),
    RC_FEEL(45,"RC Feel",MAV_PARAM_TYPE_REAL32),
    HELI_EXT_GYRO(13,"Heli Ext Gyro",MAV_PARAM_TYPE_REAL32),
    DECLINATION(38,"Declination",MAV_PARAM_TYPE_REAL32),
    CIRCLE_RATE(39,"Circle Rate",MAV_PARAM_TYPE_REAL32),
    RANGE_FINDER_GAIN(41,"RangeFinder Gain",MAV_PARAM_TYPE_REAL32),
    RATE_PITCH_KP(46,"Rate Pitch kP",MAV_PARAM_TYPE_REAL32),
    RATE_PITCH_KI(47,"Rate Pitch kI",MAV_PARAM_TYPE_REAL32),
    RATE_PITCH_KD(48,"Rate Pitch kD",MAV_PARAM_TYPE_REAL32),
    RATE_ROLL_KP(49,"Rate Roll kP",MAV_PARAM_TYPE_REAL32),
    RATE_ROLL_KI(50,"Rate Roll kI",MAV_PARAM_TYPE_REAL32),
    RATE_ROLL_KD(51,"Rate Roll kD",MAV_PARAM_TYPE_REAL32),
    RATE_PITCH_FF(52,"Rate Pitch FF",MAV_PARAM_TYPE_REAL32),
    RATE_ROLL_FF(53,"Rate Roll FF",MAV_PARAM_TYPE_REAL32),
    RATE_YAW_FF(54,"Rate Yaw FF",MAV_PARAM_TYPE_REAL32),

    UNKNOWN(-1,"UNKNOWN",MAV_PARAM_TYPE_REAL32); // Should not be sent to quad, only to emphsize issue;

    private final int number;
    private final String name;
    private final int type;

    ApmTuning(int number, String name, int type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public static ApmTuning getTune(int i) {
        for (ApmTuning tune : ApmTuning.values()) {
            if (i == tune.getNumber()) {
                return tune;
            }
        }
        return UNKNOWN;
    }

    public static List<ApmTuning> getTuningList() {
        ArrayList list = new ArrayList();
        ApmTuning[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ApmTuning mode = var1[var3];
            list.add(mode);
        }

        return list;
    }

    @Override
    public String toString() {
        return name;
    }
}
