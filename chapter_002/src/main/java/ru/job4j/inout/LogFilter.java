package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            in.lines().forEach(lines::add);
            for (String line : lines) {
                if (line.contains("404")) {
                    String[] tmp = line.split(" ");
                    if (tmp.length >= 2 && tmp[tmp.length - 2].equals("404")) {
                    rsl.add(line);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> list, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String string : list) {
                out.write(string);
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
        for (String str : log) {
            System.out.println(str);
        }
    }
}
