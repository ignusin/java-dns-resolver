package ru.revoltech.dnsresolver;

import ru.revoltech.dnsresolver.cli.CommandLineArgs;
import ru.revoltech.dnsresolver.cli.CommandLineArgsReader;
import ru.revoltech.dnsresolver.cli.CommandLineHelpFormatter;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<CommandLineArgs> parsedArgsOptional = new CommandLineArgsReader().read(args);
        if (parsedArgsOptional.isEmpty()) {
            System.out.println(new CommandLineHelpFormatter().formatHelp());
            return;
        }

        CommandLineArgs parsedArgs = parsedArgsOptional.get();


    }
}