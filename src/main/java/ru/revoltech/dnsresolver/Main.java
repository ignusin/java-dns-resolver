package ru.revoltech.dnsresolver;

import ru.revoltech.dnsresolver.cli.CommandLineArgs;
import ru.revoltech.dnsresolver.cli.CommandLineArgsReader;
import ru.revoltech.dnsresolver.cli.CommandLineHelpFormatter;
import ru.revoltech.dnsresolver.dns.*;
import ru.revoltech.dnsresolver.dns.channel.NameServerUDPChannelConnector;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        new Main(
                new CommandLineArgsReader(),
                new CommandLineHelpFormatter(),
                new Resolver(
                        new NameServerUDPChannelConnector(),
                        new RequestBuilderImpl(),
                        new ResponseParserImpl()
                )
        ).run(args);
    }

    // TODO: move constant out of here.
    private static final int NS_PORT = 53;

    // TODO: move to IoC.
    private final CommandLineArgsReader commandLineArgsReader;
    private final CommandLineHelpFormatter commandLineHelpFormatter;
    private final Resolver resolver;

    public Main(
            CommandLineArgsReader commandLineArgsReader,
            CommandLineHelpFormatter commandLineHelpFormatter,
            Resolver resolver) {
        this.commandLineArgsReader = commandLineArgsReader;
        this.commandLineHelpFormatter = commandLineHelpFormatter;
        this.resolver = resolver;
    }

    public void run(String[] args) {
        Optional<CommandLineArgs> parsedArgsOptional = commandLineArgsReader.read(args);
        if (parsedArgsOptional.isEmpty()) {
            System.out.println(commandLineHelpFormatter.formatHelp());
            return;
        }

        CommandLineArgs parsedArgs = parsedArgsOptional.get();
        resolver.resolve(new ResolverRequest(parsedArgs.getNameServer(), NS_PORT, parsedArgs.getHostname()));
    }
}