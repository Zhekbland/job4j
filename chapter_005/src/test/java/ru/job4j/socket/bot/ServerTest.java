package ru.job4j.socket.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class ServerTest is testing class Server.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.07.2019.
 */
public class ServerTest {

    private static final String LN = System.getProperty("line.separator");

    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(
                input.getBytes()
        );
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenWeWriteExitAndGetOut() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenWeWriteHelloAndGetMessageWithGuava() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "Hello Oracle",
                        "exit"
                ),
                Joiner.on(LN).join(
                        "Hello, my dear friend, I'm the Oracle.",
                        "",
                        ""
                )

        );
    }

    @Test
    public void whenWeWriteSomethingAndGetMessageWithGuava() throws IOException {
        this.testServer(
                Joiner.on(LN).join(
                        "Unsupported ask",
                        "exit"
                ),
                Joiner.on(LN).join(
                        "What did you ask?!",
                        "",
                        ""
                )

        );
    }
}