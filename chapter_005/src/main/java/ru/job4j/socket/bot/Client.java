package ru.job4j.socket.bot;

import java.io.*;
import java.net.*;

/**
 * Class Client creates Client for message.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.07.2019.
 */
public class Client {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    /**
     * Method connected to server and begins chat.
     */
    public void start() {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true)) {
            String request;
            do {
                request = console.readLine();
                out.println(request);
                if (!request.equals("exit")) {
                    System.out.println("Server sent: " + in.readLine());
                }
            } while (!request.equals("exit"));

        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }
}
