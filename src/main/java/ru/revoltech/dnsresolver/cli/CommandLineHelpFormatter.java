package ru.revoltech.dnsresolver.cli;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CommandLineHelpFormatter {
    public String formatHelp() {
        Options options = new CommandLineOptionsFactory().buildOptions();

        HelpFormatter helpFormatter = new HelpFormatter();
        try (StringWriter stringWriter = new StringWriter()) {
            PrintWriter printWriter = new PrintWriter(stringWriter);
            helpFormatter.printHelp(
                    printWriter,
                    helpFormatter.getWidth(),
                    "dnsresolver -ns <name-server> -h <hostname>",
                    null,
                    options,
                    helpFormatter.getLeftPadding(),
                    helpFormatter.getDescPadding(),
                    null);

            return stringWriter.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
