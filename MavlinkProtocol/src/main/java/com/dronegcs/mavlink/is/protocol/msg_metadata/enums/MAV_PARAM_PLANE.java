package com.dronegcs.mavlink.is.protocol.msg_metadata.enums;

import com.dronegcs.mavlink.is.units.Range;

import java.util.HashMap;
import java.util.Map;

public enum MAV_PARAM_PLANE implements MAV_PARAM_I {

    ACRO_LOCKING(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"ACRO mode attitude locking","Enable attitude locking when sticks are released"),
    ACRO_PITCH_RATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,180,1,MAV_PARAM_UNIT.DEGREE_PER_SECOND,new Range(10,500),false,"ACRO mode pitch rate","The maximum pitch rate at full stick deflection in ACRO mode"),
    ACRO_ROLL_RATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,180,1,MAV_PARAM_UNIT.DEGREE_PER_SECOND,new Range(10,500),false,"ACRO mode roll rate","The maximum roll rate at full stick deflection in ACRO mode"),
    AHRS_COMP_BETA(MAV_PARAM_GROUP_PLANE.AHRS,0.100000001490116,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_GPS_GAIN(MAV_PARAM_GROUP_PLANE.AHRS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_GPS_MINSATS(MAV_PARAM_GROUP_PLANE.AHRS,6,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_GPS_USE(MAV_PARAM_GROUP_PLANE.AHRS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_ORIENTATION(MAV_PARAM_GROUP_PLANE.AHRS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_RP_P(MAV_PARAM_GROUP_PLANE.AHRS,0.200000002980232,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_TRIM_X(MAV_PARAM_GROUP_PLANE.AHRS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_TRIM_Y(MAV_PARAM_GROUP_PLANE.AHRS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_TRIM_Z(MAV_PARAM_GROUP_PLANE.AHRS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_WIND_MAX(MAV_PARAM_GROUP_PLANE.AHRS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AHRS_YAW_P(MAV_PARAM_GROUP_PLANE.AHRS,0.200000002980232,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ALT_CTRL_ALG(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Automatic");}},false,"Altitude control algorithm","This sets what algorithm will be used for altitude control. The default is zero, which selects the most appropriate algorithm for your airframe. Currently the default is to use TECS (total energy control system). From time to time we will add other experimental altitude control algorithms which will be selected using this parameter."),
    ALT_HOLD_FBWCM(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.CENTIMETER,false,"Minimum altitude for FBWB mode","This is the minimum altitude in centimeters that FBWB and CRUISE modes will allow. If you attempt to descend below this altitude then the plane will level off. A value of zero means no limit."),
    ALT_HOLD_RTL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,10000,1,MAV_PARAM_UNIT.CENTIMETER,false,"RTL altitude","Target altitude above home for RTL mode. Maintains current altitude if set to -1. Rally point altitudes are used if plane does not return to home."),
    ALT_MIX(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ALT_OFFSET(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(-32767,32767),false,"Altitude offset","This is added to the target altitude in automatic flight. It can be used to add a global altitude offset to a mission"),
    ARMING_CHECK(MAV_PARAM_GROUP_PLANE.ARMING,1,1,MAV_PARAM_UNIT.BITMASK,new HashMap<Number, String>(){{put(0,"All");put(1,"Barometer");put(2 ,"Compass");put(3 ,"GPS lock");put(4 ,"INS");put(5 ,"Parameters");put(6 ,"RC Channels");put(7 ,"Board voltage");put(8 ,"Battery Level");put(9 ,"Airspeed");put(10 ,"Logging Available");put(11 ,"Hardware safety switch");put(12 ,"GPS Configuration");put(13 ,"System");put(14 ,"Mission");put(15 ,"Rangefinder");}},false,"Arm Checks to Perform (bitmask)","Checks prior to arming motor. This is a bitmask of checks that will be performed before allowing arming. The default is no checks, allowing arming at any time. You can select whatever checks you prefer by adding together the values of each check type to set this parameter. For example, to only allow arming when you have GPS lock and no RC failsafe you would set ARMING_CHECK to 72. For most users it is recommended that you set this to 1 to enable all checks."),
    ARMING_REQUIRE(MAV_PARAM_GROUP_PLANE.ARMING,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARMING_RUDDER(MAV_PARAM_GROUP_PLANE.ARMING,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_AUTOCAL(MAV_PARAM_GROUP_PLANE.ARSPD,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_ENABLE(MAV_PARAM_GROUP_PLANE.ARSPD,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_FBW_MAX(MAV_PARAM_GROUP_PLANE.ARSPD,22,1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(5,100),false,"Maximum Airspeed","Maximum airspeed demanded in automatic throttle modes. Should be set slightly less than level flight speed at THR_MAX and also at least 50% above ARSPD_FBW_MIN to allow for accurate TECS altitude control."),
    ARSPD_FBW_MIN(MAV_PARAM_GROUP_PLANE.ARSPD,9,1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(5,100),false,"Minimum Airspeed","Minimum airspeed demanded in automatic throttle modes. Should be set to 20% higher than level flight stall speed."),
    ARSPD_OFFSET(MAV_PARAM_GROUP_PLANE.ARSPD,860.682922363281,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_PIN(MAV_PARAM_GROUP_PLANE.ARSPD,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_RATIO(MAV_PARAM_GROUP_PLANE.ARSPD,1.99360001087189,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_SKIP_CAL(MAV_PARAM_GROUP_PLANE.ARSPD,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_TUBE_ORDER(MAV_PARAM_GROUP_PLANE.ARSPD,2,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ARSPD_USE(MAV_PARAM_GROUP_PLANE.ARSPD,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    AUTOTUNE_LEVEL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,6,1,MAV_PARAM_UNIT.UNKNOWN,new Range(1,10),false,"Autotune level","Level of aggressiveness of pitch and roll PID gains. Lower values result in a 'softer' tune. Level 6 recommended for most planes."),
    AUTO_FBW_STEER(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(42,"Enabled");}},false,"Use FBWA steering in AUTO","When enabled this option gives FBWA navigation and steering in AUTO mode. This can be used to allow manual stabilised piloting with waypoint logic for triggering payloads. With this enabled the pilot has the same control over the plane as in FBWA mode, and the normal AUTO navigation is completely disabled. THIS OPTION IS NOT RECOMMENDED FOR NORMAL USE."),
    BATT2_AMP_OFFSET(MAV_PARAM_GROUP_PLANE.BATT2,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_AMP_PERVOL(MAV_PARAM_GROUP_PLANE.BATT2,17,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_CAPACITY(MAV_PARAM_GROUP_PLANE.BATT2,3300,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_CURR_PIN(MAV_PARAM_GROUP_PLANE.BATT2,12,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_MONITOR(MAV_PARAM_GROUP_PLANE.BATT2,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_VOLT_MULT(MAV_PARAM_GROUP_PLANE.BATT2,10.1000003814697,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT2_VOLT_PIN(MAV_PARAM_GROUP_PLANE.BATT2,13,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_AMP_OFFSET(MAV_PARAM_GROUP_PLANE.BATT,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_AMP_PERVOLT(MAV_PARAM_GROUP_PLANE.BATT,17,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_CAPACITY(MAV_PARAM_GROUP_PLANE.BATT,3300,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_CURR_PIN(MAV_PARAM_GROUP_PLANE.BATT,12,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_MONITOR(MAV_PARAM_GROUP_PLANE.BATT,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_VOLT_MULT(MAV_PARAM_GROUP_PLANE.BATT,10.1000003814697,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BATT_VOLT_PIN(MAV_PARAM_GROUP_PLANE.BATT,13,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    BRD_SERIAL_NUM(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CAM_DURATION(MAV_PARAM_GROUP_PLANE.CAM,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CAM_SERVO_OFF(MAV_PARAM_GROUP_PLANE.CAM,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CAM_SERVO_ON(MAV_PARAM_GROUP_PLANE.CAM,1300,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CAM_TRIGG_DIST(MAV_PARAM_GROUP_PLANE.CAM,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CAM_TRIGG_TYPE(MAV_PARAM_GROUP_PLANE.CAM,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CHUTE_CHAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Parachute release channel","If set to a non-zero value then this is an RC input channel number to use for manually releasing the parachute. When this channel goes above 1700 the parachute will be released"),
    COMPASS_AUTODEC(MAV_PARAM_GROUP_PLANE.COMPASS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_DEC(MAV_PARAM_GROUP_PLANE.COMPASS,0.0694443434476852,1,MAV_PARAM_UNIT.RADIANS,new Range(-3.142,3.142),false,"","An angle to compensate between the true north and magnetic north"),
    COMPASS_EXTERNAL(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_LEARN(MAV_PARAM_GROUP_PLANE.COMPASS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_MOTCT(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_MOT_X(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_MOT_Y(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_MOT_Z(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_OFS_X(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_OFS_Y(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_OFS_Z(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_ORIENT(MAV_PARAM_GROUP_PLANE.COMPASS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    COMPASS_USE(MAV_PARAM_GROUP_PLANE.COMPASS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    CRASH_ACC_THRESH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER_PER_SECOND_PER_SECOND,new Range(10,127),false,"Crash Deceleration Threshold","X-Axis deceleration threshold to notify the crash detector that there was a possible impact which helps disarm the motor quickly after a crash. This value should be much higher than normal negative x-axis forces during normal flight, check flight log files to determine the average IMU.x values for your aircraft and motor type. Higher value means less sensative (triggers on higher impact). For electric planes that don't vibrate much during fight a value of 25 is good (that's about 2.5G). For petrol/nitro planes you'll want a higher value. Set to 0 to disable the collision detector."),
    CRASH_DETECT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Crash Detection","Automatically detect a crash during AUTO flight and perform the bitmask selected action(s). Disarm will turn off motor for safety and to help against burning out ESC and motor. Set to 0 to disable crash detection."),
    DSPOILR_RUD_RATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.PERCENT,new Range(-100,100),false,"Differential spoilers rudder rate","Sets the amount of deflection that the rudder output will apply to the differential spoilers, as a percentage. The default value of 100 results in full rudder applying full deflection. A value of 0 will result in the differential spoilers exactly following the elevons (no rudder effect)."),
    ELEVON_CH1_REV(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ELEVON_CH2_REV(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ELEVON_MIXING(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ELEVON_OUTPUT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    ELEVON_REVERSE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    FBWA_TDRAG_CHAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"FBWA taildragger channel","This is a RC input channel which when it goes above 1700 enables FBWA taildragger takeoff mode. It should be assigned to a momentary switch. Once this feature is enabled it will stay enabled until the aircraft goes above TKOFF_TDRAG_SPD1 airspeed, changes mode, or the pitch goes above the initial pitch when this is engaged or goes below 0 pitch. When enabled the elevator will be forced to TKOFF_TDRAG_ELEV. This option allows for easier takeoffs on taildraggers in FBWA mode, and also makes it easier to test auto-takeoff steering handling in FBWA. Setting it to 0 disables this option."),
    FBWB_CLIMB_RATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,2.0f,0.1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(1,10),false,"Fly By Wire B altitude change rate","This sets the rate in m/s at which FBWB and CRUISE modes will change its target altitude for full elevator deflection. Note that the actual climb rate of the aircraft can be lower than this, depending on your airspeed and throttle control settings. If you have this parameter set to the default value of 2.0, then holding the elevator at maximum deflection for 10 seconds would change the target altitude by 20 meters."),
    FBWB_ELEV_REV(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Fly By Wire elevator reverse","Reverse sense of elevator in FBWB and CRUISE modes. When set to 0 up elevator (pulling back on the stick) means to lower altitude. When set to 1, up elevator means to raise altitude."),
    FENCE_ACTION(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"None");put(1,"GuidedMode");put(2,"ReportOnly");put(3,"GuidedModeThrPass");put(4,"RTL_Mode");}},false,"Action on geofence breach","What to do on fence breach. If this is set to 0 then no action is taken, and geofencing is disabled. If this is set to 1 then the plane will enter GUIDED mode, with the target waypoint as the fence return point. If this is set to 2 then the fence breach is reported to the ground station, but no other action is taken. If set to 3 then the plane enters guided mode but the pilot retains manual throttle control. If set to 4 the plane enters RTL mode, with the target waypoint as the closest rally point (or home point if there are no rally points)."),
    FENCE_AUTOENABLE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"NoAutoEnable");put(1,"AutoEnable");put(2,"AutoEnableDisableFloorOnly");put(3,"EnableWhenArmed");}},false,"Fence automatic enable","When set to 1, geofence automatically enables after an auto takeoff and automatically disables at the beginning of an auto landing.  When on the ground before takeoff the fence is disabled.  When set to 2, the fence autoenables after an auto takeoff, but only disables the fence floor during landing. It is highly recommended to not use this option for line of sight flying and use a fence enable channel instead. When set to 3 the fence auto-enables when the vehicle is armed and disables when disarmed and arming will fail if the fence cannot be enabled or is outside the fence. Option 3 cannot be used with a non-zero FENCE_MINALT"),
    FENCE_CHANNEL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Fence Channel","RC Channel to use to enable geofence. PWM input above 1750 enables the geofence"),
    FENCE_MAXALT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(0,32767),false,"Fence Maximum Altitude","Maximum altitude allowed before geofence triggers"),
    FENCE_MINALT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(0,32767),false,"Fence Minimum Altitude","Minimum altitude allowed before geofence triggers"),
    FENCE_RETALT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(0,32767),false,"Fence Return Altitude","Altitude the aircraft will transit to when a fence breach occurs.  If FENCE_RETALT is <= 0 then the midpoint between FENCE_MAXALT and FENCE_MINALT is used, unless FENCE_MAXALT < FENCE_MINALT.  If FENCE_MAXALT < FENCE_MINALT AND FENCE_RETALT is <= 0 then ALT_HOLD_RTL is the altitude used on a fence breach."),
    FENCE_RET_RALLY(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"FenceReturnPoint");put(1,"NearestRallyPoint");}},false,"Fence Return to Rally","When set to 1: on fence breach the plane will return to the nearest rally point rather than the fence return point.  If no rally points have been defined the plane will return to the home point."),
    FENCE_TOTAL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Fence Total","Number of geofence points currently loaded"),
    FLAPERON_OUTPUT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    FLAP_1_PERCNT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Flap 1 percentage","The percentage change in flap position when FLAP_1_SPEED is reached. Use zero to disable flaps"),
    FLAP_1_SPEED(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(0,100),false,"Flap 1 speed","The speed in meters per second at which to engage FLAP_1_PERCENT of flaps. Note that FLAP_1_SPEED should be greater than or equal to FLAP_2_SPEED"),

    FLAP_2_PERCNT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Flap 2 percentage","The percentage change in flap position when FLAP_2_SPEED is reached. Use zero to disable flaps"),

    FLAP_2_SPEED(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(0,100),false,"Flap 2 speed","The speed in meters per second at which to engage FLAP_2_PERCENT of flaps. Note that FLAP_1_SPEED should be greater than or equal to FLAP_2_SPEED"),
    FLAP_IN_CHANNEL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    FLAP_SLEWRATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,75,1,MAV_PARAM_UNIT.PERCENT_PER_SECOND,new Range(0,100),false,"Flap slew rate","maximum percentage change in flap output per second. A setting of 25 means to not change the flap by more than 25% of the full flap range in one second. A value of 0 means no rate limiting."),

    FLTMODE1(MAV_PARAM_GROUP_PLANE.ARDUPLANE,11,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode1","Flight mode for switch position 1 (910 to 1230 and above 2049)"),

    FLTMODE2(MAV_PARAM_GROUP_PLANE.ARDUPLANE,11,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode2","Flight mode for switch position 2 (1231 to 1360)"),

    FLTMODE3(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode3","Flight mode for switch position 3 (1361 to 1490)"),

    FLTMODE4(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode4","Flight mode for switch position 4 (1491 to 1620)"),

    FLTMODE5(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode5","Flight mode for switch position 5 (1621 to 1749)"),

    FLTMODE6(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"FlightMode6","Flight mode for switch position 6 (1750 to 2049)"),

    FLTMODE_CH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,8,1,MAV_PARAM_UNIT.UNKNOWN,false,"Flightmode channel","RC Channel to use for flight mode control"),
    FORMAT_VERSION(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new Range(1,255),false,"Eeprom format version number","This value is incremented when changes are made to the eeprom format"),

    FS_BATT_MAH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    FS_BATT_VOLTAGE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    FS_GCS_ENABL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Heartbeat");put(2,"HeartbeatAndREMRSSI");put(3,"HeartbeatAndAUTO");}},false,"GCS failsafe enable","Enable ground control station telemetry failsafe. Failsafe will trigger after FS_LONG_TIMEOUT seconds of no MAVLink heartbeat messages. There are three possible enabled settings. Seeing FS_GCS_ENABL to 1 means that GCS failsafe will be triggered when the aircraft has not received a MAVLink HEARTBEAT message. Setting FS_GCS_ENABL to 2 means that GCS failsafe will be triggered on either a loss of HEARTBEAT messages, or a RADIO_STATUS message from a MAVLink enabled 3DR radio indicating that the ground station is not receiving status updates from the aircraft, which is indicated by the RADIO_STATUS.remrssi field being zero (this may happen if you have a one way link due to asymmetric noise on the ground station and aircraft radios).Setting FS_GCS_ENABL to 3 means that GCS failsafe will be triggered by Heartbeat(like option one), but only in AUTO mode. WARNING: Enabling this option opens up the possibility of your plane going into failsafe mode and running the motor on the ground it it loses contact with your ground station. If this option is enabled on an electric plane then you should enable ARMING_REQUIRED."),

    FS_LONG_ACTN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Continue");put(1,"ReturnToLaunch");put(2,"Glide");put(3,"Deploy Parachute");}},false,"Long failsafe action","The action to take on a long (FS_LONG_TIMEOUT seconds) failsafe event. If the aircraft was in a stabilization or manual mode when failsafe started and a long failsafe occurs then it will change to RTL mode if FS_LONG_ACTN is 0 or 1, and will change to FBWA if FS_LONG_ACTN is set to 2. If the aircraft was in an auto mode (such as AUTO or GUIDED) when the failsafe started then it will continue in the auto mode if FS_LONG_ACTN is set to 0, will change to RTL mode if FS_LONG_ACTN is set to 1 and will change to FBWA mode if FS_LONG_ACTN is set to 2. If FS_LONG_ACTION is set to 3, the parachute will be deployed (make sure the chute is configured and enabled)."),
    FS_LONG_TIMEOUT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,0.5,MAV_PARAM_UNIT.SECOND,new Range(1,300),false,"Long failsafe timeout","The time in seconds that a failsafe condition has to persist before a long failsafe event will occur. This defaults to 5 seconds."),

    FS_SHORT_ACTN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"CIRCLE/no change(if already in AUTO|GUIDED|LOITER)");put(1,"CIRCLE");put(2,"FBWA");put(3,"Disable");}},false,"Short failsafe action","The action to take on a short (FS_SHORT_TIMEOUT) failsafe event. A short failsafe even can be triggered either by loss of RC control (see THR_FS_VALUE) or by loss of GCS control (see FS_GCS_ENABL). If in CIRCLE or RTL mode this parameter is ignored. A short failsafe event in stabilization and manual modes will cause an change to CIRCLE mode if FS_SHORT_ACTN is 0 or 1, and a change to FBWA mode if FS_SHORT_ACTN is 2. In all other modes (AUTO, GUIDED and LOITER) a short failsafe event will cause no mode change if FS_SHORT_ACTN is set to 0, will cause a change to CIRCLE mode if set to 1 and will change to FBWA mode if set to 2. Please see the documentation for FS_LONG_ACTN for the behaviour after FS_LONG_TIMEOUT seconds of failsafe."),
    FS_SHORT_TIMEOUT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1.5f,0.5,MAV_PARAM_UNIT.SECOND,new Range(1,100),false,"Short failsafe timeout","The time in seconds that a failsafe condition has to persist before a short failsafe event will occur. This defaults to 1.5 seconds"),
    GCS_PID_MASK(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Roll");put(1,"Pitch");put(2,"Yaw");put(3,"Steering");put(4,"Landing");}},false,"",""),
    GLIDE_SLOPE_MIN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,15,1,MAV_PARAM_UNIT.METER,new Range(0,1000),false,"Glide slope minimum","This controls the minimum altitude change for a waypoint before a glide slope will be used instead of an immediate altitude change. The default value is 15 meters, which helps to smooth out waypoint missions where small altitude changes happen near waypoints. If you don't want glide slopes to be used in missions then you can set this to zero, which will disable glide slope calculations. Otherwise you can set it to a minimum number of meters of altitude error to the destination waypoint before a glide slope will be used to change altitude."),


    GLIDE_SLOPE_THR(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5.0,1,MAV_PARAM_UNIT.METER,new Range(0,100),false,"Glide slope threshold","This controls the height above the glide slope the plane may be before rebuilding a glide slope. This is useful for smoothing out an autotakeoff"),
    GND_ABS_PRESS(MAV_PARAM_GROUP_PLANE.GND,50705.5625,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GND_ALT_OFFSET(MAV_PARAM_GROUP_PLANE.GND,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GND_TEMP(MAV_PARAM_GROUP_PLANE.GND,25,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GPS_GNSS_MODE(MAV_PARAM_GROUP_PLANE.GPS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GPS_MIN_ELEV(MAV_PARAM_GROUP_PLANE.GPS,-100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GPS_NAVFILTER(MAV_PARAM_GROUP_PLANE.GPS,8,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GPS_SBAS_MODE(MAV_PARAM_GROUP_PLANE.GPS,2,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enable");put(2,"NoChange");}},false,"",""),
    GPS_TYPE(MAV_PARAM_GROUP_PLANE.GPS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    GROUND_STEER_ALT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.1,MAV_PARAM_UNIT.METER,new Range(-100,100),false,"Ground steer altitude","Altitude at which to use the ground steering controller on the rudder. If non-zero then the STEER2SRV controller will be used to control the rudder for altitudes within this limit of the home altitude."),


    GROUND_STEER_DPS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,90,1,MAV_PARAM_UNIT.DEGREE_PER_SECOND,new Range(10,360),false,"Ground steer rate","Ground steering rate in degrees per second for full rudder stick deflection"),
    HIL_ERR_LIMIT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,0.1,MAV_PARAM_UNIT.DEGREE,new Range(0,90),false,"Limit of error in HIL attitude before reset","This controls the maximum error in degrees on any axis before HIL will reset the DCM attitude to match the HIL_STATE attitude. This limit will prevent poor timing on HIL from causing a major attitude error. If the value is zero then no limit applies."),
    HIL_MODE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"HIL mode enable","This enables and disables hardware in the loop mode. If HIL_MODE is 1 then on the next reboot all sensors are replaced with HIL sensors which come from the GCS."),
    HIL_SERVOS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"HIL Servos enable","This controls whether real servo controls are used in HIL mode. If you enable this then the APM will control the real servos in HIL mode. If disabled it will report servo values, but will not output to the real servos. Be careful that your motor and propeller are not connected if you enable this option."),

    INITIAL_MODE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Manual");put(1,"CIRCLE");put(2,"STABILIZE");put(3,"TRAINING");put(4,"ACRO");put(5,"FBWA");put(6,"FBWB");put(7,"CRUISE");put(8,"AUTOTUNE");put(10,"Auto");put(11,"RTL");put(12,"Loiter");put(13,"TAKEOFF");put(14,"AVOID_ADSB");put(15,"Guided");put(17,"QSTABILIZE");put(18,"QHOVER");put(19,"QLOITER");put(20,"QLAND");put(21,"QRTL");put(22,"QAUTOTUNE");put(23,"QACRO");}},false,"Initial flight mode","This selects the mode to start in on boot. This is useful for when you want to start in AUTO mode on boot without a receiver."),
    INS_ACCEL_FILTER(MAV_PARAM_GROUP_PLANE.INS,20,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCOFFS_X(MAV_PARAM_GROUP_PLANE.INS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCOFFS_Y(MAV_PARAM_GROUP_PLANE.INS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCOFFS_Z(MAV_PARAM_GROUP_PLANE.INS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCSCAL_X(MAV_PARAM_GROUP_PLANE.INS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCSCAL_Y(MAV_PARAM_GROUP_PLANE.INS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_ACCSCAL_Z(MAV_PARAM_GROUP_PLANE.INS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_GYROFFS_X(MAV_PARAM_GROUP_PLANE.INS,0.000180917340912856,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_GYROFFS_Y(MAV_PARAM_GROUP_PLANE.INS,-0.0767195895314217,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_GYROFFS_Z(MAV_PARAM_GROUP_PLANE.INS,0.0333446636795998,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_GYRO_FILTER(MAV_PARAM_GROUP_PLANE.INS,20,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_PRODUCT_ID(MAV_PARAM_GROUP_PLANE.INS,88,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INS_USE(MAV_PARAM_GROUP_PLANE.INS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    INVERTEDFLT_CH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    KFF_RDDRMIX(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0.5,0.01,MAV_PARAM_UNIT.UNKNOWN,new Range(0,1),false,"Rudder Mix","Amount of rudder to add during aileron movement. Increase if nose initially yaws away from roll. Reduces adverse yaw."),
    KFF_THR2PTCH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.01,MAV_PARAM_UNIT.UNKNOWN,new Range(0,5),false,"Throttle to Pitch Mix","Degrees of elevator added for full throttle application. Increase to compensate for throttle causing down pitch."),
    LAND_DISARMDELAY(MAV_PARAM_GROUP_PLANE.LAND,20,1,MAV_PARAM_UNIT.SECOND,new Range(0,127),false,"","Landing disarm delay"),
    LAND_FLAP_PERCNT(MAV_PARAM_GROUP_PLANE.LAND,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    LAND_FLARE_ALT(MAV_PARAM_GROUP_PLANE.LAND,3,0.1,MAV_PARAM_UNIT.DEGREE,false,"","Landing flare altitude"),
    LAND_FLARE_SEC(MAV_PARAM_GROUP_PLANE.LAND,2,0.1,MAV_PARAM_UNIT.DEGREE,false,"","Landing flare time"),
    LAND_PITCH_CD(MAV_PARAM_GROUP_PLANE.LAND,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Landing Pitch"),

    LEVEL_ROLL_LIMIT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,1,MAV_PARAM_UNIT.DEGREE,new Range(0,45),false,"Level flight roll limit","This controls the maximum bank angle in degrees during flight modes where level flight is desired, such as in the final stages of landing, and during auto takeoff. This should be a small angle (such as 5 degrees) to prevent a wing hitting the runway during takeoff or landing. Setting this to zero will completely disable heading hold on auto takeoff and final landing approach."),

    LIM_PITCH_MAX(MAV_PARAM_GROUP_PLANE.ARDUPLANE,2000,1,MAV_PARAM_UNIT.CENTIDEGREE,new Range(0,9000),false,"Maximum Pitch Angle","Maximum pitch up angle commanded in modes with stabilized limits."),

    LIM_PITCH_MIN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,-2500,1,MAV_PARAM_UNIT.CENTIDEGREE,new Range(-9000,0),false,"Minimum Pitch Angle","Maximum pitch down angle commanded in modes with stabilized limits"),

    LIM_ROLL_CD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,4500,1,MAV_PARAM_UNIT.CENTIDEGREE,new Range(0,9000),false,"Maximum Bank Angle","Maximum bank angle commanded in modes with stabilized limits. Increase this value for sharper turns, but decrease to prevent accelerated stalls."),
    LOG_BITMASK(MAV_PARAM_GROUP_PLANE.ARDUPLANE,16254,1,MAV_PARAM_UNIT.BITMASK,new HashMap<Number, String>(){{put(0,"Attitude Fast");put(1,"Attitude Medium");put(2,"GPS");put(3,"Performance Monitoring");put(4,"Control Tuning");put(5,"Navigation Tuning");put(6,"Mode");put(7,"IMU");put(8,"Commands");put(9,"Battery");put(10,"Compass");put(11,"TECS");put(12,"Camera");put(13,"RCandServo");put(14,"Sonar");put(15,"Arming");put(16,"Full Logs");}},false,"Log bitmask","Bitmap of what on-board log types to enable. This value is made up of the sum of each of the log types you want to be saved. It is usually best just to enable all log types by setting this to 65535. The individual bits are ATTITUDE_FAST=1, ATTITUDE_MEDIUM=2, GPS=4, PerformanceMonitoring=8, ControlTuning=16, NavigationTuning=32, Mode=64, IMU=128, Commands=256, Battery=512, Compass=1024, TECS=2048, Camera=4096, RCandServo=8192, Sonar=16384, Arming=32768, FullLogs=65535"),
    MAG_ENABLE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    MIN_GNDSPD_CM(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0.1,1,MAV_PARAM_UNIT.CENTIMETER_PER_SECOND,false,"Minimum ground speed","Minimum ground speed in cm/s when under airspeed control"),
    MIS_RESTART(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"ResumeMission");put(1,"RestartMission");}},false,"","Not Yet"),
    MIS_TOTAL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","The number of mission mission items that has been loaded by the ground station. Do not change this manually."),

    MIXING_GAIN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0.5f,1,MAV_PARAM_UNIT.UNKNOWN,new Range(0.5,1.2),false,"Mixing Gain","The gain for the Vtail and elevon output mixers. The default is 0.5, which ensures that the mixer doesn't saturate, allowing both input channels to go to extremes while retaining control over the output. Hardware mixers often have a 1.0 gain, which gives more servo throw, but can saturate. If you don't have enough throw on your servos with VTAIL_OUTPUT or ELEVON_OUTPUT enabled then you can raise the gain using MIXING_GAIN. The mixer allows outputs in the range 900 to 2100 microseconds."),
    MIXING_OFFSET(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new Range(-1000,1000),false,"Mixing Offset","The offset for the Vtail and elevon output mixers, as a percentage. This can be used in combination with MIXING_GAIN to configure how the control surfaces respond to input. The response to aileron or elevator input can be increased by setting this parameter to a positive or negative value. A common usage is to enter a positive value to increase the aileron response of the elevons of a flying wing. The default value of zero will leave the aileron-input response equal to the elevator-input response."),
    NAVL1_DAMPING(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0.75,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    NAVL1_PERIOD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,20,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    NAV_CONTROLLER(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Default");put(1,"L1Controller");}},false,"Navigation controller selection","Which navigation controller to enable. Currently the only navigation controller available is L1. From time to time other experimental controllers will be added which are selected using this parameter."),
    OVERRIDE_CHAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"IO override channel","If set to a non-zero value then this is an RC input channel number to use for giving IO manual control in case the main FMU microcontroller on a board with a IO co-processor fails. When this RC input channel goes above 1750 the FMU microcontroller will no longer be involved in controlling the servos and instead the IO microcontroller will directly control the servos. Note that IO manual control will be automatically activated if the FMU crashes for any reason. This parameter allows you to test for correct manual behaviour without actually crashing the FMU. This parameter is can be set to a non-zero value either for ground testing purposes or for giving the effect of an external override control board. Please also see the docs on OVERRIDE_SAFETY. Note that you may set OVERRIDE_CHAN to the same channel as FLTMODE_CH to get IO based override when in flight mode 6. Note that when override is triggered due to a FMU crash the 6 auxiliary output channels on Pixhawk will no longer be updated, so all the flight controls you need must be assigned to the first 8 channels."),
    OVERRIDE_SAFETY(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"IO override safety switch","This controls whether the safety switch is turned off when you activate override with OVERRIDE_CHAN. When set to 1 the safety switch is de-activated (activating the servos) then a IO override is triggered. In that case the safety remains de-activated after override is disabled. If OVERRIDE_SAFETTY is set to 0 then the safety switch state does not change. Note that regardless of the value of this parameter the servos will be active while override is active."),
    PTCH2SRV_D(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0.0199999995529652,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_FF(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_I(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0.0399999991059303,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_IMAX(MAV_PARAM_GROUP_PLANE.PTCH2SRV,3000,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_P(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0.400000005960465,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_RLL(MAV_PARAM_GROUP_PLANE.PTCH2SRV,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_RMAX_DN(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_RMAX_UP(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    PTCH2SRV_TCONST(MAV_PARAM_GROUP_PLANE.PTCH2SRV,0.5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RALLY_INCL_HOME(MAV_PARAM_GROUP_PLANE.RALLY,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RALLY_LIMIT_KM(MAV_PARAM_GROUP_PLANE.RALLY,5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RALLY_TOTAL(MAV_PARAM_GROUP_PLANE.RALLY,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_DZ(MAV_PARAM_GROUP_PLANE.RC10,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_FUNCTION(MAV_PARAM_GROUP_PLANE.RC10,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_MAX(MAV_PARAM_GROUP_PLANE.RC10,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_MIN(MAV_PARAM_GROUP_PLANE.RC10,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_REV(MAV_PARAM_GROUP_PLANE.RC10,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC10_TRIM(MAV_PARAM_GROUP_PLANE.RC10,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_DZ(MAV_PARAM_GROUP_PLANE.RC11,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_FUNCTION(MAV_PARAM_GROUP_PLANE.RC11,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_MAX(MAV_PARAM_GROUP_PLANE.RC11,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_MIN(MAV_PARAM_GROUP_PLANE.RC11,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_REV(MAV_PARAM_GROUP_PLANE.RC11,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC11_TRIM(MAV_PARAM_GROUP_PLANE.RC11,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC1_DZ(MAV_PARAM_GROUP_PLANE.RC1,30,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC1_MAX(MAV_PARAM_GROUP_PLANE.RC1,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC1_MIN(MAV_PARAM_GROUP_PLANE.RC1,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC1_REV(MAV_PARAM_GROUP_PLANE.RC1,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC1_TRIM(MAV_PARAM_GROUP_PLANE.RC1,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC2_DZ(MAV_PARAM_GROUP_PLANE.RC2,30,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC2_MAX(MAV_PARAM_GROUP_PLANE.RC2,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC2_MIN(MAV_PARAM_GROUP_PLANE.RC2,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC2_REV(MAV_PARAM_GROUP_PLANE.RC2,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC2_TRIM(MAV_PARAM_GROUP_PLANE.RC2,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC3_DZ(MAV_PARAM_GROUP_PLANE.RC3,30,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC3_MAX(MAV_PARAM_GROUP_PLANE.RC3,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC3_MIN(MAV_PARAM_GROUP_PLANE.RC3,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC3_REV(MAV_PARAM_GROUP_PLANE.RC3,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC3_TRIM(MAV_PARAM_GROUP_PLANE.RC3,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC4_DZ(MAV_PARAM_GROUP_PLANE.RC4,30,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC4_MAX(MAV_PARAM_GROUP_PLANE.RC4,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC4_MIN(MAV_PARAM_GROUP_PLANE.RC4,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC4_REV(MAV_PARAM_GROUP_PLANE.RC4,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC4_TRIM(MAV_PARAM_GROUP_PLANE.RC4,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_DZ(MAV_PARAM_GROUP_PLANE.RC5,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_FUNCTION(MAV_PARAM_GROUP_PLANE.RC5,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_MAX(MAV_PARAM_GROUP_PLANE.RC5,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_MIN(MAV_PARAM_GROUP_PLANE.RC5,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_REV(MAV_PARAM_GROUP_PLANE.RC5,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC5_TRIM(MAV_PARAM_GROUP_PLANE.RC5,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_DZ(MAV_PARAM_GROUP_PLANE.RC6,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_FUNCTION(MAV_PARAM_GROUP_PLANE.RC6,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_MAX(MAV_PARAM_GROUP_PLANE.RC6,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_MIN(MAV_PARAM_GROUP_PLANE.RC6,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_REV(MAV_PARAM_GROUP_PLANE.RC6,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC6_TRIM(MAV_PARAM_GROUP_PLANE.RC6,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_DZ(MAV_PARAM_GROUP_PLANE.RC7,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_FUNCTION(MAV_PARAM_GROUP_PLANE.RC7,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_MAX(MAV_PARAM_GROUP_PLANE.RC7,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_MIN(MAV_PARAM_GROUP_PLANE.RC7,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_REV(MAV_PARAM_GROUP_PLANE.RC7,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC7_TRIM(MAV_PARAM_GROUP_PLANE.RC7,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_DZ(MAV_PARAM_GROUP_PLANE.RC8,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_FUNCTION(MAV_PARAM_GROUP_PLANE.RC8,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_MAX(MAV_PARAM_GROUP_PLANE.RC8,1900,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_MIN(MAV_PARAM_GROUP_PLANE.RC8,1100,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_REV(MAV_PARAM_GROUP_PLANE.RC8,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RC8_TRIM(MAV_PARAM_GROUP_PLANE.RC8,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RCMAP_PITCH(MAV_PARAM_GROUP_PLANE.RCMAP,2,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RCMAP_ROLL(MAV_PARAM_GROUP_PLANE.RCMAP,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RCMAP_THROTTLE(MAV_PARAM_GROUP_PLANE.RCMAP,3,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RCMAP_YAW(MAV_PARAM_GROUP_PLANE.RCMAP,4,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RELAY_DEFAULT(MAV_PARAM_GROUP_PLANE.RALLY,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RELAY_PIN(MAV_PARAM_GROUP_PLANE.RALLY,13,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RELAY_PIN2(MAV_PARAM_GROUP_PLANE.RALLY,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RELAY_PIN3(MAV_PARAM_GROUP_PLANE.RALLY,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RELAY_PIN4(MAV_PARAM_GROUP_PLANE.RALLY,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_D(MAV_PARAM_GROUP_PLANE.RLL2SRV,0.0199999995529652,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_FF(MAV_PARAM_GROUP_PLANE.RLL2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_I(MAV_PARAM_GROUP_PLANE.RLL2SRV,0.0399999991059303,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_IMAX(MAV_PARAM_GROUP_PLANE.RLL2SRV,3000,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_P(MAV_PARAM_GROUP_PLANE.RLL2SRV,0.400000005960465,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_RMAX(MAV_PARAM_GROUP_PLANE.RLL2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RLL2SRV_TCONST(MAV_PARAM_GROUP_PLANE.RLL2SRV,0.5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RNGFND_LANDING(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RSSI_PIN(MAV_PARAM_GROUP_PLANE.RSSI,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RSSI_RANGE(MAV_PARAM_GROUP_PLANE.RSSI,5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    RST_MISSION_CH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Reset Mission Channel","Enables a channel to reset the mission to the first waypoint. Mission restart is triggered by channel rising above 1750 PWM. 0 disables."),
    RST_SWITCH_CH(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"Reset Switch Channel","RC channel to use to reset to last flight mode	after geofence takeover."),
    RTL_AUTOLAND(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disable");put(1,"Enable - go HOME then land");put(2,"Enable - go directly to landing sequence");}},false,"RTL auto land","Automatically begin landing sequence after arriving at RTL location. This requires the addition of a DO_LAND_START mission item, which acts as a marker for the start of a landing sequence. The closest landing sequence will be chosen to the current location."),
    RTL_RADIUS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(-32767,32767),false,"RTL loiter radius","Defines the radius of the loiter circle when in RTL mode. If this is zero then WP_LOITER_RAD is used. If the radius is negative then a counter-clockwise is used. If positive then a clockwise loiter is used."),
    RUDDER_ONLY(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Rudder only aircraft","Enable rudder only mode. The rudder will control attitude in attitude controlled modes (such as FBWA). You should setup your transmitter to send roll stick inputs to the RCMAP_YAW channel (normally channel 4). The rudder servo should be attached to the RCMAP_YAW channel as well. Note that automatic ground steering will be disabled for rudder only aircraft. You should also set KFF_RDDRMIX to 1.0. You will also need to setup the YAW2SRV_DAMP yaw damping appropriately for your aircraft. A value of 0.5 for YAW2SRV_DAMP is a good starting point."),

    SCALING_SPEED(MAV_PARAM_GROUP_PLANE.ARDUPLANE,15,1,MAV_PARAM_UNIT.METER_PER_SECOND,false,"speed used for speed scaling calculations","Airspeed in m/s to use when calculating surface speed scaling. Note that changing this value will affect all PID values"),
    SCHED_DEBUG(MAV_PARAM_GROUP_PLANE.SCHED,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SERIAL0_BAUD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,115,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"1200");put(2,"2400");put(4,"4800");put(9,"9600");put(19,"19200");put(38,"38400");put(57,"57600");put(111,"111100");put(115,"115200");put(256,"256000");put(460,"460800");put(500,"500000");put(921,"921600");put(1500,"1500000");}},false,"",""),
    SERIAL0_PROTOCOL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(1,"MAVlink1");put(2,"MAVlink2");}},false,"",""),
    SERIAL1_BAUD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,57,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"1200");put(2,"2400");put(4,"4800");put(9,"9600");put(19,"19200");put(38,"38400");put(57,"57600");put(111,"111100");put(115,"115200");put(256,"256000");put(460,"460800");put(500,"500000");put(921,"921600");put(1500,"1500000");}},false,"",""),
    SERIAL1_PROTOCOL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(1,"MAVlink1");put(2,"MAVlink2");}},false,"",""),
    SERIAL2_BAUD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,57,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"1200");put(2,"2400");put(4,"4800");put(9,"9600");put(19,"19200");put(38,"38400");put(57,"57600");put(111,"111100");put(115,"115200");put(256,"256000");put(460,"460800");put(500,"500000");put(921,"921600");put(1500,"1500000");}},false,"",""),
    SERIAL2_PROTOCOL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(1,"MAVlink1");put(2,"MAVlink2");}},false,"",""),
    SERIAL3_BAUD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,38,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"1200");put(2,"2400");put(4,"4800");put(9,"9600");put(19,"19200");put(38,"38400");put(57,"57600");put(111,"111100");put(115,"115200");put(256,"256000");put(460,"460800");put(500,"500000");put(921,"921600");put(1500,"1500000");}},false,"",""),
    SERIAL3_PROTOCOL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(1,"MAVlink1");put(2,"MAVlink2");}},false,"",""),
    SERIAL4_BAUD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,38,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"1200");put(2,"2400");put(4,"4800");put(9,"9600");put(19,"19200");put(38,"38400");put(57,"57600");put(111,"111100");put(115,"115200");put(256,"256000");put(460,"460800");put(500,"500000");put(921,"921600");put(1500,"1500000");}},false,"",""),
    SERIAL4_PROTOCOL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,5,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(1,"MAVlink1");put(2,"MAVlink2");}},false,"",""),
    SKIP_GYRO_CAL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Unknown"),
    SR0_EXTRA1(MAV_PARAM_GROUP_PLANE.ARDUPLANE,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_EXTRA2(MAV_PARAM_GROUP_PLANE.ARDUPLANE,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_EXTRA3(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_EXT_STAT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_PARAMS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_POSITION(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_RAW_CTRL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_RAW_SENS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR0_RC_CHAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_EXTRA1(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_EXTRA2(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_EXTRA3(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_EXT_STAT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_PARAMS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_POSITION(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_RAW_CTRL(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_RAW_SENS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    SR1_RC_CHAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    STAB_PITCH_DOWN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,2.0f,0.1,MAV_PARAM_UNIT.DEGREE,new Range(0,15),false,"Low throttle pitch down trim","Degrees of down pitch added when throttle is below TRIM_THROTTLE in FBWA and AUTOTUNE modes. Scales linearly so full value is added when THR_MIN is reached. Helps to keep airspeed higher in glides or landing approaches and prevents accidental stalls. 2 degrees recommended for most planes."),
    STALL_PREVENTION(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Enable stall prevention","Enables roll limits at low airspeed in roll limiting flight modes. Roll limits based on aerodynamic load factor in turns and scale on ARSPD_FBW_MIN that must be set correctly. Without airspeed sensor, uses synthetic airspeed from wind speed estimate that may both be inaccurate."),
    STEER2SRV_D(MAV_PARAM_GROUP_PLANE.STEER2SRV,0.00499999988824129,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_FF(MAV_PARAM_GROUP_PLANE.STEER2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_I(MAV_PARAM_GROUP_PLANE.STEER2SRV,0.200000002980232,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_IMAX(MAV_PARAM_GROUP_PLANE.STEER2SRV,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_MINSPD(MAV_PARAM_GROUP_PLANE.STEER2SRV,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_P(MAV_PARAM_GROUP_PLANE.STEER2SRV,1.79999995231628,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    STEER2SRV_TCONST(MAV_PARAM_GROUP_PLANE.STEER2SRV,0.75,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    STICK_MIXING(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"FBWMixing");put(2,"DirectMixing");}},false,"Stick Mixing","When enabled, this adds user stick input to the control surfaces in auto modes, allowing the user to have some degree of flight control without changing modes.  There are two types of stick mixing available. If you set STICK_MIXING to 1 then it will use \"fly by wire\" mixing, which controls the roll and pitch in the same way that the FBWA mode does. This is the safest option if you usually fly ArduPlane in FBWA or FBWB mode. If you set STICK_MIXING to 2 then it will enable direct mixing mode, which is what the STABILIZE mode uses. That will allow for much more extreme maneuvers while in AUTO mode."),

    SYSID_MYGCS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,255,1,MAV_PARAM_UNIT.UNKNOWN,new Range(1,255),false,"Ground station MAVLink system ID","The identifier of the ground station in the MAVLink protocol. Don't change this unless you also modify the ground station to match."),
    SYSID_SW_TYPE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Unknown"),
    SYSID_THISMAV(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,new Range(1,255),false,"MAVLink system ID of this vehicle","Allows setting an individual MAVLink system id for this vehicle to distinguish it from others on the same network"),
    SYS_NUM_RESETS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,true,"Num Resets","Number of APM board resets"),
    TECS_CLMB_MAX(MAV_PARAM_GROUP_PLANE.TECS,5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_HGT_OMEGA(MAV_PARAM_GROUP_PLANE.TECS,3,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_INTEG_GAIN(MAV_PARAM_GROUP_PLANE.TECS,0.100000001490116,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_ARSPD(MAV_PARAM_GROUP_PLANE.TECS,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_DAMP(MAV_PARAM_GROUP_PLANE.TECS,0.5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_PMAX(MAV_PARAM_GROUP_PLANE.TECS,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_SINK(MAV_PARAM_GROUP_PLANE.TECS,0.25,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_SPDWGT(MAV_PARAM_GROUP_PLANE.TECS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_TCONST(MAV_PARAM_GROUP_PLANE.TECS,2,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_LAND_THR(MAV_PARAM_GROUP_PLANE.TECS,-1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_PITCH_MAX(MAV_PARAM_GROUP_PLANE.TECS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_PITCH_MIN(MAV_PARAM_GROUP_PLANE.TECS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_PTCH_DAMP(MAV_PARAM_GROUP_PLANE.TECS,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_RLL2THR(MAV_PARAM_GROUP_PLANE.TECS,10,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_SINK_MAX(MAV_PARAM_GROUP_PLANE.TECS,5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_SINK_MIN(MAV_PARAM_GROUP_PLANE.TECS,2,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_SPDWEIGHT(MAV_PARAM_GROUP_PLANE.TECS,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_SPD_OMEGA(MAV_PARAM_GROUP_PLANE.TECS,2,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_THR_DAMP(MAV_PARAM_GROUP_PLANE.TECS,0.5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_TIME_CONST(MAV_PARAM_GROUP_PLANE.TECS,5,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TECS_VERT_ACC(MAV_PARAM_GROUP_PLANE.TECS,7,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    TELEM_DELAY(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.SECOND,new Range(0,30),false,"Telemetry startup delay","The amount of time (in seconds) to delay radio telemetry to prevent an Xbee bricking on power up"),

    TERRAIN_FOLLOW(MAV_PARAM_GROUP_PLANE.TERRAIN,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Use terrain following","This enables terrain following for CRUISE mode, FBWB mode, RTL and for rally points. To use this option you also need to set TERRAIN_ENABLE to 1, which enables terrain data fetching from the GCS, and you need to have a GCS that supports sending terrain data to the aircraft. When terrain following is enabled then CRUISE and FBWB mode will hold height above terrain rather than height above home. In RTL the return to launch altitude will be considered to be a height above the terrain. Rally point altitudes will be taken as height above the terrain. This option does not affect mission items, which have a per-waypoint flag for whether they are height above home or height above the terrain. To use terrain following missions you need a ground station which can set the waypoint type to be a terrain height waypoint when creating the mission."),
    TERRAIN_LOOKAHD(MAV_PARAM_GROUP_PLANE.TERRAIN,2000,1,MAV_PARAM_UNIT.METER,new Range(0,10000),false,"Terrain lookahead","This controls how far ahead the terrain following code looks to ensure it stays above upcoming terrain. A value of zero means no lookahead, so the controller will track only the terrain directly below the aircraft. The lookahead will never extend beyond the next waypoint when in AUTO mode."),

    THROTTLE_NUDGE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Throttle nudge enable","When enabled, this uses the throttle input in auto-throttle modes to 'nudge' the throttle or airspeed to higher or lower values. When you have an airspeed sensor the nudge affects the target airspeed, so that throttle inputs above 50% will increase the target airspeed from TRIM_ARSPD_CM up to a maximum of ARSPD_FBW_MAX. When no airspeed sensor is enabled the throttle nudge will push up the target throttle for throttle inputs above 50%."),

    THR_FAILSAFE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,1,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Throttle and RC Failsafe Enable","This enables failsafe on loss of RC input. How this is detected depends on the type of RC receiver being used. For older radios an input below the THR_FS_VALUE is used to trigger failsafe. For newer radios the failsafe trigger is part of the protocol between the autopilot and receiver."),

    THR_FS_VALUE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,950,1,MAV_PARAM_UNIT.UNKNOWN,new Range(925,2200),false,"Throttle Failsafe Value","The PWM level on the throttle input channel below which throttle failsafe triggers. Note that this should be well below the normal minimum for your throttle channel."),

    THR_MAX(MAV_PARAM_GROUP_PLANE.ARDUPLANE,75,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Maximum Throttle","Maximum throttle percentage used in automatic throttle modes."),

    THR_MIN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(-100,100),false,"Minimum Throttle","Minimum throttle percentage used in all modes except manual, provided THR_PASS_STAB is not set. Negative values allow reverse thrust if hardware supports it."),
    THR_PASS_STAB(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Throttle passthru in stabilize","If this is set then when in STABILIZE, FBWA or ACRO modes the throttle is a direct passthru from the transmitter. This means the THR_MIN and THR_MAX settings are not used in these modes. This is useful for petrol engines where you setup a throttle cut switch that suppresses the throttle below the normal minimum."),

    THR_SLEWRATE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,100,1,MAV_PARAM_UNIT.PERCENT_PER_SECOND,new Range(0,127),false,"Throttle slew rate","Maximum change in throttle percentage per second. Lower limit  based on 1 microsend of servo increase per loop. Divide SCHED_LOOP_RATE by approximately 10 to determine minimum achievable value."),


    THR_SUPP_MAN(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Throttle suppress manual passthru","When throttle is suppressed in auto mode it is normally forced to zero. If you enable this option, then while suppressed it will be manual throttle. This is useful on petrol engines to hold the idle throttle manually while waiting for takeoff"),
    TKOFF_FLAP_PCNT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Takeoff flap percentage","The amount of flaps (as a percentage) to apply in automatic takeoff"),

    TKOFF_PLIM_SEC(MAV_PARAM_GROUP_PLANE.ARDUPLANE,2,0.5,MAV_PARAM_UNIT.SECOND,new Range(0,10),false,"Takeoff pitch limit reduction","This parameter reduces the pitch minimum limit of an auto-takeoff just a few seconds before it reaches the target altitude. This reduces overshoot by allowing the flight controller to start leveling off a few seconds before reaching the target height. When set to zero, the mission pitch min is enforced all the way to and through the target altitude, otherwise the pitch min slowly reduces to zero in the final segment. This is the pitch_min, not the demand. The flight controller should still be commanding to gain altitude to finish the takeoff but with this param it is not forcing it higher than it wants to be."),
    TKOFF_ROTATE_SPD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(0,30),false,"Takeoff rotate speed","This parameter sets the airspeed at which the aircraft will \"rotate\", setting climb pitch specified in the mission. If TKOFF_ROTATE_SPD is zero then the climb pitch will be used as soon as takeoff is started. For hand launch and catapult launches a TKOFF_ROTATE_SPD of zero should be set. For all ground launches TKOFF_ROTATE_SPD should be set above the stall speed, usually by about 10 to 30 percent"),

    TKOFF_TDRAG_ELEV(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(-100,100),false,"Takeoff tail dragger elevator","This parameter sets the amount of elevator to apply during the initial stage of a takeoff. It is used to hold the tail wheel of a taildragger on the ground during the initial takeoff stage to give maximum steering. This option should be combined with the TKOFF_TDRAG_SPD1 option and the GROUND_STEER_ALT option along with tuning of the ground steering controller. A value of zero means to bypass the initial \"tail hold\" stage of takeoff. Set to zero for hand and catapult launch. For tail-draggers you should normally set this to 100, meaning full up elevator during the initial stage of takeoff. For most tricycle undercarriage aircraft a value of zero will work well, but for some tricycle aircraft a small negative value (say around -20 to -30) will apply down elevator which will hold the nose wheel firmly on the ground during initial acceleration. Only use a negative value if you find that the nosewheel doesn't grip well during takeoff. Too much down elevator on a tricycle undercarriage may cause instability in steering as the plane pivots around the nosewheel. Add down elevator 10 percent at a time."),

    TKOFF_TDRAG_SPD1(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(0,30),false,"Takeoff tail dragger speed1","This parameter sets the airspeed at which to stop holding the tail down and transition to rudder control of steering on the ground. When TKOFF_TDRAG_SPD1 is reached the pitch of the aircraft will be held level until TKOFF_ROTATE_SPD is reached, at which point the takeoff pitch specified in the mission will be used to \"rotate\" the pitch for takeoff climb. Set TKOFF_TDRAG_SPD1 to zero to go straight to rotation. This should be set to zero for hand launch and catapult launch. It should also be set to zero for tricycle undercarriages unless you are using the method above to genetly hold the nose wheel down. For tail dragger aircraft it should be set just below the stall speed."),


    TKOFF_THR_DELAY(MAV_PARAM_GROUP_PLANE.ARDUPLANE,2,1,MAV_PARAM_UNIT.DECISECOND,new Range(0,127),false,"Takeoff throttle delay","This parameter sets the time delay (in 1/10ths of a second) that the ground speed check is delayed after the forward acceleration check controlled by TKOFF_THR_MINACC has passed. For hand launches with pusher propellers it is essential that this is set to a value of no less than 2 (0.2 seconds) to ensure that the aircraft is safely clear of the throwers arm before the motor can start. For bungee launches a larger value can be used (such as 30) to give time for the bungee to release from the aircraft before the motor is started."),
    TKOFF_THR_MAX(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Maximum Throttle for takeoff","The maximum throttle setting during automatic takeoff. If this is zero then THR_MAX is used for takeoff as well."),

    TKOFF_THR_MINACC(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.1,MAV_PARAM_UNIT.METER_PER_SECOND_PER_SECOND,new Range(0,30),false,"Takeoff throttle min acceleration","Minimum forward acceleration in m/s/s before arming the ground speed check in auto-takeoff. This is meant to be used for hand launches. Setting this value to 0 disables the acceleration test which means the ground speed check will always be armed which could allow GPS velocity jumps to start the engine. For hand launches and bungee launches this should be set to around 15. Also see TKOFF_ACCEL_CNT paramter for control of full \"shake to arm\"."),

    TKOFF_THR_MINSPD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,0.1,MAV_PARAM_UNIT.METER_PER_SECOND,new Range(0,30),false,"Takeoff throttle min speed","Minimum GPS ground speed in m/s used by the speed check that un-suppresses throttle in auto-takeoff. This can be be used for catapult launches where you want the motor to engage only after the plane leaves the catapult, but it is preferable to use the TKOFF_THR_MINACC and TKOFF_THR_DELAY parameters for catapult launches due to the errors associated with GPS measurements. For hand launches with a pusher prop it is strongly advised that this parameter be set to a value no less than 4 m/s to provide additional protection against premature motor start. Note that the GPS velocity will lag the real velocity by about 0.5 seconds. The ground speed check is delayed by the TKOFF_THR_DELAY parameter."),

    TKOFF_THR_SLEW(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.PERCENT_PER_SECOND,new Range(-1,127),false,"Takeoff throttle slew rate","This parameter sets the slew rate for the throttle during auto takeoff. When this is zero the THR_SLEWRATE parameter is used during takeoff. For rolling takeoffs it can be a good idea to set a lower slewrate for takeoff to give a slower acceleration which can improve ground steering control. The value is a percentage throttle change per second, so a value of 20 means to advance the throttle over 5 seconds on takeoff. Values below 20 are not recommended as they may cause the plane to try to climb out with too little throttle. A value of -1 means no limit on slew rate in takeoff."),

    TRIM_ARSPD_CM(MAV_PARAM_GROUP_PLANE.ARDUPLANE,45,1,MAV_PARAM_UNIT.CENTIMETER_PER_SECOND,false,"Target airspeed","Target airspeed in cm/s in automatic throttle modes. Value is as an indicated (calibrated/apparent) airspeed."),

    TRIM_AUTO(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.FLAGS,new HashMap<Number, String>(){{put(0,"Disabled");put(1,"Enabled");}},false,"Automatic trim adjustment","Enables the setting SERVOn_TRIM values to current levels when switching out of MANUAL mode. Should not be left on as mode switches while the plane is rolling or pitching can cause invalid trim values and subsequent unstable behavior. See newer and safer SERVO_AUTO_TRIM parameter for automated results."),

    TRIM_PITCH_CD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.CENTIDEGREE,false,"Pitch angle offset","Offset applied to AHRS pitch used for in-flight pitch trimming. Correct ground leveling is better than changing this parameter."),
    TRIM_RC_AT_START(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    TRIM_THROTTLE(MAV_PARAM_GROUP_PLANE.ARDUPLANE,45,1,MAV_PARAM_UNIT.PERCENT,new Range(0,100),false,"Throttle cruise percentage","Target percentage of throttle to apply for flight in automatic throttle modes and throttle percentage that maintains TRIM_ARSPD_CM. Caution: low battery voltages at the end of flights may require higher throttle to maintain airspeed."),
    USE_REV_THRUST(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.BITMASK,new HashMap<Number, String>(){{put(0,"Never");put(1,"Auto Always");put(2,"Auto Landing");}},false,"Bitmask for when to allow negative reverse thrust","This controls when to use reverse thrust. If set to zero then reverse thrust is never used. If set to a non-zero value then the bits correspond to flight stages where reverse thrust may be used. Note that reverse thrust is only ever auto-enabled in auto-throttle modes. In modes where throttle control is pilot controlled the ability to do reverse thrust is controlled by throttle stick input. The most commonly used value for USE_REV_THRUST is 2, which means AUTO_LAND only. That enables reverse thrust in the landing stage of AUTO mode. Another common choice is 1, which means to use reverse thrust in all auto flight stages."),
    VTAIL_OUTPUT(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    WP_LOITER_RAD(MAV_PARAM_GROUP_PLANE.ARDUPLANE,60,1,MAV_PARAM_UNIT.METER,new Range(-32767,32767),false,"Waypoint Loiter Radius","Defines the distance from the waypoint center, the plane will maintain during a loiter. If you set this value to a negative number then the default loiter direction will be counter-clockwise instead of clockwise."),
    WP_MAX_RADIUS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,0,1,MAV_PARAM_UNIT.METER,new Range(0,32767),false,"Waypoint Maximum Radius","Sets the maximum distance to a waypoint for the waypoint to be considered complete. This overrides the \"cross the finish line\" logic that is normally used to consider a waypoint complete. For normal AUTO behaviour this parameter should be set to zero. Using a non-zero value is only recommended when it is critical that the aircraft does approach within the given radius, and should loop around until it has done so. This can cause the aircraft to loop forever if its turn radius is greater than the maximum radius set."),


    WP_RADIUS(MAV_PARAM_GROUP_PLANE.ARDUPLANE,90,1,MAV_PARAM_UNIT.METER,new Range(1,32767),false,"Waypoint Radius","Defines the maximum distance from a waypoint that when crossed indicates the waypoint may be complete. To avoid the aircraft looping around the waypoint in case it misses by more than the WP_RADIUS an additional check is made to see if the aircraft has crossed a \"finish line\" passing through the waypoint and perpendicular to the flight path from the previous waypoint. If that finish line is crossed then the waypoint is considered complete. Note that the navigation controller may decide to turn later than WP_RADIUS before a waypoint, based on how sharp the turn is and the speed of the aircraft. It is safe to set WP_RADIUS much larger than the usual turn radius of your aircraft and the navigation controller will work out when to turn. If you set WP_RADIUS too small then you will tend to overshoot the turns."),
    YAW2SRV_DAMP(MAV_PARAM_GROUP_PLANE.YAW2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    YAW2SRV_IMAX(MAV_PARAM_GROUP_PLANE.YAW2SRV,1500,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    YAW2SRV_INT(MAV_PARAM_GROUP_PLANE.YAW2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    YAW2SRV_RLL(MAV_PARAM_GROUP_PLANE.YAW2SRV,1,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),
    YAW2SRV_SLIP(MAV_PARAM_GROUP_PLANE.YAW2SRV,0,1,MAV_PARAM_UNIT.UNKNOWN,false,"","Not Yet"),

    ;

    private final Number defaultValue;
    private final Number increment;
    private final MAV_PARAM_UNIT unit;
    private final Range range;
    private final boolean readOnly;
    private final String title;
    private final String description;
    private final Map<Number, String> options;
    private final MAV_PARAM_GROUP_I group;

    MAV_PARAM_PLANE(MAV_PARAM_GROUP_I group, Number defaultValue, Number increment, MAV_PARAM_UNIT unit, Map<Number, String> options, boolean readOnly, String title, String description) {
        this.group = group;
        this.defaultValue = defaultValue;
        this.increment = increment;
        this.unit = unit;
        this.options = options;
        this.range = null;
        this.readOnly = readOnly;
        this.title = title;
        this.description = description;
    }

    MAV_PARAM_PLANE(MAV_PARAM_GROUP_I group, Number defaultValue, Number increment, MAV_PARAM_UNIT unit, Range range, boolean readOnly, String title, String description) {
        this.group = group;
        this.defaultValue = defaultValue;
        this.increment = increment;
        this.unit = unit;
        this.options = null;
        this.range = range;
        this.readOnly = readOnly;
        this.title = title;
        this.description = description;
    }

    MAV_PARAM_PLANE(MAV_PARAM_GROUP_I group, Number defaultValue, Number increment, MAV_PARAM_UNIT unit, boolean readOnly, String title, String description) {
        this.group = group;
        this.defaultValue = defaultValue;
        this.increment = increment;
        this.unit = unit;
        this.options = null;
        this.range = null;
        this.readOnly = readOnly;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {

        return "MAV_PARAMETERS_LIST{" +
                "name=" + this.name() +
                ", defaultValue=" + defaultValue +
                ", increment=" + increment +
                ", unit=" + unit +
                ", readOnly=" + readOnly +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public MAV_PARAM_GROUP_I getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public Number getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Number getIncrement() {
        return increment;
    }

    @Override
    public MAV_PARAM_UNIT getUnit() {
        return unit;
    }

    @Override
    public Range getRange() {
        return range;
    }

    @Override
    public Map<Number, String> getOptions() {
        return options;
    }

    @Override
    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
