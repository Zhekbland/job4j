package ru.job4j.io.chat;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ChatTest {

    private static final String LN = System.getProperty("line.separator");

    private void testChat(String ask) {
        ByteArrayInputStream consoleInput = new ByteArrayInputStream(ask.getBytes());
        ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();
        System.setIn(consoleInput);
        System.setOut(new PrintStream(consoleOutput));
        Chat chat = new Chat();
        chat.start();
        assertThat(consoleOutput.toString(), is(chat.getAnswers()));
    }

    @Test
    public void whenWePrintExit() {
        testChat(
                Joiner.on(LN).join(
                        "exit",
                        ""
                ));
    }

    @Test
    public void whenWePrintSomethingAndExit() {
        testChat(
                Joiner.on(LN).join(
                        "Hi",
                        "exit",
                        ""
                ));
    }

    @Test
    public void whenWePrintStopSomethingAndExit() {
        testChat(
                Joiner.on(LN).join(
                        "stop",
                        "Hey",
                        "next",
                        "exit",
                        ""
                ));
    }
}