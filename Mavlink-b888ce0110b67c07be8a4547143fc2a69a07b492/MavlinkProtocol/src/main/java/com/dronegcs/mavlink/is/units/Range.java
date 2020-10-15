package com.dronegcs.mavlink.is.units;

public class Range {

    private Number min;
    private Number max;

    public Range(Number min, Number max) {
        this.min = min;
        this.max = max;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }
}
