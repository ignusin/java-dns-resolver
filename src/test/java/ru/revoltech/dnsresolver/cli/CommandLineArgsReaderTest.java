package ru.revoltech.dnsresolver.cli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineArgsReaderTest {
    private CommandLineArgsReader reader;

    @BeforeEach
    void setUp() {
        reader = new CommandLineArgsReader();
    }

    @Test
    void testValid() {
        Optional<CommandLineArgs> argsOptional = reader
                .read(new String[] { "-ns", "8.8.8.8", "-h", "yandex.ru"});

        assertTrue(argsOptional.isPresent());
        assertEquals("8.8.8.8", argsOptional.get().getNameServer());
        assertEquals("yandex.ru", argsOptional.get().getHostname());
    }

    @Test
    void testInvalid() {
        Optional<CommandLineArgs> argsOptional = reader.read(new String[0]);
        assertTrue(argsOptional.isEmpty());
    }
}
