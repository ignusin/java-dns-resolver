package ru.revoltech.dnsresolver.dns;

import ru.revoltech.dnsresolver.io.LittleEndianInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public class ResponseParserImpl implements ResponseParser {
    @Override
    public Optional<ResolverResponse> parse(byte[] buffer) {
        try {
            return parseExceptional(buffer);
        } catch (IOException ex) {
            return Optional.empty();
        }
    }

    private Optional<ResolverResponse> parseExceptional(byte[] buffer) throws IOException {
        try (ByteArrayInputStream baseStream = new ByteArrayInputStream(buffer);
             LittleEndianInputStream input = new LittleEndianInputStream(baseStream)) {

            Header header = readHeader(input);
        }
    }

    private static class Header {

    }
}
