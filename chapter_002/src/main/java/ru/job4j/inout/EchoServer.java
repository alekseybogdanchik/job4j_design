package ru.job4j.inout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean run = true;
            while (run) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    String msg = null;
                    while (true) {
                        str = in.readLine();
                        if (str.isEmpty()) {
                            break;
                        }
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
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
    }
}
