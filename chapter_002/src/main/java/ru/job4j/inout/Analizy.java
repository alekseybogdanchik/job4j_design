package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Analizy {
    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        List<String> diapason = new ArrayList<>();
        boolean available = true;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(list::add);
            for (String string : list) {
                if ((string.startsWith("200") || string.startsWith("300")) && !available) {
                    String[] tmp = string.split(" ");
                    diapason.add(tmp[1]);
                    available = true;
                }
                if ((string.startsWith("400") || string.startsWith("500")) && available) {
                    String[] tmp = string.split(" ");
                    diapason.add(tmp[1]);
                    available = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = 0;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String time : diapason) {
                out.write(time);
                i++;
                if (i % 2 == 0) {
                    out.write(System.lineSeparator());
                } else {
                    out.write(";");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
