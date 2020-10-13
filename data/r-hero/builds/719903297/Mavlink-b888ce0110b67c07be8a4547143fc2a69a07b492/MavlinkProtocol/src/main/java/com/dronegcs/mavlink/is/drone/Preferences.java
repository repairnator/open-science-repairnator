package com.dronegcs.mavlink.is.drone;

import com.dronegcs.mavlink.core.firmware.FirmwareType;
import com.dronegcs.mavlink.is.drone.profiles.VehicleProfile;

public interface Preferences {

	public abstract FirmwareType getVehicleType();

	public abstract VehicleProfile loadVehicleProfile(FirmwareType firmwareType);

	VehicleProfile getProfile();

	public abstract Rates getRates();

	public abstract void setRates(Rates rates);

	public class Rates {

		public final static int DEFAULT_RATE_ALTITUDE = 10;
		public final static int DEFAULT_RATE_STATUS = 1;
		public final static int DEFAULT_RATE_SENSORS = 1;
		public final static int DEFAULT_RATE_POSITION = 1;
		public final static int DEFAULT_RATE_RC_CHANNELS = 1;
		public final static int DEFAULT_RATE_CONTROLLER = 1;


		public int extendedStatus;
		public int extra1;
		public int extra2;
		public int extra3;
		public int position;
		public int rcChannels;
		public int rawSensors;
		public int rawController;
		
		public Rates(	int extendedStatus, 
						int extra1, int extra2, int extra3, 
						int position, int rcChannels, int rawSensors,
						int rawControler) {
			this.extendedStatus = extendedStatus;
			this.extra1 = extra1;
			this.extra2 = extra2;
			this.extra3 = extra3;
			this.position = position;
			this.rcChannels = rcChannels;
			this.rawSensors = rawSensors;
			this.rawController = rawControler;
		}
	}
}
