package ru.job4j.inout;

public class ArgZip {

    private final String[] args;

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 3) {
            throw new IllegalArgumentException("No program arguments: Zip folder, excluding files extension or output name");
        }
        return true;
    }

    public String directory() {
        return args[0];
    }

    public String exclude() {
        return args[1];
    }

    public String output() {
        return args[2];
    }
}
