package ru.job4j.inout;

import java.util.LinkedList;


public class Shell {

    private LinkedList<String> curDir = new LinkedList<>();

    public void cd(String path) {
        if (path.startsWith("/")) {
            curDir.clear();
        }
        if (path.contains("/") && path.length() > 1) {
            String[] tempDir = path.split("/");
            for (String s : tempDir) {
                if (!s.equals("") && !s.equals("..")) {
                    curDir.add(s);
                }
            }
        } else if (!path.equals("..") && !path.equals("/")) {
            curDir.add(path);
        }
        if (path.contains("..") && curDir.size() != 0) {
            curDir.removeLast();
        }
    }

    public String pwd() {
        StringBuilder result = new StringBuilder();
        if (curDir.size() == 0) {
            result.append("/");
        } else {
            for (String dir : curDir) {
                result.append("/");
                result.append(dir);
            }
        }
        return result.toString();
    }
}
