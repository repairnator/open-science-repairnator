package com.dronegcs.mavlink.is.protocol.msg_metadata;

/**
 * Storage for MAVLink Packet and Error statistics
 * 
 */
public class MAVLinkStats /* implements Serializable */{

	private int receivedPacketCount;

	private int crcErrorCount;

	private int lostPacketCount;

	private int lastPacketSeq;

	private int receivedUncategorizedPackets;

	/**
	 * Check the new received packet to see if has lost someone between this and
	 * the last packet
	 * 
	 * @param packet
	 *            Packet that should be checked
	 */
	public void newPacket(MAVLinkPacket packet) {
		advanceLastPacketSequence();
		if (hasLostPackets(packet)) {
			updateLostPacketCount(packet);
		}
		lastPacketSeq = packet.seq;
		receivedPacketCount++;
	}

	private void updateLostPacketCount(MAVLinkPacket packet) {
		int lostPackets;
		if (packet.seq - lastPacketSeq < 0) {
			lostPackets = (packet.seq - lastPacketSeq) + 255;
		} else {
//			System.out.println("Stat: " + packet.seq + " " + lastPacketSeq);
			lostPackets = (packet.seq - lastPacketSeq);
		}
		lostPacketCount += lostPackets;
	}

	private boolean hasLostPackets(MAVLinkPacket packet) {
		return lastPacketSeq > 0 && packet.seq != lastPacketSeq;
	}

	private void advanceLastPacketSequence() {
		// wrap from 255 to 0 if necessary
		lastPacketSeq = (lastPacketSeq + 1) & 0xFF;
	}

	/**
	 * Called when a CRC error happens on the parser
	 */
	public void advancedCrcError() {
		crcErrorCount++;
	}

	public int getReceivedPacketCount() {
		return receivedPacketCount;
	}

	public void setReceivedPacketCount(int receivedPacketCount) {
		this.receivedPacketCount = receivedPacketCount;
	}

	public int getCrcErrorCount() {
		return crcErrorCount;
	}

	public void setCrcErrorCount(int crcErrorCount) {
		this.crcErrorCount = crcErrorCount;
	}

	public int getLostPacketCount() {
		return lostPacketCount;
	}

	public void setLostPacketCount(int lostPacketCount) {
		this.lostPacketCount = lostPacketCount;
	}

	public int getLastPacketSeq() {
		return lastPacketSeq;
	}

	public void setLastPacketSeq(int lastPacketSeq) {
		this.lastPacketSeq = lastPacketSeq;
	}

	/**
	 * Resets statistics for this MAVLink.
	 */
	public void resetStats() {
		lastPacketSeq = -1;
		lostPacketCount = 0;
		crcErrorCount = 0;
		receivedPacketCount = 0;
		receivedUncategorizedPackets = 0;
	}

	public void advancedReceivedUncategorizedPackets() {
		receivedUncategorizedPackets++;
	}

	public long getReceivedUncategorizedPackets() {
		return receivedUncategorizedPackets;
	}
}