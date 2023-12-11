package ru.revoltech.dnsresolver.dns.channel;

import java.io.Closeable;
import java.io.IOException;

public interface NameServerChannel extends Closeable {
    void send(byte[] data) throws IOException;
    byte[] receive() throws IOException;
}
