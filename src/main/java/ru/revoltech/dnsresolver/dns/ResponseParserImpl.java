package ru.revoltech.dnsresolver.dns;

import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;

public class ResponseParserImpl implements ResponseParser {
    @Override
    public ResolverResponse parse(byte[] buffer) throws IOException {
        throw new NotImplementedException();
    }
}
