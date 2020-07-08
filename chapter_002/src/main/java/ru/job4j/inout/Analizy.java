package ru.job4j.inout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Analizy {
    private void addTime(String string, List<String> list) {
        String[] tmp = string.split(" ");
        list.add(tmp[1]);
    }

    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        List<String> diapason = new ArrayList<>();
        boolean available = true;
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(list::add);
            for (String string : list) {
                if (!available && (string.startsWith("200") || string.startsWith("300"))) {
                    addTime(string, diapason);
                    available = true;
                }
                if (available && (string.startsWith("400") || string.startsWith("500"))) {
                    addTime(string, diapason);
                    available = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        targetWrite(diapason, target);
    }

    private void targetWrite(List<String> listToWrite, String target) {
        int i = 0;
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (String time : listToWrite) {
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
