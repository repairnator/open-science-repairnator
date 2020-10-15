package com.dronegcs.mavlink.is.protocol.msgparser;

import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket;
import com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkStats;
import org.slf4j.LoggerFactory;

import static com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket.MAVLINK_1;
import static com.dronegcs.mavlink.is.protocol.msg_metadata.MAVLinkPacket.MAVLINK_2;

public class Parser {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Parser.class);

	private static void debug(String str) {
//		System.out.println(str);
	}
	/**
	 * States from the parsing state machine
	 */
	enum MAV_states {
		MAVLINK_PARSE_STATE_UNINIT, MAVLINK_PARSE_STATE_IDLE, MAVLINK_PARSE_STATE_GOT_STX, MAVLINK_PARSE_STATE_GOT_LENGTH, MAVLINK_PARSE_STATE_GOT_SEQ, MAVLINK_PARSE_STATE_GOT_SYSID, MAVLINK_PARSE_STATE_GOT_COMPID, MAVLINK_PARSE_STATE_GOT_MSGID, MAVLINK_PARSE_STATE_GOT_CRC1, MAVLINK_PARSE_STATE_GOT_INCOMP_FLAGS, MAVLINK_PARSE_STATE_GOT_COMP_FLAGS, MAVLINK_PARSE_STATE_GOT_PAYLOAD, MAVLINK_PARSE_STATE_GOT_LENGTH_M1, MAVLINK_PARSE_STATE_GOT_LENGTH_M2
	}

	private MAV_states state = MAV_states.MAVLINK_PARSE_STATE_UNINIT;

	private static boolean msg_received;

