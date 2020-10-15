# ArduCopter Parameters

## ACRO_BAL_PITCH: Acro Balance Pitch
rate at which pitch angle returns to level in acro mode. A higher value causes the vehicle to return to level faster.
Unit: 
Default Value: 1

Min | Max
--- | ---
 0 | 3

## ACRO_BAL_ROLL: Acro Balance Roll
rate at which roll angle returns to level in acro mode. A higher value causes the vehicle to return to level faster.
Unit: 
Default Value: 1

Min | Max
--- | ---
 0 | 3

## ACRO_EXPO: Acro Expo
Acro roll/pitch Expo to allow faster rotation when stick at edges
Unit: 
Default Value: 0.3

Key | Value
--- | ---
0 | Disabled
0.3 | Medium
0.5 | Very High
0.1 | Very Low
0.2 | Low
0.4 | High


## ACRO_RP_P: 
Converts pilot roll and pitch into a desired rate of rotation in ACRO and SPORT mode. Higher values mean faster rate of rotation.
Unit: 
Default Value: 4.5


## ACRO_TRAINER: Acro Trainer

Unit: list
Default Value: 2

Key | Value
--- | ---
0 | Disabled
1 | Leveling
2 | LevelingAndLimited


## ACRO_YAW_P: 
Converts pilot yaw input into a desired rate of rotation in ACRO, Stabilize and SPORT modes. Higher values mean faster rate of rotation.
Unit: 
Default Value: 4.5


## AHRS_COMP_BETA: AHRS Velocity Complementary Filter Beta Coefficient
This controls the time constant for the cross-over frequency used to fuse AHRS (MAV_PARAM_GROUP.ARDUCOPTER,airspeed and heading) and GPS data to estimate ground velocity. Time constant is 0.1/beta. A larger time constant will use GPS data less and a small time constant will use air data less.
Unit: 
Default Value: 0.1

Min | Max
--- | ---
 0.001 | 0.5

## AHRS_GPS_GAIN: AHRS GPS gain
This controls how how much to use the GPS to correct the attitude. This should never be set to zero for a plane as it would result in the plane losing control in turns. For a plane please use the default value of 1.0.
Unit: 
Default Value: 1

Min | Max
--- | ---
 0.0 | 1.0

## AHRS_GPS_MINSATS: AHRS GPS Minimum satellites
Minimum number of satellites visible to use GPS for velocity based corrections attitude correction. This defaults to 6, which is about the point at which the velocity numbers from a GPS become too unreliable for accurate correction of the accelerometers.
Unit: 
Default Value: 6

Min | Max
--- | ---
 0 | 10

## AHRS_GPS_USE: AHRS use GPS for navigation
This controls whether to use dead-reckoning or GPS based navigation. If set to 0 then the GPS won’t be used for navigation, and only dead reckoning will be used. A value of zero should never be used for normal flight. Currently this affects only the DCM-based AHRS: the EKF uses GPS whenever it is available.
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## AHRS_ORIENTATION: Board Orientation
Overall board orientation relative to the standard orientation for the board type. This rotates the IMU and compass readings to allow the board to be oriented in your vehicle at any 90 or 45 degree angle. This option takes affect on next boot. After changing you will need to re-level your vehicle.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | None
1 | Yaw45
2 | Yaw90
3 | Yaw135
4 | Yaw180
5 | Yaw225
6 | Yaw270
7 | Yaw315
8 | Roll180
9 | Roll180Yaw45
10 | Roll180Yaw90
11 | Roll180Yaw135
12 | Pitch180
13 | Roll180Yaw225
14 | Roll180Yaw270
15 | Roll180Yaw315
16 | Roll90
17 | Roll90Yaw45
18 | Roll90Yaw90
19 | Roll90Yaw135
20 | Roll270
21 | Roll270Yaw45
22 | Roll270Yaw90
23 | Roll270Yaw136
24 | Pitch90
25 | Pitch270
26 | Pitch180Yaw90
27 | Pitch180Yaw270
28 | Roll90Pitch90
29 | Roll180Pitch90
30 | Roll270Pitch90
31 | Roll90Pitch180
32 | Roll270Pitch180
33 | Roll90Pitch270
34 | Roll180Pitch270
35 | Roll270Pitch270
36 | Roll90Pitch180Yaw90
37 | Roll90Yaw270


## AHRS_RP_P: AHRS RP_P
This controls how fast the accelerometers correct the attitude
Unit: 
Default Value: 0.1

Min | Max
--- | ---
 0.1 | 0.4

## AHRS_TRIM_X: AHRS Trim Roll
Compensates for the roll angle difference between the control board and the frame. Positive values make the vehicle roll right.
Unit: radian
Default Value: 4.882812E-4

Min | Max
--- | ---
 -0.1745 | 0.1745

## AHRS_TRIM_Y: AHRS Trim Pitch
Compensates for the pitch angle difference between the control board and the frame. Positive values make the vehicle pitch up/back.
Unit: radian
Default Value: 0.02518386

Min | Max
--- | ---
 -0.1745 | 0.1745

## AHRS_TRIM_Z: AHRS Trim Yaw
Not Used
Unit: radian
Default Value: 0

Min | Max
--- | ---
 -0.1745 | 0.1745

## AHRS_WIND_MAX: Maximum wind
This sets the maximum allowable difference between ground speed and airspeed. This allows the plane to cope with a failing airspeed sensor. A value of zero means to use the airspeed as is.
Unit: m/s
Default Value: 0

Min | Max
--- | ---
 0 | 127

## AHRS_YAW_P: 
This controls the weight the compass or GPS has on the heading. A higher value means the heading will track the yaw source (MAV_PARAM_GROUP.ARDUCOPTER,GPS or compass) more rapidly.
Unit: 
Default Value: 0.1

Min | Max
--- | ---
 0.1 | 0.4

## ANGLE_MAX: Angle Max
Maximum lean angle in all flight modes
Unit: centidegree
Default Value: 4500

Min | Max
--- | ---
 1000 | 8000

## ARMING_CHECK: 
Allows enabling or disabling of pre-arming checks of receiver, accelerometer, barometer, compass and GPS
Unit: list
Default Value: 94

Key | Value
--- | ---
0 | Disabled
-17 | SkipINS
-33 | SkipParams/Rangefinder
-65 | SkipRC
1 | Enabled
-3 | SkipBaro
-5 | SkipCompass
-9 | SkipGPS
127 | SkipVoltage


## ATC_ACCEL_RP_MAX: Acceleration Max for Roll/Pitch
Maximum acceleration in roll/pitch axis
Unit: centidegrees/s/s
Default Value: 0

Key | Value
--- | ---
0 | Disabled
18000 | Slow
36000 | Medium
54000 | Fast
9000 | VerySlow


## ATC_ACCEL_Y_MAX: Acceleration Max for Yaw
Maximum acceleration in yaw axis
Unit: centidegrees/s/s
Default Value: 0

Key | Value
--- | ---
0 | Disabled
18000 | Slow
36000 | Medium
54000 | Fast
9000 | VerySlow


## ATC_RATE_FF_ENAB: 
Controls whether body-frame rate feedforward is enabled or disabled
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## ATC_RATE_RP_MAX: Angular Velocity Max for Pitch/Roll
Maximum angular velocity in pitch/roll axis
Unit: deg/s
Default Value: 18000

Key | Value
--- | ---
0 | Disabled
720 | Medium
360 | Slow
1080 | Fast


## ATC_RATE_Y_MAX: Angular Velocity Max for Yaw
Maximum angular velocity in yaw axis
Unit: deg/s
Default Value: 9000

Key | Value
--- | ---
0 | Disabled
720 | Medium
360 | Slow
1080 | Fast


## ATC_SLEW_YAW: Yaw target slew rate
Maximum rate the yaw target can be updated in Loiter, RTL, Auto flight modes
Unit: centidegrees/s
Default Value: 1000

Min | Max
--- | ---
 500 | 18000

## BAROGLTCH_ACCEL: Baro glitch protection’s max vehicle acceleration assumption
Baro glitch protection’s max vehicle acceleration assumption
Unit: cm/s/s
Default Value: 1500

Min | Max
--- | ---
 100 | 2000

## BAROGLTCH_DIST: Baro glitch protection distance within which alt update is immediately accepted
Baro glitch protection distance within which alt update is immediately accepted
Unit: cm
Default Value: 500

Min | Max
--- | ---
 100 | 2000

## BAROGLTCH_ENABLE: Baro Glitch protection enable/disable
Allows you to enable (1) or disable (0) baro glitch protection
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## BATT_AMP_OFFSET: AMP offset
Voltage offset at zero current on current sensor
Unit: volt
Default Value: 0


## BATT_AMP_PERVOLT: Amps per volt
Number of amps that a 1V reading on the current sensor corresponds to. On the APM2 or Pixhawk using the 3DR Power brick this should be set to 17. For the Pixhawk with the 3DR 4in1 ESC this should be 17.
Unit: amper/volt
Default Value: 17


## BATT_AMP_PERVLT: Amps per volt
Number of amps that a 1V reading on the current sensor corresponds to. On the APM2 or Pixhawk using the 3DR Power brick this should be set to 17. For the Pixhawk with the 3DR 4in1 ESC this should be 17.
Unit: amper/volt
Default Value: 17


