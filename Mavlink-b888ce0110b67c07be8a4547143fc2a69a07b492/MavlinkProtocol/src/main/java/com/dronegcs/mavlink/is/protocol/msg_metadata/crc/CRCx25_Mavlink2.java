package com.dronegcs.mavlink.is.protocol.msg_metadata.crc;

import java.nio.charset.StandardCharsets;

public class CRCx25_Mavlink2 {
    private int crc;

    public CRCx25_Mavlink2() {
        crc = 0xffff;
    }

    public void accumulate(String str) {
        accumulate(str.getBytes(StandardCharsets.UTF_8));
    }

    public void accumulate(byte[] bytes) {
        accumulate(bytes, 0, bytes.length);
    }

    public void accumulate(byte[] bytes, int offset, int length) {
        for (int i = offset; i < length; i++) {
            accumulate(bytes[i]);
        }
    }

    public void accumulate(int b) {
        b = b ^ (crc & 0xff);
        b ^= (b << 4) & 0xff;
        b &= 0xff;
        crc = (crc >> 8) ^ (b << 8) ^ (b << 3) ^ (b >> 4);
    }

    public int get() {
        return crc & 0xffff;
    }

    public int getMSB() {
        return ((crc >> 8) & 0xff);
    }

    public int getLSB() {
        return (crc & 0xff);
    }
}