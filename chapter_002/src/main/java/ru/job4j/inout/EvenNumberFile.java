package ru.job4j.inout;

import java.io.FileInputStream;


public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (int i = 0; i < lines.length; i++) {
                int tmp = Integer.parseInt(lines[i]);
                boolean rsl = (tmp % 2 == 0);
                System.out.println(rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
