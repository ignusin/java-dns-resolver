package ru.revoltech.dnsresolver.dns;

import ru.revoltech.dnsresolver.dns.channel.NameServerChannel;
import ru.revoltech.dnsresolver.dns.channel.NameServerChannelConnector;

import java.io.IOException;
import java.util.*;

public class Resolver {
    private final NameServerChannelConnector nameServerChannelConnector;
    private final RequestBuilder requestBuilder;
    private final ResponseParser responseParser;

    public Resolver(
            NameServerChannelConnector nameServerChannelConnector,
            RequestBuilder requestBuilder,
            ResponseParser responseParser) {
        this.nameServerChannelConnector = nameServerChannelConnector;
        this.requestBuilder = requestBuilder;
        this.responseParser = responseParser;
    }

    public Optional<ResolverResponse> resolve(ResolverRequest request) {
        try {
            return resolveExceptional(request);
        } catch (IOException ex) {
            return Optional.empty();
        }
    }

    private Optional<ResolverResponse> resolveExceptional(ResolverRequest request) throws IOException {
        try (NameServerChannel channel = nameServerChannelConnector
                .connect(request.getNameServerHost(), request.getNameServerPort())) {

            byte[] data = requestBuilder.build(request);
            channel.send(data);

            byte[] buffer = channel.receive();

            Optional<ResolverResponse> response = responseParser.parse(buffer);
            return response;
        }
    }
}
