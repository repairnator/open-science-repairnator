package com.dronegcs.mavlink.is.connection;

public interface MirrorHandler {

    void take(byte[] readOnlyBuffer, int bufferSize);

}
