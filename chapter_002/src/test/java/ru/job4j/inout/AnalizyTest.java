package ru.job4j.inout;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTwoDiapasons() throws IOException {
        Analizy analizy = new Analizy();
        File source = folder.newFile("log.txt");
        File target = folder.newFile("target.txt");
        String ls = System.lineSeparator();
        try (PrintWriter out = new PrintWriter(source)) {
            out.write("200 10:56:01" + ls
                    + "500 10:57:01" + ls
                    + "400 10:58:01" + ls
                    + "200 10:59:01" + ls
                    + "500 11:01:02" + ls
                    + "200 11:02:02");
        }

        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(
                rsl.contains("10:57:01;10:59:01"),
                is(true)
        );
        assertThat(
                rsl.contains("11:01:02;11:02:02"),
                is(true)
        );
    }
}
