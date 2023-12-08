package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ResolverRequest {
    private final String nameServerHost;
    private final int nameServerPort;
    private final String resolveHost;
}
