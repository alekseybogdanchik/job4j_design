package ru.job4j.inout;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleChat {

    private Path answers = Paths.get("answers.txt");
    private List<String> lines = new ArrayList<>();
    private List<String> log = new ArrayList<>();
    private boolean run = true;
    private boolean toAnswer = true;

    public void check(String input) {
        if (input.equals("стоп")) {
            toAnswer = false;
        }
        if (input.equals("продолжить")) {
            toAnswer = true;
        }
        if (input.equals("закончить")) {
            toAnswer = false;
            run = false;
        }
    }

    public void readAnswer(Path path) {
        try (BufferedReader in = new BufferedReader(new FileReader(path.toFile()))) {
            in.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String s : lines) {
            System.out.println(s);
        }
    }

    public String findAnswer() {
        int index = (int) (Math.random() * lines.size());
        return lines.get(index);
    }

    public static void save(List<String> list) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("./chatlog.txt")
                ))) {
            for (String string : list) {
                out.write(string);
                out.write(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chat() {
        Scanner scanner = new Scanner(System.in);
        readAnswer(answers);
        System.out.println("Поговори хоть ты со мной:");
        while (run) {
            String userInput = scanner.nextLine();
            log.add(userInput);
            check(userInput);
            if (toAnswer) {
                String answer = findAnswer();
                System.out.println(answer);
                log.add(answer);
            }
            if (!run) {
                save(log);
            }
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        consoleChat.chat();
    }
}
