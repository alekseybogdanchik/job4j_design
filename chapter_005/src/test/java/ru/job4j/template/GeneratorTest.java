package ru.job4j.template;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;


public class GeneratorTest {

    @Test
    public void whenValuesNumbersMatchTemplatesKeys() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> values = new HashMap<>();
        values.put("name", "Ivan Ivanov");
        values.put("subject", "you");
        String rsl = generator.produce(template, values);
        assertThat(rsl, is("I am a Ivan Ivanov, Who are you? "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesNumbersLessThanTemplatesKeys() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> values = new HashMap<>();
        values.put("name", "Ivan Ivanov");
        String rsl = generator.produce(template, values);
        assertThat(rsl, is("I am a Ivan Ivanov, Who are you? "));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValuesNumbersMoreThanTemplatesKeys() {
        Generator generator = new PhraseGenerator();
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> values = new HashMap<>();
        values.put("name", "Ivan Ivanov");
        values.put("subject", "you");
        values.put("age", "25");
        String rsl = generator.produce(template, values);
        assertThat(rsl, is("I am a Ivan Ivanov, Who are you? "));
    }
}
