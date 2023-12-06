package ru.revoltech.dnsresolver.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class CommandLineOptionsFactory {
    Options buildOptions() {
        Option nameServerOption = Option.builder("ns").hasArg().type(String.class).build();
        Option hostOption = Option.builder("h").hasArg().type(String.class).build();

        Options options = new Options();
        options.addOption(nameServerOption);
        options.addOption(hostOption);

        return options;
    }
}
