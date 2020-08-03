package ru.job4j.inout;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;


public class Find {

    public static List<Path> result(Path directory, Predicate<Path> predicate) throws IOException {
        SearchFiles searcher = new SearchFiles(predicate);
        Files.walkFileTree(directory, searcher);
        return searcher.getPaths();
    }

    public static void save(List<Path> list, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (Path string : list) {
                out.write(String.valueOf(string));
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgFind arg = new ArgFind(args);
        if (arg.valid()) {
            if (arg.getMatch().equals("-f")) {
                Predicate<Path> namePredicate = p -> p.toFile().getName().equals(arg.getName());
                save(result(Paths.get(arg.directory()), namePredicate), arg.output());
            }
            if (arg.getMatch().equals("-m")) {
                Predicate<Path> maskPredicate = p -> p.toFile().getName().endsWith(arg.getName());
                save(result(Paths.get(arg.directory()), maskPredicate), arg.output());
            }
        }
    }
}
