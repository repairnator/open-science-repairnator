// MESSAGE HIL_STATE_QUATERNION PACKING
package com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkMessage;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPayload;
/**
* Sent from simulation to autopilot, avoids in contrast to HIL_STATE singularities. This packet is useful for high throughput applications such as hardware in the loop simulations.
*/
public class msg_hil_state_quaternion extends MAVLinkMessage{

	public static final int MAVLINK_MSG_ID_HIL_STATE_QUATERNION = 115;
	public static final int MAVLINK_MSG_LENGTH = 64;
	private static final long serialVersionUID = MAVLINK_MSG_ID_HIL_STATE_QUATERNION;
	

 	/**
	* Timestamp (microseconds since UNIX epoch or microseconds since system boot)
	*/
	public long time_usec; 
 	/**
	* Vehicle attitude expressed as normalized quaternion in w, x, y, z order (with 1 0 0 0 being the null-rotation)
	*/
	public float attitude_quaternion[] = new float[4]; 
 	/**
	* Body frame roll / phi angular speed (rad/s)
	*/
	public float rollspeed; 
 	/**
	* Body frame pitch / theta angular speed (rad/s)
	*/
	public float pitchspeed; 
 	/**
	* Body frame yaw / psi angular speed (rad/s)
	*/
	public float yawspeed; 
 	/**
	* Latitude, expressed as * 1E7
	*/
	public int lat; 
 	/**
	* Longitude, expressed as * 1E7
	*/
	public int lon; 
 	/**
	* Altitude in meters, expressed as * 1000 (millimeters)
	*/
	public int alt; 
 	/**
	* Ground X Speed (Latitude), expressed as m/s * 100
	*/
	public short vx; 
 	/**
	* Ground Y Speed (Longitude), expressed as m/s * 100
	*/
	public short vy; 
 	/**
	* Ground Z Speed (Altitude), expressed as m/s * 100
	*/
	public short vz; 
 	/**
	* Indicated airspeed, expressed as m/s * 100
	*/
	public short ind_airspeed; 
 	/**
	* True airspeed, expressed as m/s * 100
	*/
	public short true_airspeed; 
 	/**
	* X acceleration (mg)
	*/
	public short xacc; 
 	/**
	* Y acceleration (mg)
	*/
	public short yacc; 
 	/**
	* Z acceleration (mg)
	*/
	public short zacc; 

	/**
	 * Generates the payload for a com.dronegcs.mavlink.is.mavlink message for a message of this type
	 * @return
	 */
	public MAVLinkPacket pack(){
		MAVLinkPacket packet = build(MAVLINK_MSG_LENGTH);
		packet.payload.putLong(time_usec);
		 for (int i = 0; i < attitude_quaternion.length; i++) {
                        packet.payload.putFloat(attitude_quaternion[i]);
            }
		packet.payload.putFloat(rollspeed);
		packet.payload.putFloat(pitchspeed);
		packet.payload.putFloat(yawspeed);
		packet.payload.putInt(lat);
		packet.payload.putInt(lon);
		packet.payload.putInt(alt);
		packet.payload.putShort(vx);
		packet.payload.putShort(vy);
		packet.payload.putShort(vz);
		packet.payload.putShort(ind_airspeed);
		packet.payload.putShort(true_airspeed);
		packet.payload.putShort(xacc);
		packet.payload.putShort(yacc);
		packet.payload.putShort(zacc);
		return packet;		
	}

    /**
     * Decode a hil_state_quaternion message into this class fields
     *
     * @param payload The message to decode
     */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
	    time_usec = payload.getLong();
	     for (int i = 0; i < attitude_quaternion.length; i++) {
			attitude_quaternion[i] = payload.getFloat();
		}
	    rollspeed = payload.getFloat();
	    pitchspeed = payload.getFloat();
	    yawspeed = payload.getFloat();
	    lat = payload.getInt();
	    lon = payload.getInt();
	    alt = payload.getInt();
	    vx = payload.getShort();
	    vy = payload.getShort();
	    vz = payload.getShort();
	    ind_airspeed = payload.getShort();
	    true_airspeed = payload.getShort();
	    xacc = payload.getShort();
	    yacc = payload.getShort();
	    zacc = payload.getShort();    
    }

     /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_hil_state_quaternion(int sysid){
		super(sysid);
    	msgid = MAVLINK_MSG_ID_HIL_STATE_QUATERNION;
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a com.dronegcs.mavlink.is.mavlink packet
     * 
     */
    public msg_hil_state_quaternion(MAVLinkPacket mavLinkPacket){
        this(mavLinkPacket.sysid);
        unpack(mavLinkPacket.payload);
        //Log.d("MAVLink", "HIL_STATE_QUATERNION");
        //Log.d("MAVLINK_MSG_ID_HIL_STATE_QUATERNION", toString());
    }
    
                                
    /**
     * Returns a string with the MSG name and data
     */
    public String toString(){
    	return "MAVLINK_MSG_ID_HIL_STATE_QUATERNION -"+" time_usec:"+time_usec+" attitude_quaternion:"+attitude_quaternion+" rollspeed:"+rollspeed+" pitchspeed:"+pitchspeed+" yawspeed:"+yawspeed+" lat:"+lat+" lon:"+lon+" alt:"+alt+" vx:"+vx+" vy:"+vy+" vz:"+vz+" ind_airspeed:"+ind_airspeed+" true_airspeed:"+true_airspeed+" xacc:"+xacc+" yacc:"+yacc+" zacc:"+zacc+"";
    }
}
