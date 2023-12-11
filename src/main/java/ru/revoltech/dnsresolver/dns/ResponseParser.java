package ru.revoltech.dnsresolver.dns;

import java.io.IOException;

public interface ResponseParser {
    ResolverResponse parse(byte[] buffer) throws IOException;
}
