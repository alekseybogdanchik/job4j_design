package ru.job4j.inout;


public class ArgFind {

    private final String[] args;

    public ArgFind(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 7) {
            throw new IllegalArgumentException("Enter correct arguments. "
                + "Usage java -jar find.jar "
                + "-d DIRECTORY -n NAME/MASK/RegEx -f(for full NAME match)/-m(for MASK)/-r(for RegEx) -o OUTPUT_FILE");
        }
        return true;
    }

    public String directory() {
        return args[1];
    }

    public String getKey() {
        return args[3];
    }

    public String condition() {
        return args[4];
    }

    public String output() {
        return args[6];
    }
}
