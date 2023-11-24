package dev.dmohindru.StringProcessorServer.processor;

import dev.dmohindru.StringProcessorServer.constants.CLIArgs;
import dev.dmohindru.StringProcessorServer.exceptions.InvalidAppArgumentExpection;
import dev.dmohindru.StringProcessorServer.exceptions.MissingAppArgumentException;
import org.apache.commons.cli.*;

public class CLIProcessor {
    private final CommandLineParser parser = new DefaultParser();
    private final Options options = new Options();
    public CLIProcessor() {
        options.addOption(new Option(
                CLIArgs.PORT.getShortCmd(),
                CLIArgs.PORT.getLongCmd(),
                true,
                CLIArgs.PORT.getDescription()
        ));
    }

    public Integer getServerPort(String []args) {
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(CLIArgs.PORT.getShortCmd())) {
                return Integer.valueOf(cmd.getOptionValue(CLIArgs.PORT.getShortCmd()));
            } else if (cmd.hasOption(CLIArgs.PORT.getLongCmd())) {
                return Integer.valueOf(cmd.getOptionValue(CLIArgs.PORT.getLongCmd()));
            } else {
                throw new MissingAppArgumentException("Missing port command line argument");
            }
        } catch (ParseException ex) {
            throw new InvalidAppArgumentExpection("Unable to process app arguments");
        }
    }
}