## BATT_CAPACITY: Battery capacity
Capacity of the battery in mAh when full
Unit: mAh
Default Value: 4000


## BATT_CURR_PIN: 
Setting this to 0 ~ 13 will enable battery current sensing on pins A0 ~ A13. For the 3DR power brick on APM2.5 it should be set to 12. On the PX4 it should be set to 101. On the Pixhawk powered from the PM connector it should be set to 3.
Unit: list
Default Value: 3

Key | Value
--- | ---
-1 | Disabled
1 | A1
2 | A2
3 | Pixhawk
101 | PX4
12 | A12


## BATT_MONITOR: Battery monitoring
Controls enabling monitoring of the battery's voltage and current
Unit: list
Default Value: 4

Key | Value
--- | ---
0 | Disabled
3 | Analog_Voltage_Only
4 | Analog_Voltage_and_Current
5 | SMBus
6 | Bebop


## BATT_VOLT_MULT: Voltage Multiplier
Used to convert the voltage of the voltage sensing pin (BATT_VOLT_PIN) to the actual battery's voltage (MAV_PARAM_GROUP.ARDUCOPTER,pin_voltage * VOLT_MULT). For the 3DR Power brick on APM2 or Pixhawk, this should be set to 10.1. For the Pixhawk with the 3DR 4in1 ESC this should be 12.02. For the PX4 using the PX4IO power supply this should be set to 1.
Unit: 
Default Value: 10.1


## BATT_VOLT_PIN: Battery Voltage sensing pin
Setting this to 0 ~ 13 will enable battery voltage sensing on pins A0 ~ A13. For the 3DR power brick on APM2.5 it should be set to 13. On the PX4 it should be set to 100. On the Pixhawk powered from the PM connector it should be set to 2.
Unit: list
Default Value: 2

Key | Value
--- | ---
-1 | Disabled
0 | A0
1 | A1
2 | Pixhawk
100 | PX4
13 | A13


## BATT_VOLT2_MULT: Voltage Multiplier
Used to convert the voltage of the voltage sensing pin (BATT_VOLT_PIN) to the actual battery’s voltage (pin_voltage * VOLT_MULT). For the 3DR Power brick with a Pixhawk, this should be set to 10.1. For the Pixhawk with the 3DR 4in1 ESC this should be 12.02. For the PX using the PX4IO power supply this should be set to 1
Unit: 
Default Value: 1


## BATT_VOLT2_PIN: Battery Voltage sensing pin
Sets the analog input pin that should be used for voltage monitoring.
Unit: list
Default Value: -1

Key | Value
--- | ---
-1 | Disabled
0 | A0
1 | A1
2 | Pixhawk
100 | PX4
13 | A13


## CAM_DURATION: Duration that shutter is held open
How long the shutter will be held open in 10ths of a second (i.e. enter 10 for 1second, 50 for 5seconds)
Unit: decisecond
Default Value: 10

Min | Max
--- | ---
 0 | 50

## CAM_SERVO_OFF: Servo OFF PWM value
PWM value to move servo to when shutter is deactivated
Unit: PWM/ms
Default Value: 1100

Min | Max
--- | ---
 1000 | 2000

## CAM_SERVO_ON: Servo ON PWM value
PWM value to move servo to when shutter is activated
Unit: PWM/ms
Default Value: 1300

Min | Max
--- | ---
 1000 | 2000

## CAM_TRIGG_DIST: Camera trigger distance
Distance in meters between camera triggers. If this value is non-zero then the camera will trigger whenever the GPS position changes by this number of meters regardless of what mode the APM is in. Note that this parameter can also be set in an auto mission using the DO_SET_CAM_TRIGG_DIST command, allowing you to enable/disable the triggering of the camera during the flight.
Unit: meter
Default Value: 0

Min | Max
--- | ---
 0 | 1000

## CAM_TRIGG_TYPE: Camera shutter (trigger) type
how to trigger the camera to take a picture
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Servo
1 | Relay


## CH7_OPT: Channel 7 option
Select which function is performed when CH7 is above 1800 pwm
Unit: list
Default Value: 17

Key | Value
--- | ---
0 | DoNothing
2 | Flip
3 | SimpleMode
4 | RTL
5 | SaveTrim
7 | SaveWP
9 | CameraTrigger
10 | RangeFinder
11 | Fence
13 | SuperSimpleMode
14 | AcroTrainer
15 | Sprayer
16 | Auto
17 | AutoTune
18 | Land
19 | EPM
21 | ParachuteEnable
22 | ParachuteRelease
23 | Parachute3pos
24 | AutoMissionReset
25 | AttConFeedForward
26 | AttConAccelLimits
27 | RetractMount
28 | Relay-On/Off
29 | Landing-Gear
30 | Lost-Copter-Sound
31 | Motor-Emergency-Stop
32 | MotorInterlock
33 | Brake
34 | Relay2-On/Off
35 | Relay3-On/Off
36 | Relay4-On/Off
37 | Throw
38 | Avoidance


## CH8_OPT: Channel 8 option
Select which function is performed when CH8 is above 1800 pwm
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | DoNothing
2 | Flip
3 | SimpleMode
4 | RTL
5 | SaveTrim
7 | SaveWP
9 | CameraTrigger
10 | RangeFinder
11 | Fence
13 | SuperSimpleMode
14 | AcroTrainer
15 | Sprayer
16 | Auto
17 | AutoTune
18 | Land
19 | EPM
21 | ParachuteEnable
22 | ParachuteRelease
23 | Parachute3pos
24 | AutoMissionReset
25 | AttConFeedForward
26 | AttConAccelLimitsRetractMount
28 | Relay-On/Off
29 | Landing-Gear
30 | Lost-Copter-Sound
31 | Motor-Emergency-Stop
32 | MotorInterlock
33 | Brake
34 | Relay2-On/Off
35 | Relay3-On/Off
36 | Relay4-On/Off
37 | Throw
38 | Avoidance


## CIRCLE_RADIUS: 
Defines the radius of the circle the vehicle will fly when in Circle flight mode
Unit: cm
Default Value: 500

Min | Max
--- | ---
 0 | 10000

## CIRCLE_RATE: 
Circle mode's turn rate in deg/sec. Positive to turn clockwise, negative for counter clockwise
Unit: deg/s
Default Value: 20

Min | Max
--- | ---
 -90 | 90

## COMPASS_AUTODEC: Auto Declination
Enable or disable the automatic calculation of the declination based on gps location
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## COMPASS_DEC: Compass declination
An angle to compensate between the true north and magnetic north
Unit: radian
Default Value: 0.06941485

Min | Max
--- | ---
 -3.142 | 3.142

## COMPASS_EXTERNAL: 
Configure compass so it is attached externally. This is auto-detected on PX4 and Pixhawk. Set to 1 if the compass is externally connected. When externally connected the COMPASS_ORIENT option operates independently of the AHRS_ORIENTATION board orientation option. If set to 0 or 1 then auto-detection by bus connection can override the value. If set to 2 then auto-detection will be disabled.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Internal
1 | External
2 | ForcedExternal


## COMPASS_LEARN: Learn compass offsets automatically
Enable or disable the automatic learning of compass offsets. You can enable learning either using a compass-only method that is suitable only for fixed wing aircraft or using the offsets learnt by the active EKF state estimator. If this option is enabled then the learnt offsets are saved when you disarm the vehicle.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Internal-Learning
2 | EKF-Learning


## COMPASS_MOT_X: Motor interference compensation for body frame X axis
Multiplied by the current throttle and added to the compass's x-axis values to compensate for motor interference
Unit: milligauss/amper
Default Value: 0

Min | Max
--- | ---
 -1000 | 1000

## COMPASS_MOT_Y: Motor interference compensation for body frame Y axis
Multiplied by the current throttle and added to the compass's y-axis values to compensate for motor interference
Unit: milligauss/amper
Default Value: 0

Min | Max
--- | ---
 -1000 | 1000

## COMPASS_MOT_Z: Motor interference compensation for body frame Z axis
Multiplied by the current throttle and added to the compass's z-axis values to compensate for motor interference
Unit: milligauss/amper
Default Value: 0

Min | Max
--- | ---
 -1000 | 1000

## COMPASS_MOTCT: Motor interference compensation type
Set motor interference compensation type to disabled, throttle or current. Do not change manually.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | UseThrottle
2 | UseCurrent


## COMPASS_OFS_X: Compass offsets in milligauss on the X axis
Offset to be added to the compass x-axis values to compensate for metal in the frame
Unit: milligauss
Default Value: -98

Min | Max
--- | ---
 -400 | 400

## COMPASS_OFS_Y: Compass offsets in milligauss on the Y axis
Offset to be added to the compass y-axis values to compensate for metal in the frame
Unit: milligauss
Default Value: 29

Min | Max
--- | ---
 -400 | 400

## COMPASS_OFS_Z: Compass offsets in milligauss on the X axis
Offset to be added to the compass z-axis values to compensate for metal in the frame
Unit: milligauss
Default Value: -18

Min | Max
--- | ---
 -400 | 400

