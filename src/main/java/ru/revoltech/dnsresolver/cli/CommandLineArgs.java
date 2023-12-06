package ru.revoltech.dnsresolver.cli;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommandLineArgs {
    private final String nameServer;
    private final String hostname;
}
