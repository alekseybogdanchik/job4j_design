package ru.job4j.inout;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class AnalizyTest {
    @Test
    public void whenTwoDiapasons() {
        Analizy analizy = new Analizy();
        String source = "./data/log.txt";
        String target = "./data/unavailable.txt";
        analizy.unavailable(source, target);
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
