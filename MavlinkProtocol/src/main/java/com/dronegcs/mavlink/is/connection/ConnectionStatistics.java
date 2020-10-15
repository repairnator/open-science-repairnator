package com.dronegcs.mavlink.is.connection;

import java.io.PrintStream;

/**
 * Created by taljmars on 6/9/2017.
 */
public class ConnectionStatistics {

    /*********/
    /* Bytes */
    private long receivedBytes = 0;
    private long transmittedBytes = 0;
    // Received bps
    private long receivedBytesPerSecond = 0;
    // Transmitter bps
    private long transmittedBytesPerSecond = 0;

    /***********/
    /* Packets */
    private long receivedPackets = 0;
    private long receivedErrorPackets = 0;
    private long receivedUncategorizedPackets = 0;
    private long lostPackets;
    private long transmittedPackets = 0;
    private long transmittedErrorPackets = 0;
    // Received bps
    private long receivedPacketsPerSecond = 0;
    // Transmitter bps
    private long transmittedPacketsPerSecond = 0;
    private long latency;

    public long getReceivedBytes() {
        return receivedBytes;
    }

    public void setReceivedBytes(long receivedBytes) {
        this.receivedBytes = receivedBytes;
    }

    public long getTransmittedBytes() {
        return transmittedBytes;
    }

    public void setTransmittedBytes(long transmittedBytes) {
        this.transmittedBytes = transmittedBytes;
    }

    public long getReceivedBytesPerSecond() {
        return receivedBytesPerSecond;
    }

    public void setReceivedBytesPerSecond(long receivedBytesPerSecond) {
        this.receivedBytesPerSecond = receivedBytesPerSecond;
    }

    public long getTransmittedBytesPerSecond() {
        return transmittedBytesPerSecond;
    }

    public void setTransmittedBytesPerSecond(long transmittedBytesPerSecond) {
        this.transmittedBytesPerSecond = transmittedBytesPerSecond;
    }

    public long getReceivedPackets() {
        return receivedPackets;
    }

    public void setReceivedPackets(long receivedPackets) {
        this.receivedPackets = receivedPackets;
    }

    public long getReceivedErrorPackets() {
        return receivedErrorPackets;
    }

    public void setReceivedErrorPackets(long receivedErrorPackets) {
        this.receivedErrorPackets = receivedErrorPackets;
    }

    public long getLostPackets() {
        return lostPackets;
    }

    public void setLostPackets(long lostPackets) {
        this.lostPackets = lostPackets;
    }

    public long getTransmittedPackets() {
        return transmittedPackets;
    }

    public void setTransmittedPackets(long transmittedPackets) {
        this.transmittedPackets = transmittedPackets;
    }

    public long getTransmittedErrorPackets() {
        return transmittedErrorPackets;
    }

    public void setTransmittedErrorPackets(long transmittedErrorPackets) {
        this.transmittedErrorPackets = transmittedErrorPackets;
    }

    public long getReceivedPacketsPerSecond() {
        return receivedPacketsPerSecond;
    }

    public void setReceivedPacketsPerSecond(long receivedPacketsPerSecond) {
        this.receivedPacketsPerSecond = receivedPacketsPerSecond;
    }

    public long getTransmittedPacketsPerSecond() {
        return transmittedPacketsPerSecond;
    }

    public void setTransmittedPacketsPerSecond(long transmittedPacketsPerSecond) {
        this.transmittedPacketsPerSecond = transmittedPacketsPerSecond;
    }

    @Override
    public String toString() {
        return "ConnectionStatistics{" +
                "latency=" + latency +
                ", lostPackets=" + lostPackets +
                ", receivedBytes=" + receivedBytes +
                ", receivedBytesPerSecond=" + receivedBytesPerSecond +
                ", receivedPackets=" + receivedPackets +
                ", receivedPacketsPerSecond=" + receivedPacketsPerSecond +
                ", receivedErrorPackets=" + receivedErrorPackets +
                ", receivedUncategorizedPackets=" + receivedUncategorizedPackets +
                ", transmittedBytes=" + transmittedBytes +
                ", transmittedBytesPerSecond=" + transmittedBytesPerSecond +
                ", transmittedPackets=" + transmittedPackets +
                ", transmittedPacketsPerSecond=" + transmittedPacketsPerSecond +
                ", transmittedErrorPackets=" + transmittedErrorPackets +
                '}';
    }

    public void setLatency(long latency) {
        this.latency = latency;
    }

    public long getLatency() {
        return latency;
    }

    public void reset() {
        setLatency(0);
        setLostPackets(0);

        setReceivedBytes(0);
        setReceivedBytesPerSecond(0);
        setReceivedErrorPackets(0);
        setReceivedPackets(0);
        setReceivedPacketsPerSecond(0);
        setReceivedUncategorizedPackets(0);

        setTransmittedBytes(0);
        setTransmittedBytesPerSecond(0);
        setTransmittedErrorPackets(0);
        setTransmittedPackets(0);
        setTransmittedPacketsPerSecond(0);
    }

    public void dump(PrintStream out) {
        System.out.println("Dump stat");
        out.println("Conn Stat " + this.toString());
    }

    public void setReceivedUncategorizedPackets(long uncategorizedPackets) {
        receivedUncategorizedPackets = uncategorizedPackets;
    }

    public long getReceivedUncategorizedPackets() {
        return receivedUncategorizedPackets;
    }
}
