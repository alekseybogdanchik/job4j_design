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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public static Predicate<Path> condition(String condition, String key) {
        Predicate<Path> rsl = null;
        if (condition.equals("-f")) {
            rsl = p -> p.toFile().getName().equals(key);
        }
        if (condition.equals("-m")) {
            rsl = p -> p.toFile().getName().endsWith(key);
        }
        if (condition.equals("-r")) {
            rsl = path -> {
                Pattern p = Pattern.compile(key);
                Matcher matcher = p.matcher(path.toFile().getName());
                return matcher.find();
            };
        }
        return rsl;
    }

    public static void main(String[] args) throws IOException {
        ArgFind arg = new ArgFind(args);
        if (arg.valid()) {
            save(
                    result(Paths.get(arg.directory()), condition(arg.condition(), arg.getKey())),
                    arg.output()
            );
        }
    }
}
