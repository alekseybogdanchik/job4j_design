package ru.job4j.gc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;


public class CacheTxt implements Cache {
    private Map<String, SoftReference<Container>> cache = new HashMap<>();

    @Override
    public String get(String key) {
        boolean run = true;
        String cacheStr = null;
        if (cache.containsKey(key)) {
            Container fromCache = cache.get(key).get();
            if (fromCache != null) {
                cacheStr = fromCache.getContent();
                run = false;
            }
        }
        if (run) {
            String s = load(key);
            if (s != null) {
                cacheStr = s;
            }
        }
        return cacheStr;
    }

    @Override
    public String load(String key) {
        String rsl = null;
        StringBuilder sb = new StringBuilder();
        String dir = "./chapter_004/cacheTxt/";
        String source = dir + key;
        File file = new File(source);
        System.out.println(file.getAbsolutePath());
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
                int i;
                while ((i = reader.read()) != -1) {
                    sb.append((char) i);
                }
                rsl = sb.toString();
                Container container = new Container(key, rsl);
                SoftReference<Container> cacheStr = new SoftReference<>(container);
                cache.put(key, cacheStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not exist");
        }
        return rsl;
    }
}
