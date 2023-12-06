package ru.revoltech.dnsresolver.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLineHelpFormatterTest {
    private CommandLineHelpFormatter helpFormatter;

    @BeforeEach
    void setUp() {
        helpFormatter = new CommandLineHelpFormatter();
    }

    @Test
    void testFormat() {
        String expectedHelpText =
                "usage: dnsresolver -ns <name-server> -h <hostname>\n" +
                " -h <arg>\n" +
                " -ns <arg>\n";
        String actualHelpText = helpFormatter.formatHelp();
        Assertions.assertEquals(expectedHelpText, actualHelpText);
    }
}
