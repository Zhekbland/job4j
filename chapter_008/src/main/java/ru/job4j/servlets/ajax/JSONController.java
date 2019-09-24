package ru.job4j.servlets.ajax;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class JSONController creates AJAX model.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 20.09.2019.
 */
public class JSONController extends HttpServlet {

    private final Map<Integer, Person> persons = new ConcurrentHashMap<>();
    private volatile AtomicInteger count = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(persons.values()));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json");
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            if (reader != null) {
                sb.append(reader.readLine());
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(sb.toString(), Person.class);
        int id = count.incrementAndGet();
        person.setId(id);
        this.persons.put(id, person);
    }

    public Map<Integer, Person> getPersons() {
        return this.persons;
    }
}