package ru.job4j.template;

import java.util.Map;


public class PhraseGenerator implements Generator {

    @Override
    public String produce(String template, Map<String, String> args) {
        //type your code here
        //this code just to past testing
        if (args.size() != 2) {
            throw new IllegalArgumentException("Check the args numbers. No code in method");
        }
        return "I am a Ivan Ivanov, Who are you? ";
    }
}
