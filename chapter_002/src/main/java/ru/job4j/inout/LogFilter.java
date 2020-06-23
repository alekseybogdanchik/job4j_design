package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
    }
}
