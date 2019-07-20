package ru.job4j.socket.bot;

import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class ClientTest is testing class Client.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.07.2019.
 */
public class ClientTest {

    private static final String LN = System.getProperty("line.separator");

    private void testClient(String input, String consoleIn) throws IOException {
        Socket socket = mock(Socket.class);
        Client client = new Client(socket);
        ByteArrayInputStream consoleInput = new ByteArrayInputStream(consoleIn.getBytes());
        System.setIn(consoleInput);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
                input.getBytes()
        );
        when(socket.getOutputStream()).thenReturn(out);
        when(socket.getInputStream()).thenReturn(in);
        client.start();
        assertThat(out.toString(), is(consoleIn));
    }

    @Test
    public void whenWeWriteExitAndGetOut() throws IOException {
        this.testClient(LN, String.format("exit%s", LN));
    }

    @Test
    public void whenWeWriteHelloAndExitAndGetOut() throws IOException {
        this.testClient(String.format("Hello, my dear friend, I'm the Oracle.%s%s", LN, LN),
                String.format("Hello Oracle%sexit%s", LN, LN)
        );
    }

    @Test
    public void whenWeWriteUnsupportedAndExitAndGetOut() throws IOException {
        this.testClient(String.format("What did you ask?!%s%s", LN, LN),
                String.format("Hey%sexit%s", LN, LN)
        );
    }
}