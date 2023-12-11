package ru.revoltech.dnsresolver.dns.channel;

import java.io.IOException;

public interface NameServerChannelConnector {
    NameServerChannel connect(String host, int port) throws IOException;
}
