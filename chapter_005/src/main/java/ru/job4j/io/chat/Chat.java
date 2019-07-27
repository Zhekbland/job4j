package ru.job4j.io.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class Chat creates simple Bot chat with Log.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 27.07.2019.
 */
public class Chat {

    private String question;
    Scanner consoleInput = new Scanner(System.in);
    private static final String LN = System.getProperty("line.separator");
    private ByteArrayOutputStream outOfAnswers = new ByteArrayOutputStream();

    /**
     * Method start creates chat's logic.
     */
    public void start() {
        do {
            writeQuestion();
            if (question.toUpperCase().trim().equals("STOP")) {
                while (!question.toUpperCase().trim().equals("NEXT")) {
                    writeQuestion();
                }
            }
            System.out.println(answer());
        } while (!(question.trim().toUpperCase()).equals("EXIT"));
    }


    /**
     * Take answer from file and put into List, then take random file from list. This is an answer.
     *
     * @return answer.
     */
    public String answer() {
        String answer = null;
        List<String> text = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ChatText.txt"))) {
            String str;
            while ((str = reader.readLine()) != null) {
                text.add(str);
            }
            answer = text.get((int) (Math.random() * (text.size())));
            this.outOfAnswers.write((answer + LN).getBytes());
        } catch (IOException e) {
            System.out.println("IO " + e);
        }
        return answer;
    }

    /**
     * Method waits input and writes input as answer.
     */
    public void writeQuestion() {
        do {
            question = consoleInput.nextLine();
        } while (question == null);
    }

    /**
     * Method return String of answer from console.
     *
     * @return String of answers
     */
    public String getAnswers() {
        return this.outOfAnswers.toString();
    }
}