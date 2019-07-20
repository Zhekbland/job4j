package ru.job4j.socket.bot;

import java.io.*;
import java.net.*;

/**
 * Class Server creates Server for message.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.07.2019.
 */
public class Server {

    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method start creates server for clients. And answers on client's questions.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String ask;
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                System.out.println(ask);
                if ("Hello Oracle".equals(ask)) {
                    out.println("Hello, my dear friend, I'm the Oracle.");
                    out.println();
                } else if (!ask.equals("exit")) {
                    out.println("What did you ask?!");
                    out.println();
                }
            } while (!ask.equals("exit"));
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