//	static byte[] debugBuffer = new byte[4096];
//	static int index = 0;

	private MAVLinkStats mavlinkStats = new MAVLinkStats();
	private MAVLinkPacket m;
	private int shifter = 0;

	/**
	 * This is a convenience function which handles the complete MAVLink
	 * parsing. the function will parse one byte at a time and return the
	 * complete packet once it could be successfully decoded. Checksum and other
	 * failures will be silently ignored.
	 *
	 * @param c
	 *            The char to parse
	 */
	public MAVLinkPacket mavlink_parse_char(int c) {
		msg_received = false;
//		debugBuffer[index] = (byte) c;
//		index++;

		switch (state) {
			case MAVLINK_PARSE_STATE_UNINIT:
			case MAVLINK_PARSE_STATE_IDLE:

				if (c == MAVLINK_1 || c == MAVLINK_2) {
					state = MAV_states.MAVLINK_PARSE_STATE_GOT_STX;
					m = new MAVLinkPacket(c);
					m.buffer[m.index++] = (byte) c;
				}
				switch (c) {
					case MAVLINK_1:
						debug("Packet of version 1 initialized");
						break;
					case MAVLINK_2:
						debug("Packet of version 2 initialized");
						break;
					default:
						debug("Packet of version unknown initialized");
						state = MAV_states.MAVLINK_PARSE_STATE_IDLE;
						break;
				}
				break;

			case MAVLINK_PARSE_STATE_GOT_STX:
				if (msg_received) {
					msg_received = false;
					state = MAV_states.MAVLINK_PARSE_STATE_IDLE;
				} else {
					m.buffer[m.index++] = (byte) c;
					m.len = c;
					debug("Len = " + c);
					if (m.version == MAVLINK_1) {
						state = MAV_states.MAVLINK_PARSE_STATE_GOT_LENGTH_M1;
					}
					else {
						state = MAV_states.MAVLINK_PARSE_STATE_GOT_LENGTH_M2;
					}
				}
				break;

			case MAVLINK_PARSE_STATE_GOT_LENGTH_M2:
				m.buffer[m.index++] = (byte) c;
				debug("incomp flags = " + c);
				m.incompatibility_flags = c;
				state = MAV_states.MAVLINK_PARSE_STATE_GOT_INCOMP_FLAGS;
				break;

			case MAVLINK_PARSE_STATE_GOT_INCOMP_FLAGS:
				m.buffer[m.index++] = (byte) c;
				debug("comp flags = " + c);
				m.compatibility_flags = c;
				state = MAV_states.MAVLINK_PARSE_STATE_GOT_COMP_FLAGS;
				break;

			case MAVLINK_PARSE_STATE_GOT_LENGTH_M1:
			case MAVLINK_PARSE_STATE_GOT_COMP_FLAGS:
				m.buffer[m.index++] = (byte) c;
				debug("seq = " + c);
				m.seq = c;
				state = MAV_states.MAVLINK_PARSE_STATE_GOT_SEQ;
				break;

			case MAVLINK_PARSE_STATE_GOT_SEQ:
				m.buffer[m.index++] = (byte) c;
				debug("sysid = " + c);
				m.sysid = c;
				state = MAV_states.MAVLINK_PARSE_STATE_GOT_SYSID;
				break;

			case MAVLINK_PARSE_STATE_GOT_SYSID:
				m.buffer[m.index++] = (byte) c;
				debug("compid = " + c);
				m.compid = c;
				state = MAV_states.MAVLINK_PARSE_STATE_GOT_COMPID;
				shifter = 0;
				break;

			case MAVLINK_PARSE_STATE_GOT_COMPID:
				m.buffer[m.index++] = (byte) c;

				if (m.version == MAVLinkPacket.MAVLINK_2) {
					m.msgid += c << (shifter*8);
					shifter++;
					if (shifter <= 2)
						break;
				}
				else {
					m.msgid = c;
				}

				shifter = 0;
				if (m.len == 0) {
					state = MAV_states.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
					debug("message id = NO MSG ID (len 0)");
				} else {
					debug("message id = " + m.msgid);
					state = MAV_states.MAVLINK_PARSE_STATE_GOT_MSGID;
				}
				break;

			case MAVLINK_PARSE_STATE_GOT_MSGID:
				m.buffer[m.index++] = (byte) c;
				debug("Building payload " + c);
				m.payload.add((byte) c);
				if (m.payloadIsFilled()) {
					state = MAV_states.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
				}
				break;

			case MAVLINK_PARSE_STATE_GOT_PAYLOAD:
				m.generateCRC();
				m.generateCRC2(m.msgid, m.buffer);

				int lsb;
				if (m.version == MAVLINK_2) {
					lsb = m.crc2.getLSB();
					debug("calc crc2 lsb " + m.crc2.getLSB());
					debug("calc crc2 msb " + m.crc2.getMSB());
				}
				else {
					lsb = m.crc.getLSB();
					debug("calc crc lsb " + m.crc.getLSB());
					debug("calc crc msb " + m.crc.getMSB());
					debug("Building crc " + c);
				}
				// Check first checksum byte
				if (c != lsb) {
					msg_received = false;
					state = MAV_states.MAVLINK_PARSE_STATE_IDLE;
					if (c == MAVLinkPacket.MAVLINK_STX) {
						state = MAV_states.MAVLINK_PARSE_STATE_GOT_STX;
						m.crc.start_checksum();
					}
					mavlinkStats.advancedCrcError();
					System.out.println("Checksum Failed - LSB, msgid = " + m.msgid + " protocol = " + (m.version == MAVLINK_1 ? "m1" : "m2"));
					LOGGER.error("Checksum Failed - LSB !!!");

//				for (int i = 0 ; i < index ; i++)
//					System.err.print(debugBuffer[i] + ",");
//				System.err.println();
				} else {
					state = MAV_states.MAVLINK_PARSE_STATE_GOT_CRC1;
				}
				break;

			case MAVLINK_PARSE_STATE_GOT_CRC1:
				// Check second checksum byte
				int msb;
				if (m.version == MAVLINK_2) {
					msb = m.crc2.getMSB();
					debug("calc crc2 lsb " + m.crc2.getLSB());
					debug("calc crc2 msb " + m.crc2.getMSB());
				}
				else {
					msb = m.crc.getMSB();
					debug("calc crc lsb " + m.crc.getLSB());
					debug("calc crc msb " + m.crc.getMSB());
					debug("Building crc " + c);
				}
				debug("calc crc lsb " + m.crc.getMSB());
				debug("Building crc " + c);
				if (c != msb) {
					msg_received = false;
					state = MAV_states.MAVLINK_PARSE_STATE_IDLE;
					if (c == MAVLinkPacket.MAVLINK_STX) {
						state = MAV_states.MAVLINK_PARSE_STATE_GOT_STX;
						m.crc.start_checksum();
					}
					mavlinkStats.advancedCrcError();
					System.out.println("Checksum Failed - MSB");
					LOGGER.error("Checksum Failed - MSB");

//				for (int i = 0 ; i < index ; i++)
//					System.err.print(debugBuffer[i] + ",");
//				System.err.println();
				}
				else { // Successfully received the message
					mavlinkStats.newPacket(m);
					msg_received = true;
					state = MAV_states.MAVLINK_PARSE_STATE_IDLE;

//				for (int i = 0 ; i < index ; i++)
//					System.err.print(debugBuffer[i] + ",");
//				System.err.println();
				}

				break;

		}

		if (msg_received) {
//			index = 0;
			return m;
		} else {
			return null;
		}
	}

	public MAVLinkStats getMavlinkStats() {
		return mavlinkStats;
	}
}