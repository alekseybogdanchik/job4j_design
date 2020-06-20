package ru.job4j.inout;

import java.io.FileOutputStream;


public class MultiplyFile {
    public static void main(String[] args) {
        int size = 10;
        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("multiply.txt", true)) {
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    out.write(System.lineSeparator().getBytes());
                }
                for (int j = 0; j < size; j++) {
                    int tmp = (i + 1) * (j + 1);
                    String rsl = String.valueOf(tmp);
                    rsl = rsl + " ";
                        out.write(rsl.getBytes());
                    }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
