package ru.revoltech.dnsresolver.dns;

public interface RequestBuilder {
    byte[] build(ResolverRequest request);
}
