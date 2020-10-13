
package com.dronegcs.mavlink.is.protocol.msg_metadata;

import java.io.Serializable;

public abstract class MAVLinkMessage implements Serializable {
	private static final long serialVersionUID = -7754622750478538539L;
	// The MAVLink message classes have been changed to implement Serializable, 
	// this way is possible to pass a com.dronegcs.mavlink.is.mavlink message trought the Service-Acctivity interface
	
	/**
	 *  Simply a common interface for all MAVLink Messagess
	 */
	
	public final int sysid;
	public final int compid;
	public int msgid;

	protected MAVLinkMessage(int sysid) {
		this.sysid = sysid;
		this.compid = 190;
	}

	protected MAVLinkPacket build(int length) {
		MAVLinkPacket packet = new MAVLinkPacket();
		packet.len = length;
		packet.sysid = sysid;
		packet.compid = compid;
		packet.msgid = msgid;
		return packet;
	}

	public abstract MAVLinkPacket pack();

	public abstract void unpack(MAVLinkPayload payload);

}
	