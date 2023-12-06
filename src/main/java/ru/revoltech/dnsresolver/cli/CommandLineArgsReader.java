package ru.revoltech.dnsresolver.cli;

import org.apache.commons.cli.*;

import java.util.Optional;

public class CommandLineArgsReader {
    public Optional<CommandLineArgs> read(String[] args) {
        Options options = new CommandLineOptionsFactory().buildOptions();
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmdLine = parser.parse(options, args);
            String nameServer = (String)cmdLine.getParsedOptionValue("ns");
            String host = (String)cmdLine.getParsedOptionValue("h");

            return Optional.of(new CommandLineArgs(nameServer, host));
        } catch (ParseException ex) {
            return Optional.empty();
        }
    }
}
