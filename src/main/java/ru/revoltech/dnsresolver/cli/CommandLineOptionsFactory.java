package ru.revoltech.dnsresolver.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

class CommandLineOptionsFactory {
    Options buildOptions() {
        Option nameServerOption = Option.builder("ns").hasArg().type(String.class).build();
        nameServerOption.setRequired(true);

        Option hostOption = Option.builder("h").hasArg().type(String.class).build();
        hostOption.setRequired(true);

        Options options = new Options();
        options.addOption(nameServerOption);
        options.addOption(hostOption);

        return options;
    }
}
