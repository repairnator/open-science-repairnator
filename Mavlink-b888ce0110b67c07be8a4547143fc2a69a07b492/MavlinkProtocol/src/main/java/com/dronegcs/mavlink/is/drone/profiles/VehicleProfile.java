package com.dronegcs.mavlink.is.drone.profiles;

import com.dronegcs.mavlink.is.protocol.msg_metadata.enums.MAV_PARAM_I;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public abstract class VehicleProfile {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(VehicleProfile.class);

	private Map<String, MAV_PARAM_I> parametersMetadata;
	//	private String parameterMetadataType;
	private Default default_ = new Default();

	protected abstract MAV_PARAM_I[] getParametersMetadataList();

//	public String getParameterMetadataType() {
//		return parameterMetadataType;
//	}

	public Default getDefault() {
		return default_;
	}

	public void setDefault(Default default_) {
		this.default_ = default_;
	}

//	public void setParameterMetadataType(String parameterMetadataType) {
//		this.parameterMetadataType = parameterMetadataType;
//	}

	public void init() {
		parametersMetadata = new HashMap<>();
		for (MAV_PARAM_I val : getParametersMetadataList()) {
			parametersMetadata.put(val.getName(), val);
		}
	}

	public Map<String, MAV_PARAM_I> getParametersMetadataMap() {
		return parametersMetadata;
	}

	public static class Default {
		private int wpNavSpeed;
		private int maxAltitude;

		public int getWpNavSpeed() {
			return wpNavSpeed;
		}

		public void setWpNavSpeed(int wpNavSpeed) {
			this.wpNavSpeed = wpNavSpeed;
		}

		public int getMaxAltitude() {
			return maxAltitude;
		}

		public void setMaxAltitude(int maxAltitude) {
			this.maxAltitude = maxAltitude;
		}
	}
}