## COMPASS_ORIENT: 
The orientation of the compass relative to the autopilot board. This will default to the right value for each board type, but can be changed if you have an external compass. See the documentation for your external compass for the right value. The correct orientation should give the X axis forward, the Y axis to the right and the Z axis down. So if your aircraft is pointing west it should show a positive value for the Y axis, and a value close to zero for the X axis. On a PX4 or Pixhawk with an external compass the correct value is zero if the compass is correctly oriented. NOTE: This orientation is combined with any AHRS_ORIENTATION setting.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | None
1 | Yaw45
2 | Yaw90
3 | Yaw135
4 | Yaw180
5 | Yaw225
6 | Yaw270
7 | Yaw315
8 | Roll180
9 | Roll180Yaw45
10 | Roll180Yaw90
11 | Roll180Yaw135
12 | Pitch180
13 | Roll180Yaw225
14 | Roll180Yaw270
15 | Roll180Yaw315
16 | Roll90
17 | Roll90Yaw45
18 | Roll90Yaw90
19 | Roll90Yaw135
20 | Roll270
21 | Roll270Yaw45
22 | Roll270Yaw90
23 | Roll270Yaw136
24 | Pitch90
25 | Pitch270
26 | Pitch180Yaw90
27 | Pitch180Yaw270
28 | Roll90Pitch90
29 | Roll180Pitch90
30 | Roll270Pitch90
31 | Roll90Pitch180
32 | Roll270Pitch180
33 | Roll90Pitch270
34 | Roll180Pitch270
35 | Roll270Pitch270
36 | Roll90Pitch180Yaw90
37 | Roll90Yaw270
38 | Yaw293Pitch68Roll90


## COMPASS_USE: Use compass for yaw
Enable or disable the use of the compass (MAV_PARAM_GROUP.ARDUCOPTER,instead of the GPS) for determining heading
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## DCM_CHECK_THRESH: DCM yaw error threshold
Allows setting the maximum acceptable yaw error as a sin of the yaw error (0 to disable check)
Unit: list
Default Value: 0.8

Key | Value
--- | ---
0 | Disabled
0.8 | Default
0.98 | Relaxed


## EKF_CHECK_THRESH: EKF check compass and velocity variance threshold
Allows setting the maximum acceptable compass and velocity variance (0 to disable check)
Unit: list
Default Value: 0.8

Key | Value
--- | ---
0 | Disabled
1.0 | Relaxed
0.8 | Default


## ESC: ESC Calibration
Controls whether ArduCopter will enter ESC calibration on the next restart. Do not adjust this parameter manually
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Normal Start-up
1 | Start-up in ESC Calibration mode if throttle high
2 | Start-up in ESC Calibration mode regardless of throttle


## FENCE_ACTION: 
What action should be taken when fence is breached
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | ReportOnly
1 | RTL-or-Land


## FENCE_ALT_MAX: Fence Maximum Altitude
Maximum altitude allowed before geofence triggers
Unit: meter
Default Value: 100

Min | Max
--- | ---
 10 | 1000

## FENCE_ENABLE: 
Allows you to enable (1) or disable (0) the fence functionality
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## FENCE_MARGIN: Fence Margin
Distance that autopilot's should maintain from the fence to avoid a breach
Unit: meter
Default Value: 2

Min | Max
--- | ---
 1 | 10

## FENCE_RADIUS: 
Circle fence radius which when breached will cause an RTL
Unit: meter
Default Value: 300

Min | Max
--- | ---
 30 | 10000

## FENCE_TYPE: 
Enabled fence types held as bitmask
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | None
1 | Altitude
2 | Circle
3 | AltitudeAndCircle
4 | Polygon
5 | AltitudeAndPolygon
6 | CircleAndPolygon
7 | All


## FLOW_ENABLE: 
Setting this to Enabled(1) will enable optical flow. Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will disable optical flow
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## FLTMODE1: 
Flight mode when Channel 5 pwm is <= 1230
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FLTMODE2: 
Flight mode when Channel 5 pwm is >1230, <= 1360
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FLTMODE3: 
Flight mode when Channel 5 pwm is >1360, <= 1490
Unit: list
Default Value: 16

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FLTMODE4: 
Flight mode when Channel 5 pwm is >1490, <= 1620
Unit: list
Default Value: 16

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FLTMODE5: 
Flight mode when Channel 5 pwm is >1620, <= 1749
Unit: list
Default Value: 6

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FLTMODE6: 
Flight mode when Channel 5 pwm is >=1750
Unit: list
Default Value: 6

Key | Value
--- | ---
0 | Stabilize
1 | Acro
2 | AltHold
3 | Auto
4 | Guided
5 | Loiter
6 | RTL
7 | Circle
9 | Land
11 | Drift
13 | Sport
14 | Flip
15 | AutoTune
16 | PosHold
17 | Brake
18 | Throw
19 | Avoid_ADSB
20 | Guided_NoGPS


## FRAME: 
Controls motor mixing for multicopters. Not used for Tri or Traditional Helicopters.
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Plus
1 | X
2 | V
3 | H
4 | V-Tail
5 | A-Tail
10 | Y6B-New)


## FS_BATT_ENABLE: 
Controls whether failsafe will be invoked when battery voltage or current runs low
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Land
2 | RTL


## FS_BATT_MAH: 
Battery capacity remaining to trigger failsafe. Set to 0 to disable battery remaining failsafe. If the battery remaining drops below this level then the copter will RTL
Unit: mAh
Default Value: 0


## FS_BATT_VOLTAGE: 
Battery voltage to trigger failsafe. Set to 0 to disable battery voltage failsafe. If the battery voltage drops below this voltage then the copter will RTL
Unit: volt
Default Value: 14


## FS_GCS_ENABLE: 
Controls whether failsafe will be invoked (MAV_PARAM_GROUP.ARDUCOPTER,and what action to take) when connection with Ground station is lost for at least 5 seconds. NB. The GCS Failsafe is only active when RC_OVERRIDE is being used to control the vehicle.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled_always_RTL
2 | Enabled_Continue_with_Mission_in_Auto_Mode


## FS_GPS_ENABLE: GPS Failsafe Enable
Controls what action will be taken if GPS signal is lost for at least 5 seconds
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Land
2 | AltHold
3 | Land even from Stabilize


## FS_THR_ENABLE: 
The throttle failsafe allows you to configure a software failsafe activated by a setting on the throttle input channel
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled_Always_RTL
2 | Enabled_Continue_with_Mission_in_Auto_Mode
3 | Enabled_Always_LAND


## FS_THR_VALUE: 
The PWM level on channel 3 below which throttle failsafe triggers
Unit: PWM
Default Value: 975

Min | Max
--- | ---
 925 | 1100

## GND_ABS_PRESS: 
calibrated ground pressure in PASCAL
Unit: pascal
Default Value: 50362.25


## GND_ALT_OFFSET: 
altitude offset in meters added to barometric altitude. This is used to allow for automatic adjustment of the base barometric altitude by a ground station equipped with a barometer. The value is added to the barometric altitude read by the aircraft. It is automatically reset to 0 when the barometer is calibrated on each reboot or when a preflight calibration is performed.
Unit: meter
Default Value: 0


## GND_TEMP: 
calibrated ground temperature in degrees Celsius
Unit: degrees-celsius
Default Value: 29.32966


## GPS_HDOP_GOOD: GPS Hdop Good
GPS Hdop value at or below this value represent a good position. Used for pre-arm checks
Unit: 
Default Value: 230

Min | Max
--- | ---
 100 | 900

## GPS_NAVFILTER: 
Navigation filter engine setting
Unit: list
Default Value: 8

Key | Value
--- | ---
0 | Portable
2 | Stationary
3 | Pedestrian
4 | Automotive
5 | Sea
6 | Airborne1G
7 | Airborne2G
8 | Airborne4G


## GPS_TYPE: 
GPS type
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | None
1 | AUTO
2 | uBlox
3 | MTK
4 | MTK19
5 | NMEA
6 | SiRF
7 | HIL
8 | SwiftNav
9 | PX4-UAVCAN
10 | SBF
11 | GSOF
12 | QURT
13 | ERB
14 | MAV
15 | NOVA


## GPSGLITCH_ACCEL: GPS glitch protection - accel
GPS glitch protection’s max vehicle acceleration assumption
Unit: centidegrees/s/s
Default Value: 1000

Min | Max
--- | ---
 100 | 2000

## GPSGLITCH_ENABLE: GPS Glitch protection enable/disable
Allows you to enable (1) or disable (0) gps glitch protection
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## GPSGLITCH_RADIUS: GPS glitch protection - radius
GPS glitch protection radius within which all new positions are accepted
Unit: cm
Default Value: 200

Min | Max
--- | ---
 100 | 2000

## HLD_LAT_P: Loiter position controller P gain
Loiter position controller P gain. Converts the distance (in the latitude direction) to the target location into a desired speed which is then passed to the loiter latitude rate controller
Unit: 
Default Value: 1

Min | Max
--- | ---
 0.5 | 2.0

## INAV_TC_XY: Horizontal Time Constant
Time constant for GPS and accel mixing. Higher TC decreases GPS impact on position estimate
Unit: 
Default Value: 2.5

Min | Max
--- | ---
 0 | 10

## INAV_TC_Z: Vertical Time Constant
Time constant for baro and accel mixing. Higher TC decreases barometers impact on altitude estimate
Unit: 
Default Value: 5

Min | Max
--- | ---
 0 | 10

## INS_ACCOFFS_X: Accelerometer offsets of X axis
This is setup using the acceleration calibration or level operations
Unit: m/s/s
Default Value: 0.02704384

Min | Max
--- | ---
 -3.5 | 3.5

