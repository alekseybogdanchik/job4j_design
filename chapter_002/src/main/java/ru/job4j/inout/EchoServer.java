package ru.job4j.inout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean run = true;
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String msg = null;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("/?msg=")) {
                            msg = str.substring((str.indexOf("=") + 1), str.lastIndexOf(" "));
                            if (msg.equals("Exit")) {
                                run = false;
                            }
                        }
                    }
                    if (msg.equals("Hello")) {
                        out.write("HTTP/1.1 200 OK\r\n".getBytes());
                        out.write("Hello!!!\r\n".getBytes());
                    } else if (run) {
                        out.write("HTTP/1.1 200 OK\r\n".getBytes());
                        out.write((msg + "\r\n").getBytes());
                    }
                }
            }
        }
    }
}
