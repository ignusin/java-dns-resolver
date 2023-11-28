package ru.revoltech.dnsresolver.dns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum QType {
    A(1);

    private final int value;
}
