package ru.job4j.servlets.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class JSONControllerTest is testing.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 20.09.2019.
 */
public class JSONControllerTest {

    @Test
    public void whenWeGetJSONParseItAndPutIntoMap() throws IOException {
        Person testPerson = new Person(1, "Zheka", "Lebron", "male", "NBA");
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader json = new BufferedReader(new StringReader(mapper.writeValueAsString(testPerson)));
        JSONController jsonController = new JSONController();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getReader()).thenReturn(json);
        jsonController.doPost(request, response);
        assertThat(jsonController.getPersons().get(1), is(testPerson));
    }
}