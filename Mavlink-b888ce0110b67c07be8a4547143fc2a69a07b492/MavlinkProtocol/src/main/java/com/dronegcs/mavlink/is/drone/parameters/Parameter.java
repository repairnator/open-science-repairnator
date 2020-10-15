package com.dronegcs.mavlink.is.drone.parameters;

import com.dronegcs.mavlink.is.protocol.msg_metadata.ardupilotmega.msg_param_value;
import com.dronegcs.mavlink.is.units.Range;
import com.generic_tools.Pair.Pair;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Map;

public class Parameter implements Comparable<Parameter>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4968235225796045915L;

	private final String name;
	private final Map<Number, String> options;
	private final String title;
	private final String group;
	private Number value;
	private final Number defaultValue;
	private final int type;
	private final Range range;
	private final String description;
	private final String unit;
	private final boolean readOnly;

	private final static DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
	static {
		format.applyPattern("0.###");
	}

	public Parameter(String name, String group, Number value, Number defaultValue, String unit, int type, boolean readOnly, Range range, String title, String description) {
		this.name = name;
		this.value = value;
		this.defaultValue = defaultValue;
		this.unit = unit;
		this.readOnly = readOnly;
		this.type = type;
		this.options = null;
		this.range = range;
		this.title = title;
		this.description = description;
		this.group = group;
	}

	public Parameter(String name, String group, Number value, Number defaultValue, String unit, int type, boolean readOnly, Map<Number,String> options, String title, String description) {
		this.name = name;
		this.value = value;
		this.defaultValue = defaultValue;
		this.unit = unit;
		this.readOnly = readOnly;
		this.type = type;
		this.options = options;
		this.range = null;
		this.title = title;
		this.description = description;
		this.group = group;
	}

	public Parameter(String name, String group, Number value, Number defaultValue, String unit, int type, boolean readOnly, String title, String description) {
		this.name = name;
		this.value = value;
		this.defaultValue = defaultValue;
		this.unit = unit;
		this.readOnly = readOnly;
		this.type = type;
		this.options = null;
		this.range = null;
		this.title = title;
		this.description = description;
		this.group = group;
	}

	public Number getValue() {
		return value;
	}
//	public String getValue() {
//		return format.format(value);
//	}

	public static void checkParameterName(String name) throws Exception {
		if (name.equals("SYSID_SW_MREV")) {
			throw new Exception("ExludedName");
		} else if (name.contains("WP_TOTAL")) {
			throw new Exception("ExludedName");
		} else if (name.contains("CMD_TOTAL")) {
			throw new Exception("ExludedName");
		} else if (name.contains("FENCE_TOTAL")) {
			throw new Exception("ExludedName");
		} else if (name.contains("SYS_NUM_RESETS")) {
			throw new Exception("ExludedName");
		} else if (name.contains("ARSPD_OFFSET")) {
			throw new Exception("ExludedName");
		} else if (name.contains("GND_ABS_PRESS")) {
			throw new Exception("ExludedName");
		} else if (name.contains("GND_TEMP")) {
			throw new Exception("ExludedName");
		} else if (name.contains("CMD_INDEX")) {
			throw new Exception("ExludedName");
		} else if (name.contains("LOG_LASTFILE")) {
			throw new Exception("ExludedName");
		} else if (name.contains("FORMAT_VERSION")) {
			throw new Exception("ExludedName");
		} else {
		}
	}

	public String getName() {
		return name;
	}

	public String getGroup() {
		return group;
	}

	public Number getDefaultValue() {
		return defaultValue;
	}

	public String getUnit() {
		return unit;
	}

	public int getType() {
		return type;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public Map<Number, String> getOptions() {
		return options;
	}

	public Range getRange() {
		return range;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public static DecimalFormat getFormat() {
		return format;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;

        Parameter parameter = (Parameter) o;

        return (name == null ? parameter.name == null : name.equals(parameter.name));
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public int compareTo(Parameter another) {
        return name.compareTo(another.name);
    }

	@Override
	public String toString() {
		return "Parameter{" +
				"name='" + name + '\'' +
				", value=" + value +
				", type=" + type +
				", range='" + range + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public void setValue(Number value) {
		this.value = value;
	}
}