## INS_ACCOFFS_Y: Accelerometer offsets of Y axis
This is setup using the acceleration calibration or level operations
Unit: m/s/s
Default Value: 0.07232188

Min | Max
--- | ---
 -3.5 | 3.5

## INS_ACCOFFS_Z: Accelerometer offsets of Z axis
This is setup using the acceleration calibration or level operations
Unit: m/s/s
Default Value: -0.2916607

Min | Max
--- | ---
 -3.5 | 3.5

## INS_ACCSCAL_X: Accelerometer scaling of X axis
Calculated during acceleration calibration routine
Unit: 
Default Value: 0.9998162

Min | Max
--- | ---
 0.8 | 1.2

## INS_ACCSCAL_Y: Accelerometer scaling of Y axis
Calculated during acceleration calibration routine
Unit: 
Default Value: 0.9920868

Min | Max
--- | ---
 0.8 | 1.2

## INS_ACCSCAL_Z: Accelerometer scaling of Z axis
Calculated during acceleration calibration routine
Unit: 
Default Value: 1.0001

Min | Max
--- | ---
 0.8 | 1.2

## INS_GYROFFS_X: Gyro sensor offsets of X axis
This is setup on each boot during gyro calibrations
Unit: rad/s
Default Value: -0.003293759


## INS_GYROFFS_Y: Gyro sensor offsets of Y axis
This is setup on each boot during gyro calibrations
Unit: rad/s
Default Value: -0.07851811


## INS_GYROFFS_Z: Gyro sensor offsets of Z axis
This is setup on each boot during gyro calibrations
Unit: rad/s
Default Value: 0.02421099


## INS_MPU6K_FILTER: MPU6000 filter frequency
Filter frequency to ask the MPU6000 to apply to samples. This can be set to a lower value to try to cope with very high vibration levels in aircraft. The default value on ArduPlane, APMrover2 and ArduCopter is 20Hz. This option takes effect on the next reboot or gyro initialisation
Unit: Hz
Default Value: 0

Key | Value
--- | ---
0 | Default
98 | 98Hz
20 | 20Hz
5 | 5Hz
10 | 10Hz
42 | 42Hz


## INS_PRODUCT_ID: 
Which type of IMU is installed (read-only).
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Unknown
256 | unused
1 | unused
257 | Linux
2 | unused
3 | SITL
4 | PX4v1
5 | PX4v2
88 | unused


## LAND_REPOSITION: 
Enables user input during LAND mode, the landing phase of RTL, and auto mode landings.
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | NoRepositioning
1 | Repositioning


## LAND_SPEED: 
The descent speed for the final stage of landing in CENTIMETER/s
Unit: cm/s
Default Value: 50

Min | Max
--- | ---
 30 | 200

## LOG_BITMASK: 
4 byte bitmap of log types to enable, Bitmask: 0:ATTITUDE_FAST,1:ATTITUDE_MED,2:GPS,3:PM,4:CTUN,5:NTUN,6:RCIN,7:IMU,8:CMD,9:CURRENT,10:RCOUT,11:OPTFLOW,12:PID,13:COMPASS,14:INAV,15:CAMERA,17:MOTBATT,18:IMU_FAST,19:IMU_RAW
Unit: list
Default Value: 26622

Key | Value
--- | ---
0 | Disabled
-6146 | NearlyAll-AC315
655358 | All+FullIMU
397310 | All+FastIMU+PID
393214 | All+FastIMU
262142 | All+MotBatt
830 | Default
894 | Default+RCIN
958 | Default+IMU
1854 | Default+Motors
45054 | NearlyAll
131071 | All+FastATT


## LOITER_LAT_D: Loiter latitude rate controller D gain
Loiter latitude rate controller D gain. Compensates for short-term change in desired speed vs actual speed
Unit: 
Default Value: 0

Min | Max
--- | ---
 0 | 0.6

## LOITER_LAT_I: Loiter latitude rate controller I gain
Loiter latitude rate controller I gain. Corrects long-term difference in desired speed and actual speed in the latitude direction
Unit: 
Default Value: 0.5

Min | Max
--- | ---
 0.02 | 1.0

## LOITER_LAT_IMAX: Loiter rate controller I gain maximum
Loiter rate controller I gain maximum. Constrains the lean angle that the I gain will output
Unit: centidegree
Default Value: 1000

Min | Max
--- | ---
 0 | 4500

## LOITER_LAT_P: Loiter latitude rate controller P gain 
Loiter latitude rate controller P gain. Converts the difference between desired speed and actual speed into a lean angle in the latitude direction
Unit: 
Default Value: 1

Min | Max
--- | ---
 0.1 | 6

## LOITER_LON_D: Loiter longitude rate controller D gain
Loiter longitude rate controller D gain. Compensates for short-term change in desired speed vs actual speed
Unit: 
Default Value: 0

Min | Max
--- | ---
 0 | 0.6

## LOITER_LON_I: Loiter longitude rate controller I gain
Loiter longitude rate controller I gain. Corrects long-term difference in desired speed and actual speed in the longitude direction
Unit: 
Default Value: 0.5

Min | Max
--- | ---
 0.02 | 1.0

## LOITER_LON_IMAX: 
Loiter longitude rate controller I gain maximum. Constrains the lean angle that the I gain will output
Unit: centidegree
Default Value: 1000

Min | Max
--- | ---
 0 | 4500

## LOITER_LON_P: Loiter longitude rate controller P gain 
Loiter longitude rate controller P gain. Converts the difference between desired speed and actual speed into a lean angle in the longitude direction
Unit: 
Default Value: 1

Min | Max
--- | ---
 0.1 | 6

## MAG_ENABLE: 
Setting this to Enabled(1) will enable the compass. Setting this to Disabled(0) will disable the compass
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## MIS_RESTART: Mission Restart when entering Auto mode
Controls mission starting point when entering Auto mode (either restart from beginning of mission or resume from last command run)
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | ResumeMission
1 | RestartMission


## MIS_TOTAL: Total mission commands
The number of mission mission items that has been loaded by the ground station. Do not change this manually.
Unit: 
Default Value: 6

Min | Max
--- | ---
 0 | 32766

## MNT_ANGMAX_PAN: 
Maximum physical pan (yaw) angular position of the mount
Unit: centidegree
Default Value: 4500

Min | Max
--- | ---
 -18000 | 17999

## MNT_ANGMAX_ROL: 
Maximum physical roll angular position of the mount
Unit: centidegree
Default Value: 4500

Min | Max
--- | ---
 -18000 | 17999

## MNT_ANGMAX_TIL: 
Maximum physical tilt (pitch) angular position of the mount
Unit: centidegree
Default Value: 0

Min | Max
--- | ---
 -18000 | 17999

## MNT_ANGMIN_PAN: 
Minimum physical pan (yaw) angular position of mount.
Unit: centidegree
Default Value: -4500

Min | Max
--- | ---
 -18000 | 17999

## MNT_ANGMIN_ROL: 
Minimum physical roll angular position of mount.
Unit: centidegree
Default Value: -4500

Min | Max
--- | ---
 -18000 | 17999

## MNT_ANGMIN_TIL: 
Minimum physical tilt (pitch) angular position of mount.
Unit: centidegree
Default Value: -9000

Min | Max
--- | ---
 -18000 | 17999

## MNT_CONTROL_X: Mount roll angle
Mount roll angle when in MavLink or RC control operation mode
Unit: centidegree
Default Value: 0

Min | Max
--- | ---
 -18000 | 17999

## MNT_CONTROL_Y: Mount tilt/pitch
Mount tilt/pitch angle when in MavLink or RC control operation mode
Unit: centidegree
Default Value: 0

Min | Max
--- | ---
 -18000 | 17999

## MNT_CONTROL_Z: Mount pan/yaw angle
Mount pan/yaw angle when in MavLink or RC control operation mode
Unit: centidegree
Default Value: 0

Min | Max
--- | ---
 -18000 | 17999

## MNT_JSTICK_SPD: mount joystick speed
0 for position control, small for low speeds, 100 for max speed. A good general value is 10 which gives a movement speed of 3 degrees per second.
Unit: 
Default Value: 0

Min | Max
--- | ---
 0 | 100

## MNT_MODE: Mount Operation Mode
Camera or antenna mount operation mode, check MAV_MOUNT_MODE class
Unit: list
Default Value: 3

Key | Value
--- | ---
0 | RETRACT
1 | MODE_NEUTRAL
2 | MAVLINK_TARGETING
3 | RC_TARGETING
4 | GPS_POINT


## MNT_NEUTRAL_X: 
Mount roll angle when in neutral position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_NEUTRAL_Y: 
Mount tilt/pitch angle when in neutral position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_NEUTRAL_Z: 
Mount pan/yaw angle when in neutral position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_RC_IN_PAN: 
0 for none, any other for the RC channel to be used to control pan (MAV_PARAM_GROUP.ARDUCOPTER,yaw) movements
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
5 | RC5
6 | RC6
7 | RC7
8 | RC8
9 | RC9
10 | RC10
11 | RC11
12 | RC12


## MNT_RC_IN_ROLL: 
0 for none, any other for the RC channel to be used to control roll movements
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
5 | RC5
6 | RC6
7 | RC7
8 | RC8
9 | RC9
10 | RC10
11 | RC11
12 | RC12


