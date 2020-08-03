package ru.job4j.inout;


public class ArgFind {

    private final String[] args;

    public ArgFind(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 7) {
            throw new IllegalArgumentException("Enter correct arguments. "
                + "Usage java -jar find.jar -d DIRECTORY -n NAME/MASK -m(for MASK)/-f(for full NAME match) -o OUTPUT_FILE");
        }
        return true;
    }

    public String directory() {
        return args[1];
    }

    public String getName() {
        return args[3];
    }

    public String getMatch() {
        return args[4];
    }

    public String output() {
        return args[6];
    }
}
