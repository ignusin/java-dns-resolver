package ru.revoltech.dnsresolver.dns.channel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.*;

public class NameServerUDPChannel implements NameServerChannel {
    private static final int RECV_BUFFER_LENGTH = 2048;

    private final DatagramSocket socket;

    public NameServerUDPChannel(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void send(byte[] buffer) throws IOException {
        socket.send(new DatagramPacket(buffer, buffer.length));
    }

    @Override
    public byte[] receive() throws IOException {
        byte[] buffer = new byte[RECV_BUFFER_LENGTH];

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);

        return Arrays.copyOf(buffer, packet.getLength());
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