## MNT_RC_IN_TILT: 
0 for none, any other for the RC channel to be used to control tilt (MAV_PARAM_GROUP.ARDUCOPTER,pitch) movements
Unit: list
Default Value: 6

Key | Value
--- | ---
0 | Disabled
5 | RC5
6 | RC6
7 | RC7
8 | RC8
9 | RC9
10 | RC10
11 | RC11
12 | RC12


## MNT_RETRACT_X: 
Mount roll angle when in retracted position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_RETRACT_Y: 
Mount tilt/pitch angle when in retracted position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_RETRACT_Z: 
Mount yaw/pan angle when in retracted position
Unit: degree
Default Value: 0

Min | Max
--- | ---
 -180.0 | 179.99

## MNT_STAB_PAN: 
enable pan/yaw stabilisation relative to Earth
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## MNT_STAB_ROLL: 
enable roll stabilisation relative to Earth
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## MNT_STAB_TILT: 
enable tilt/pitch stabilisation relative to Earth
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## MOT_SPIN_ARMED: Motors always spin when armed
Controls whether motors always spin when armed (must be below THR_MIN)
Unit: 
Default Value: 90

Key | Value
--- | ---
0 | Do Not Spin
130 | Medium
100 | Slow
70 | VerySlow
150 | Fast


## MOT_TCRV_ENABLE: Thrust Curve Enable
Controls whether a curve is used to linearize the thrust produced by the motors
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | Disabled
1 | Enabled


## MOT_TCRV_MAXPCT: Thrust Curve max thrust percentage
Set to the lowest pwm position that produces the maximum thrust of the motors. Most motors produce maximum thrust below the maximum pwm value that they accept
Unit: percent
Default Value: 93

Min | Max
--- | ---
 20 | 80

## MOT_TCRV_MIDPCT: Thrust Curve mid-point percentage
Set the pwm position that produces half the maximum thrust of the motors
Unit: percent
Default Value: 52

Min | Max
--- | ---
 20 | 80

## OF_PIT_D: Optical Flow based loiter controller pitch axis D gain
Compensates for short-term change in speed in the pitch direction
Unit: 
Default Value: 0.12

Min | Max
--- | ---
 0.1 | 0.14

## OF_PIT_I: Optical Flow based loiter controller pitch axis I gain
Corrects long-term position error by more persistently pitching left or right
Unit: 
Default Value: 0.5

Min | Max
--- | ---
 0.25 | 0.75

## OF_PIT_IMAX: Optical Flow based loiter controller pitch axis I gain maximum
Constrains the maximum pitch angle that the I term will generate
Unit: centidegree
Default Value: 100

Min | Max
--- | ---
 0 | 4500

## OF_PIT_P: Optical Flow based loiter controller pitch axis P gain
Converts the position error from the target point to a pitch angle
Unit: 
Default Value: 2.5

Min | Max
--- | ---
 2 | 3

## OF_RLL_D: Optical Flow based loiter controller roll axis D gain
Compensates for short-term change in speed in the roll direction
Unit: 
Default Value: 0.12

Min | Max
--- | ---
 0.1 | 0.14

## OF_RLL_I: Optical Flow based loiter controller roll axis I gain
Corrects long-term position error by more persistently rolling left or right
Unit: 
Default Value: 0.5

Min | Max
--- | ---
 0.25 | 0.75

## OF_RLL_IMAX: Optical Flow based loiter controller roll axis I gain maximum
Constrains the maximum roll angle that the I term will generate
Unit: centidegree
Default Value: 100

Min | Max
--- | ---
 0 | 4500

## OF_RLL_P: Optical Flow based loiter controller roll axis P gain
Converts the position error from the target point to a roll angle
Unit: 
Default Value: 2.5

Min | Max
--- | ---
 2 | 3

## PHLD_BRAKE_ANGLE: 
PosHold flight mode's max lean angle during braking in centi-degrees
Unit: centidegree
Default Value: 3000

Min | Max
--- | ---
 2000 | 4500

## PHLD_BRAKE_RATE: PosHold braking rate
PosHold flight mode's rotation rate during braking in deg/sec
Unit: deg/s
Default Value: 8

Min | Max
--- | ---
 4 | 12

## PILOT_ACCEL_Z: 
The vertical acceleration used when pilot is controlling the altitude
Unit: cm/s/s
Default Value: 250

Min | Max
--- | ---
 50 | 500

## PILOT_VELZ_MAX: 
The maximum vertical velocity the pilot may request in CENTIMETER/s
Unit: cm/s
Default Value: 250

Min | Max
--- | ---
 50 | 500

## POSCON_THR_HOVER: Throttle Hover
The autopilot’s estimate of the throttle required to maintain a level hover. Calculated automatically from the pilot’s throttle input while in stabilize mode
Unit: percent
Default Value: 724

Min | Max
--- | ---
 0 | 1000

## RATE_PIT_D: Pitch axis rate controller D gain
Compensates for short-term change in desired pitch rate vs actual pitch rate
Unit: 
Default Value: 0.0055

Min | Max
--- | ---
 0.001 | 0.02

## RATE_PIT_I: Pitch axis rate controller I gain
Corrects long-term difference in desired pitch rate vs actual pitch rate
Unit: 
Default Value: 0.07999999

Min | Max
--- | ---
 0.01 | 0.5

## RATE_PIT_IMAX: Pitch axis rate controller I gain maximum
Constrains the maximum motor output that the I gain will output
Unit: percent
Default Value: 1000

Min | Max
--- | ---
 0 | 4500

## RATE_PIT_P: Pitch axis rate controller P gain
Converts the difference between desired pitch rate and actual pitch rate into a motor speed output
Unit: 
Default Value: 0.07999999

Min | Max
--- | ---
 0.08 | 0.3

## RATE_RLL_D: Roll axis rate controller D gain
Compensates for short-term change in desired roll rate vs actual roll rate
Unit: 
Default Value: 0.003

Min | Max
--- | ---
 0.001 | 0.02

## RATE_RLL_I: Roll axis rate controller I gain
Corrects long-term difference in desired roll rate vs actual roll rate
Unit: 
Default Value: 0.08499999

Min | Max
--- | ---
 0.01 | 0.5

## RATE_RLL_IMAX: Roll axis rate controller I gain maximum
Constrains the maximum motor output that the I gain will output
Unit: percent
Default Value: 1000

Min | Max
--- | ---
 0 | 4500

## RATE_RLL_P: Roll axis rate controller P gain
Converts the difference between desired roll rate and actual roll rate into a motor speed output
Unit: 
Default Value: 0.08499999

Min | Max
--- | ---
 0.08 | 0.3

## RATE_YAW_D: Yaw axis rate controller D gain
Compensates for short-term change in desired yaw rate vs actual yaw rate
Unit: 
Default Value: 0.003

Min | Max
--- | ---
 0 | 0.02

## RATE_YAW_I: Yaw axis rate controller I gain
Corrects long-term difference in desired yaw rate vs actual yaw rate
Unit: 
Default Value: 0.02

Min | Max
--- | ---
 0.01 | 0.05

## RATE_YAW_IMAX: Yaw axis rate controller I gain maximum
Constrains the maximum motor output that the I gain will output
Unit: percent
Default Value: 1000

Min | Max
--- | ---
 0 | 4500

## RATE_YAW_P: Yaw axis rate controller P gain
Converts the difference between desired yaw rate and actual yaw rate into a motor speed output
Unit: 
Default Value: 0.17

Min | Max
--- | ---
 0.15 | 0.5

## RC_FEEL_RP: 
RC feel for roll/pitch which controls vehicle response to user input with 0 being extremely soft and 100 being crisp
Unit: list
Default Value: 100

Key | Value
--- | ---
0 | Standard
50 | Medium
100 | VeryCrisp
1000 | VerySoft
25 | Soft
75 | Crisp


## RC_SPEED: ESC Update Speed
This is the speed in Hertz that your ESCs will receive updates
Unit: Hz
Default Value: 490

Min | Max
--- | ---
 50 | 490

## RC1_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 30

Min | Max
--- | ---
 0 | 200

## RC1_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1976

Min | Max
--- | ---
 800 | 2200

## RC1_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 998

Min | Max
--- | ---
 800 | 2200

