package ru.revoltech.dnsresolver.dns;

import org.apache.commons.lang3.NotImplementedException;
import ru.revoltech.dnsresolver.dns.channel.NameServerChannel;
import ru.revoltech.dnsresolver.dns.channel.NameServerChannelConnector;

import java.io.IOException;

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

    public ResolverResponse resolve(ResolverRequest request) {
        try {
            return resolveExceptional(request);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private ResolverResponse resolveExceptional(ResolverRequest request) throws IOException {
        try (NameServerChannel channel = nameServerChannelConnector
                .connect(request.getNameServerHost(), request.getNameServerPort())) {

            byte[] data = requestBuilder.build(request);
            channel.send(data);

            // TODO: magic numbers.
            byte[] buffer = channel.receive();

            ResolverResponse response = responseParser.parse(buffer);
            return response;
        }
    }
}
