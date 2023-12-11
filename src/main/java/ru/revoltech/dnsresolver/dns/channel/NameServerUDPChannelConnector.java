package ru.revoltech.dnsresolver.dns.channel;

import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class NameServerUDPChannelConnector implements NameServerChannelConnector {
    @Override
    public NameServerChannel connect(String host, int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.connect(new InetSocketAddress(host, port));

        return new NameServerUDPChannel(socket);
    }
}
