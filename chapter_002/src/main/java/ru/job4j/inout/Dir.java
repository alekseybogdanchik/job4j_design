package ru.job4j.inout;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            System.out.println(String.format("name : %s", subFile.getName()));
            System.out.println(String.format("size : %s", subFile.length()));
        }
    }
}
