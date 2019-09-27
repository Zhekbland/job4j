package ru.job4j.servlets.http.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.http.persistent.LocationDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet CountryController give country from LocationDB.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 5.
 * @since 27.09.2019.
 */
public class CountryController extends HttpServlet {

    private final LocationDB locationDB = LocationDB.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        writer.println(mapper.writeValueAsString(this.locationDB.getCountries().values()));
        writer.close();
    }
}
