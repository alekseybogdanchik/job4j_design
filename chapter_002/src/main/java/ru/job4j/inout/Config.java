package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(list::add);
            for (String string : list) {
                if (string.length() > 0 && !string.startsWith("/") && string.contains("=")) {
                    String[] tmp = string.split("=");
                    values.put(tmp[0], tmp[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
