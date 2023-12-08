package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class QName {
    private final List<String> sections;

    public static QName fromHostname(String hostname) {
        List<String> sections = Arrays.asList(hostname.split("\\."));
        return new QName(sections);
    }
}
