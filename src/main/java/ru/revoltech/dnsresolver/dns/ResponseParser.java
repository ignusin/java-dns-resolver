package ru.revoltech.dnsresolver.dns;

import java.io.IOException;
import java.util.*;

public interface ResponseParser {
    Optional<ResolverResponse> parse(byte[] buffer);
}