## RC1_REV: RC reversed
Reverse servo operation. Set to 1 for normal (forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC1_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1483

Min | Max
--- | ---
 800 | 2200

## RC10_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC10_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC10_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1900

Min | Max
--- | ---
 800 | 2200

## RC10_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1100

Min | Max
--- | ---
 800 | 2200

## RC10_REV: RC reversed
Reverse servo operation. Set to 1 for normal (forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC10_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 800 | 2200

## RC11_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC11_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC11_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1900

Min | Max
--- | ---
 800 | 2200

## RC11_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1100

Min | Max
--- | ---
 800 | 2200

## RC11_REV: RC reversed
Reverse servo operation. Set to 1 for normal (forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC11_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 800 | 2200

## RC2_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 30

Min | Max
--- | ---
 0 | 200

## RC2_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1983

Min | Max
--- | ---
 800 | 2200

## RC2_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 996

Min | Max
--- | ---
 800 | 2200

## RC2_REV: RC reversed
Reverse servo operation. Set to 1 for normal (forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC2_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1492

Min | Max
--- | ---
 800 | 2200

## RC3_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 30

Min | Max
--- | ---
 0 | 200

## RC3_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1982

Min | Max
--- | ---
 800 | 2200

## RC3_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 996

Min | Max
--- | ---
 800 | 2200

## RC3_REV: RC reversed
Reverse servo operation. Set to 1 for normal (forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC3_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1000

Min | Max
--- | ---
 800 | 2200

## RC4_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 40

Min | Max
--- | ---
 0 | 200

## RC4_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1981

Min | Max
--- | ---
 800 | 2200

## RC4_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 992

Min | Max
--- | ---
 800 | 2200

## RC4_REV: RC reversed
Reverse servo operation. Set to 1 for normal (MAV_PARAM_GROUP.ARDUCOPTER,forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC4_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1486

Min | Max
--- | ---
 800 | 2200

## RC5_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC5_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC5_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1982

Min | Max
--- | ---
 800 | 2200

## RC5_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 992

Min | Max
--- | ---
 800 | 2200

## RC5_REV: RC reversed
Reverse servo operation. Set to 1 for normal (MAV_PARAM_GROUP.ARDUCOPTER,forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC5_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 993

Min | Max
--- | ---
 800 | 2200

## RC6_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC6_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC6_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1985

Min | Max
--- | ---
 800 | 2200

## RC6_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 992

Min | Max
--- | ---
 800 | 2200

## RC6_REV: RC reversed
Reverse servo operation. Set to 1 for normal (MAV_PARAM_GROUP.ARDUCOPTER,forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC6_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 992

Min | Max
--- | ---
 800 | 2200

## RC7_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC7_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC7_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1900

Min | Max
--- | ---
 800 | 2200

## RC7_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1100

Min | Max
--- | ---
 800 | 2200

## RC7_REV: RC reversed
Reverse servo operation. Set to 1 for normal (MAV_PARAM_GROUP.ARDUCOPTER,forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC7_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1498

Min | Max
--- | ---
 800 | 2200

## RC8_DZ: RC dead-zone
dead zone around trim or bottom
Unit: PWM
Default Value: 0

Min | Max
--- | ---
 0 | 200

## RC8_FUNCTION: 
Setting this to Disabled(MAV_PARAM_GROUP.ARDUCOPTER,0) will setup this output for control by auto missions or MAVLink servo set commands. any other value will enable the corresponding function
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
1 | RCPassThru
2 | Flap
3 | Flap_auto
4 | Aileron
6 | mount_pan
7 | mount_tilt
8 | mount_roll
9 | mount_open
10 | camera_trigger
11 | release
12 | mount2_pan
13 | mount2_tilt
14 | mount2_roll
15 | mount2_open
16 | DifferentialSpoiler1
17 | DifferentialSpoiler2
18 | AileronWithInput
19 | Elevator
20 | ElevatorWithInput
21 | Rudder
24 | Flaperon1
25 | Flaperon2
26 | GroundSteering
27 | Parachute
28 | EPM
29 | LandingGear
30 | EngineRunEnable
31 | HeliRSC
32 | HeliTailRSC
33 | Motor1
34 | Motor2
35 | Motor3
36 | Motor4
37 | Motor5
38 | Motor6
39 | Motor7
40 | Motor8
51 | RCIN1
52 | RCIN2
53 | RCIN3
54 | RCIN4
55 | RCIN5
56 | RCIN6
57 | RCIN7
58 | RCIN8
59 | RCIN9
60 | RCIN10
61 | RCIN11
62 | RCIN12
63 | RCIN13
64 | RCIN14
65 | RCIN15
66 | RCIN16
67 | Ignition
68 | Choke
69 | Starter
70 | Throttle


## RC8_MAX: RC max PWM
RC maximum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1900

Min | Max
--- | ---
 800 | 2200

## RC8_MIN: RC min PWM
RC minimum PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1100

Min | Max
--- | ---
 800 | 2200

## RC8_REV: RC reversed
Reverse servo operation. Set to 1 for normal (MAV_PARAM_GROUP.ARDUCOPTER,forward) operation. Set to -1 to reverse this channel.
Unit: list
Default Value: 1

Key | Value
--- | ---
-1 | Reversed
1 | Normal


## RC8_TRIM: RC trim PWM
RC trim (neutral) PWM pulse width. Typically 1000 is lower limit, 1500 is neutral and 2000 is upper limit.
Unit: PWM
Default Value: 1498

Min | Max
--- | ---
 800 | 2200

## RCMAP_PITCH: 
Pitch channel number. This is useful when you have a RC transmitter that can't change the channel order easily. Pitch is normally on channel 2, but you can move it to any channel with this parameter. Reboot is required for changes to take effect.
Unit: 
Default Value: 2


## RCMAP_ROLL: 
Roll channel number. This is useful when you have a RC transmitter that can't change the channel order easily. Roll is normally on channel 1, but you can move it to any channel with this parameter. Reboot is required for changes to take effect.
Unit: 
Default Value: 1


## RCMAP_THROTTLE: 
Throttle channel number. This is useful when you have a RC transmitter that can't change the channel order easily. Throttle is normally on channel 3, but you can move it to any channel with this parameter. Warning APM 2.X: Changing the throttle channel could produce unexpected fail-safe results if connection between receiver and on-board PPM Encoder is lost. Disabling on-board PPM Encoder is recommended. Reboot is required for changes to take effect.
Unit: 
Default Value: 3


## RCMAP_YAW: 
Yaw channel number. This is useful when you have a RC transmitter that can't change the channel order easily. Yaw (also known as rudder) is normally on channel 4, but you can move it to any channel with this parameter. Reboot is required for changes to take effect.
Unit: 
Default Value: 4


## RELAY_PIN: 
Digital pin number for first relay control. This is the pin used for camera control.
Unit: list
Default Value: 13

Key | Value
--- | ---
-1 | Disabled
13 | APM2 A9 pin
47 | APM1 relay
111 | PX4 FMU Relay1
112 | PX4 FMU Relay2
113 | PX4IO Relay1
50 | Pixhawk AUXOUT1
114 | PX4IO Relay2
51 | Pixhawk AUXOUT2
115 | PX4IO ACC1
52 | Pixhawk AUXOUT3
116 | PX4IO ACC2
53 | Pixhawk AUXOUT4
54 | Pixhawk AUXOUT5
55 | Pixhawk AUXOUT6


## RELAY_PIN2: 
Digital pin number for 2nd relay control.
Unit: list
Default Value: -1

Key | Value
--- | ---
-1 | Disabled
13 | APM2 A9 pin
47 | APM1 relay
111 | PX4 FMU Relay1
112 | PX4 FMU Relay2
113 | PX4IO Relay1
50 | Pixhawk AUXOUT1
114 | PX4IO Relay2
51 | Pixhawk AUXOUT2
115 | PX4IO ACC1
52 | Pixhawk AUXOUT3
116 | PX4IO ACC2
53 | Pixhawk AUXOUT4
54 | Pixhawk AUXOUT5
55 | Pixhawk AUXOUT6


## RNGFND_FUNCTION: 
Control over what function is used to calculate distance. For a linear function, the distance is (voltage-offset)*scaling. For a inverted function the distance is (offset-voltage)*scaling. For a hyperbolic function the distance is scaling/(voltage-offset). The functions return the distance in meters.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Linear
1 | Inverted
2 | Hyperbolic


## RNGFND_GAIN: 
Used to adjust the speed with which the target altitude is changed when objects are sensed below the copter
Unit: 
Default Value: 0.8

Min | Max
--- | ---
 0.01 | 2.0

## RNGFND_MAX_CM: 
Maximum distance in centimeters that rangefinder can reliably read
Unit: cm
Default Value: 700


## RNGFND_MIN_CM: 
Minimum distance in centimeters that rangefinder can reliably read
Unit: cm
Default Value: 20


## RNGFND_OFFSET: 
Offset in volts for zero distance for analog rangefinders. Offset added to distance in centimeters for PWM and I2C Lidars
Unit: volt
Default Value: 0


## RNGFND_PIN: 
Analog pin that rangefinder is connected to. Set this to 0..9 for the APM2 analog pins. Set to 64 on an APM1 for the dedicated 'airspeed' port on the end of the board. Set to 11 on PX4 for the analog 'airspeed' port. Set to 15 on the Pixhawk for the analog 'airspeed' port.
Unit: list
Default Value: -1

Key | Value
--- | ---
-1 | NotUsed
0 | APM2-A0
64 | APM1-airspeed port
1 | APM2-A1
2 | APM2-A2
3 | APM2-A3
4 | APM2-A4
5 | APM2-A5
6 | APM2-A6
7 | APM2-A7
8 | APM2-A8
9 | APM2-A9
11 | PX4-airspeed port
15 | Pixhawk-airspeed port


## RNGFND_RMETRIC: 
This parameter sets whether an analog rangefinder is ratiometric. Most analog rangefinders are ratiometric, meaning that their output voltage is influenced by the supply voltage. Some analog rangefinders (such as the SF/02) have their own internal voltage regulators so they are not ratiometric.
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | No
1 | Yes


## RNGFND_SCALING: Rangefinder scaling
Scaling factor between rangefinder reading and distance. For the linear and inverted functions this is in meters per volt. For the hyperbolic function the units are meterVolts.
Unit: m/volt
Default Value: 3


## RNGFND_SETTLE_MS: Rangefinder reading delay
The time in milliseconds that the rangefinder reading takes to settle. This is only used when a STOP_PIN is specified. It determines how long we have to wait for the rangefinder to give a reading after we set the STOP_PIN high. For a sonar rangefinder with a range of around 7m this would need to be around 50 milliseconds to allow for the sonar pulse to travel to the target and back again.
Unit: millisecond
Default Value: 0


## RNGFND_STOP_PIN: 
Digital pin that enables/disables rangefinder measurement for an analog rangefinder. A value of -1 means no pin. If this is set, then the pin is set to 1 to enable the rangefinder and set to 0 to disable it. This can be used to ensure that multiple sonar rangefinders don't interfere with each other.
Unit: list
Default Value: -1

Key | Value
--- | ---
-1 | NotUsed
111 | PX4 FMU Relay1
112 | PX4 FMU Relay2
113 | PX4IO,Relay1
50 | Pixhawk AUXOUT1
114 | PX4IO Relay2
51 | Pixhawk AUXOUT2
115 | PX4IO ACC1
52 | Pixhawk AUXOUT3
116 | PX4IO ACC2
53 | Pixhawk AUXOUT4
54 | Pixhawk AUXOUT5
55 | Pixhawk AUXOUT6


## RNGFND_TYPE: 
What type of rangefinder device that is connected
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | None
1 | Analog
2 | APM2-MaxbotixI2C
3 | APM2-PulsedLightI2C
4 | PX4-I2C
5 | PX4-PWM
6 | BBB-PRU
7 | LightWareI2C
8 | LightWareSerial
9 | Bebop
10 | MAVLink
12 | LeddarOne


## RSSI_PIN: Receiver RSSI sensing pin
This selects an analog pin for the receiver RSSI voltage. It assumes the voltage is RSSI_RANGE for max rssi, 0V for minimum
Unit: list
Default Value: -1

Key | Value
--- | ---
-1 | Disabled
0 | APM2 A0
1 | APM2 A1
2 | APM2 A2
103 | Pixhawk SBUS
13 | APM2 A13


## RSSI_RANGE: Receiver RSSI voltage range
Receiver RSSI voltage range
Unit: volt
Default Value: 5

Key | Value
--- | ---
5 | 5V
3.3 | 3.3V


## RTL_ALT: 
The minimum relative altitude the model will move to before Returning to Launch. Set to zero to return at current altitude.
Unit: cm
Default Value: 1500

Min | Max
--- | ---
 0 | 8000

## RTL_ALT_FINAL: 
This is the altitude the vehicle will move to as the final stage of Returning to Launch or after completing a mission. Set to zero to land.
Unit: cm
Default Value: 0

Min | Max
--- | ---
 -1 | 1000

## RTL_LOIT_TIME: 
Time (in milliseconds) to loiter above home before beginning final descent
Unit: m/s
Default Value: 5000

Min | Max
--- | ---
 0 | 60000

## SCHED_DEBUG: Scheduler debug level
Set to non-zero to enable scheduler debug messages. When set to show "Slips" the scheduler will display a message whenever a scheduled task is delayed due to too much CPU load. When set to ShowOverruns the scheduled will display a message whenever a task takes longer than the limit promised in the task table.
Unit: list
Default Value: 0

Key | Value
--- | ---
0 | Disabled
2 | ShowSlips
3 | ShowOverruns


## SERIAL0_BAUD: 
The baud rate used on the USB console. The APM2 can support all baudrates up to 115, and also can support 500. The PX4 can support rates of up to 1500. If you setup a rate you cannot support on APM2 and then can't connect to your board you should load a firmware from a different vehicle type. That will reset all your parameters to defaults.
Unit: list
Default Value: 115

Key | Value
--- | ---
1 | 1200
2 | 2400
19 | 19200
115 | 115200
4 | 4800
500 | 500000
38 | 38400
9 | 9600
57 | 57600
921 | 921600
1500 | 1500000
111 | 111100


## SERIAL1_BAUD: 
The baud rate used on the Telem1 port. The APM2 can support all baudrates up to 115, and also can support 500. The PX4 can support rates of up to 1500. If you setup a rate you cannot support on APM2 and then can't connect to your board you should load a firmware from a different vehicle type. That will reset all your parameters to defaults.
Unit: list
Default Value: 57

Key | Value
--- | ---
1 | 1200
2 | 2400
19 | 19200
115 | 115200
4 | 4800
500 | 500000
38 | 38400
9 | 9600
57 | 57600
921 | 921600
1500 | 1500000
111 | 111100


## SIMPLE: Simple mode bitmask
Bitmask which holds which flight modes use simple heading mode (eg bit 0 = 1 means Flight Mode 0 uses simple mode)
Unit: 
Default Value: 18


## SUPER_SIMPLE: Super Simple Mode
Bitmask to enable Super Simple mode for some flight modes. Setting this to Disabled(0) will disable Super Simple Mode
Unit: list
Default Value: 5

Key | Value
--- | ---
0 | Disabled
1 | Mode1
2 | Mode2
3 | Mode1+2
4 | Mode3
5 | Mode1+3
6 | Mode2+3
7 | Mode1+2+3
8 | Mode4
9 | Mode1+4
10 | Mode2+4
11 | Mode1+2+4
12 | Mode3+4
13 | Mode1+3+4
14 | Mode2+3+4
15 | Mode1+2+3+4
16 | Mode5
17 | Mode1+5
18 | Mode2+5
19 | Mode1+2+5
20 | Mode3+5
21 | Mode1+3+5
22 | Mode2+3+5
23 | Mode1+2+3+5
24 | Mode4+5
25 | Mode1+4+5
26 | Mode2+4+5
27 | Mode1+2+4+5
28 | Mode3+4+5
29 | Mode1+3+4+5
30 | Mode2+3+4+5
31 | Mode1+2+3+4+5
32 | Mode6
33 | Mode1+6
34 | Mode2+6
35 | Mode1+2+6
36 | Mode3+6
37 | Mode1+3+6
38 | Mode2+3+6
39 | Mode1+2+3+6
40 | Mode4+6
41 | Mode1+4+6
42 | Mode2+4+6
43 | Mode1+2+4+6
44 | Mode3+4+6
45 | Mode1+3+4+6
46 | Mode2+3+4+6
47 | Mode1+2+3+4+6
48 | Mode5+6
49 | Mode1+5+6
50 | Mode2+5+6
51 | Mode1+2+5+6
52 | Mode3+5+6
53 | Mode1+3+5+6
54 | Mode2+3+5+6
55 | Mode1+2+3+5+6
56 | Mode4+5+6
57 | Mode1+4+5+6
58 | Mode2+4+5+6
59 | Mode1+2+4+5+6
60 | Mode3+4+5+6
61 | Mode1+3+4+5+6
62 | Mode2+3+4+5+6
63 | Mode1+2+3+4+5+6


## SR0_EXT_STAT: Extended status stream rate to ground station
Stream rate of SYS_STATUS, MEMINFO, MISSION_CURRENT, GPS_RAW_INT, NAV_CONTROLLER_OUTPUT, and LIMITS_STATUS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR0_EXTRA1: Extra data type 1 stream rate to ground station
Stream rate of ATTITUDE and SIMSTATE (SITL only) to ground station
Unit: Hz
Default Value: 4

Min | Max
--- | ---
 0 | 10

## SR0_EXTRA2: Extra data type 2 stream rate to ground station
Stream rate of VFR_HUD to ground station
Unit: Hz
Default Value: 4

Min | Max
--- | ---
 0 | 10

## SR0_EXTRA3: Extra data type 3 stream rate to ground station
Stream rate of AHRS, HWSTATUS, and SYSTEM_TIME to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR0_PARAMS: Parameter stream rate to ground station
Stream rate of PARAM_VALUE to ground station
Unit: Hz
Default Value: 10

Min | Max
--- | ---
 0 | 10

## SR0_POSITION: Position stream rate to ground station
Stream rate of GLOBAL_POSITION_INT to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR0_RAW_CTRL: Raw Control stream rate to ground station
Stream rate of RC_CHANNELS_SCALED (HIL only) to ground station
Unit: Hz
Default Value: 1

Min | Max
--- | ---
 0 | 10

## SR0_RAW_SENS: Raw sensor stream rate
Stream rate of RAW_IMU, SCALED_IMU2, SCALED_PRESSURE, and SENSOR_OFFSETS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR0_RC_CHAN: RC Channel stream rate to ground station
Stream rate of SERVO_OUTPUT_RAW and RC_CHANNELS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_EXT_STAT: Extended status stream rate to ground station
Stream rate of SYS_STATUS, MEMINFO, MISSION_CURRENT, GPS_RAW_INT, NAV_CONTROLLER_OUTPUT, and LIMITS_STATUS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_EXTRA1: Extra data type 1 stream rate to ground station
Stream rate of ATTITUDE and SIMSTATE (SITL only) to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_EXTRA2: Extra data type 2 stream rate to ground station
Stream rate of VFR_HUD to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_EXTRA3: Extra data type 3 stream rate to ground station
Stream rate of AHRS, HWSTATUS, and SYSTEM_TIME to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_PARAMS: Parameter stream rate to ground station
Stream rate of PARAM_VALUE to ground station
Unit: Hz
Default Value: 0

Min | Max
--- | ---
 0 | 10

## SR1_POSITION: Position stream rate to ground station
Stream rate of GLOBAL_POSITION_INT to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_RAW_CTRL: Raw Control stream rate to ground station
Stream rate of RC_CHANNELS_SCALED (HIL only) to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_RAW_SENS: Raw sensor stream rate
Stream rate of RAW_IMU, SCALED_IMU2, SCALED_PRESSURE, and SENSOR_OFFSETS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## SR1_RC_CHAN: RC Channel stream rate to ground station
Stream rate of SERVO_OUTPUT_RAW and RC_CHANNELS to ground station
Unit: Hz
Default Value: 2

Min | Max
--- | ---
 0 | 10

## STB_PIT_P: Pitch axis stabilize controller P gain
Pitch axis stabilize (i.e. angle) controller P gain. Converts the error between the desired pitch angle and actual angle to a desired pitch rate
Unit: 
Default Value: 0.016

Min | Max
--- | ---
 3 | 12

## STB_RLL_P: Roll axis stabilize controller P gain
Roll axis stabilize (i.e. angle) controller P gain. Converts the error between the desired roll angle and actual angle to a desired roll rate
Unit: 
Default Value: 0.016

Min | Max
--- | ---
 3 | 12

## STB_YAW_P: Yaw axis stabilize controller P gain
Yaw axis stabilize (i.e. angle) controller P gain. Converts the error between the desired yaw angle and actual angle to a desired yaw rate
Unit: 
Default Value: 4

Min | Max
--- | ---
 3 | 6

## SYSID_MYGCS: My ground station number
Allows restricting radio overrides to only come from my ground station
Unit: list
Default Value: 255

Key | Value
--- | ---
252 | AP Planner 2
255 | Mission Planner and DroidPlanner


## SYSID_SW_MREV: Eeprom format version number
This value is incremented when changes are made to the eeprom format
Unit: 
Default Value: 120


## SYSID_SW_TYPE: 
This is used by the ground station to recognise the software type (MAV_PARAM_GROUP.ARDUCOPTER,eg ArduPlane vs ArduCopter)
Unit: list
Default Value: 10

Key | Value
--- | ---
0 | ArduPlane
4 | AntennaTracker
20 | Rover
10 | Copter


## SYSID_THISMAV: Allows recognising the mavlink version
Allows setting an individual MAVLink system id for this vehicle to distinguish it from others on the same network
Unit: 
Default Value: 1

Min | Max
--- | ---
 1 | 255

## TELEM_DELAY: Telemetry startup delay
The amount of time (in seconds) to delay radio telemetry to prevent an Xbee bricking on power up
Unit: second
Default Value: 0

Min | Max
--- | ---
 0 | 10

## THR_ACCEL_D: Throttle acceleration controller D gain
Throttle acceleration controller D gain. Compensates for short-term change in desired vertical acceleration vs actual acceleration
Unit: 
Default Value: 0

Min | Max
--- | ---
 0 | 0.4

## THR_ACCEL_I: Throttle acceleration controller I gain
Throttle acceleration controller I gain. Corrects long-term difference in desired vertical acceleration and actual acceleration
Unit: 
Default Value: 1

Min | Max
--- | ---
 0 | 3

## THR_ACCEL_IMAX: Throttle acceleration controller I gain maximum
Throttle acceleration controller I gain maximum. Constrains the maximum pwm that the I term will generate
Unit: percent
Default Value: 800

Min | Max
--- | ---
 0 | 1000

## THR_ACCEL_P: Throttle acceleration controller P gain
Throttle acceleration controller P gain. Converts the difference between desired vertical acceleration and actual acceleration into a motor output
Unit: 
Default Value: 0.5

Min | Max
--- | ---
 0.5 | 1.5

## THR_ALT_P: Altitude controller P gain
Altitude controller P gain. Converts the difference between the desired altitude and actual altitude into a climb or descent rate which is passed to the throttle rate controller
Unit: 
Default Value: 1

Min | Max
--- | ---
 1 | 3

## THR_DZ: 
The deadzone above and below mid throttle. Used in AltHold, Loiter, PosHold flight modes
Unit: PWM
Default Value: 100

Min | Max
--- | ---
 0 | 300

## THR_MAX: Throttle Maximum
The maximum throttle that will be sent to the motors. This should normally be left as 1000. 
Unit: percent
Default Value: 1000

Min | Max
--- | ---
 800 | 1000

## THR_MID: Throttle Mid Position
The throttle output (0 ~ 1000) when throttle stick is in mid position. Used to scale the manual throttle so that the mid throttle stick position is close to the throttle required to hover
Unit: percent
Default Value: 510

Min | Max
--- | ---
 300 | 700

## THR_MIN: Throttle Minimum
The minimum throttle that will be sent to the motors to keep them spinning
Unit: percent
Default Value: 130

Min | Max
--- | ---
 0 | 300

## THR_RATE_P: Throttle rate controller P gain
Throttle rate controller P gain. Converts the difference between desired vertical speed and actual speed into a desired acceleration that is passed to the throttle acceleration controller
Unit: 
Default Value: 6

Min | Max
--- | ---
 1 | 8

## TRIM_THROTTLE: Throttle Trim
The autopilot’s estimate of the throttle required to maintain a level hover. Calculated automatically from the pilot’s throttle input while in stabilize mode
Unit: percent
Default Value: 724

Min | Max
--- | ---
 0 | 1000

## TUNE: 
Controls which parameters (MAV_PARAM_GROUP.ARDUCOPTER,normally PID gains) are being tuned with transmitter's channel 6 knob
Unit: list
Default Value: 1

Key | Value
--- | ---
0 | None
1 | Stab_Roll/Pitch_kP
3 | Stab_Yaw_kP
4 | Rate_Roll/Pitch_kP
5 | Rate_Roll/Pitch_kI
6 | Rate_Yaw_kP
7 | Throttle_Rate_kP
10 | WP_Speed
12 | Loiter_Pos_kP
13 | Heli_Ext_Gyro
14 | Altitude_Hold_kP
17 | OF_Loiter_kP
18 | OF_Loiter_kI
19 | OF_Loiter_kD
21 | Rate_Roll/Pitch_kD
22 | Velocity_XY_kP
25 | Acro_RollPitch_kP
26 | Rate_Yaw_kD
28 | Velocity_XY_kI
34 | Throttle_Accel_kP
35 | Throttle_Accel_kI
36 | Throttle_Accel_kD
38 | Declination
39 | Circle_Rate
40 | Acro_Yaw_kP
41 | RangeFinder_Gain
42 | Loiter_Speed
46 | Rate_Pitch_kP
47 | Rate_Pitch_kI
48 | Rate_Pitch_kD
49 | Rate_Roll_kP
50 | Rate_Roll_kI
51 | Rate_Roll_kD
52 | Rate_Pitch_FF
53 | Rate_Roll_FF
54 | Rate_Yaw_FF


## TUNE_HIGH: Tuning maximum
The maximum value that will be applied to the parameter currently being tuned with the transmitter's channel 6 knob
Unit: 
Default Value: 32

Min | Max
--- | ---
 0 | 32767

## TUNE_LOW: Tuning minimum
The minimum value that will be applied to the parameter currently being tuned with the transmitter's channel 6 knob
Unit: 
Default Value: 0

Min | Max
--- | ---
 0 | 32767

## WP_YAW_BEHAVIOR: 
Determines how the autopilot controls the yaw during missions and RTL
Unit: list
Default Value: 2

Key | Value
--- | ---
0 | NeverChangeYaw
1 | FaceNextWaypoint
2 | FaceNextWaypointExceptRTL
3 | FaceAlongGPScourse


## WPNAV_ACCEL: 
Defines the horizontal acceleration in CENTIMETER/s/s used during missions
Unit: cm/s/s
Default Value: 100

Min | Max
--- | ---
 50 | 500

## WPNAV_ACCEL_Z: 
Defines the vertical acceleration in CENTIMETER/s/s used during missions
Unit: cm/s/s
Default Value: 100

Min | Max
--- | ---
 50 | 500

## WPNAV_LOIT_JERK: 
Loiter maximum jerk in CENTIMETER/s/s/s
Unit: cm/s/s/s
Default Value: 1000

Min | Max
--- | ---
 500 | 5000

## WPNAV_LOIT_SPEED: 
Defines the maximum speed in CENTIMETER/s which the aircraft will travel horizontally while in loiter mode
Unit: cm/s
Default Value: 1000

Min | Max
--- | ---
 20 | 2000

## WPNAV_RADIUS: 
Defines the distance from a waypoint, that when crossed indicates the wp has been hit.
Unit: cm
Default Value: 200

Min | Max
--- | ---
 100 | 1000

## WPNAV_SPEED: 
Defines the speed in CENTIMETER/s which the aircraft will attempt to maintain horizontally during a WP mission
Unit: cm/s
Default Value: 500

Min | Max
--- | ---
 0 | 2000

## WPNAV_SPEED_DN: 
Defines the speed in CENTIMETER/s which the aircraft will attempt to maintain while descending during a WP mission
Unit: cm/s
Default Value: 150

Min | Max
--- | ---
 10 | 500

## WPNAV_SPEED_UP: Waypoint Climb Speed Target
Defines the speed in CENTIMETER/s which the aircraft will attempt to maintain while climbing during a WP mission
Unit: cm/s
Default Value: 250

Min | Max
--- | ---
 10 | 1000

