package ru.revoltech.dnsresolver.dns;

import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Resolver {
    public ResolverResponse resolve(ResolverRequest request) {
        try {
            return resolveExceptional(request);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private ResolverResponse resolveExceptional(ResolverRequest request) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        socket.connect(new InetSocketAddress(request.getNameServerHost(), request.getNameServerPort()));


        // socket.send();
        throw new NotImplementedException();
    }
}
