package dev.dmohindru.StringProcessorServer.constants;


public enum CLIArgs {
    PORT("p", "port", "Port on which server runs");
    private final String shortCmd, longCmd, description;
    CLIArgs(String shortCmd, String longCmd, String description) {
        this.shortCmd = shortCmd;
        this.longCmd = longCmd;
        this.description = description;
    }

    public String getShortCmd() {
        return shortCmd;
    }

    public String getLongCmd() {
        return longCmd;
    }

    public String getDescription() {
        return description;
    }
}
