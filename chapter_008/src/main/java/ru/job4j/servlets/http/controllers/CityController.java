package ru.job4j.servlets.http.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.servlets.http.persistent.LocationDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet CityController give country from LocationDB.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 5.
 * @since 27.09.2019.
 */
public class CityController extends HttpServlet {

    private final LocationDB locationDB = LocationDB.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BufferedReader reader = req.getReader();
        String countryName = mapper.readValue(reader.readLine(), String.class);
        PrintWriter writer = resp.getWriter();
        writer.println(mapper.writeValueAsString(this.locationDB.getCities(countryName).values()));
        writer.close();
    }
}