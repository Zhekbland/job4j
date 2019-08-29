package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserCreateServlet show form for create user.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 28.08.2019.
 */
public class UserCreateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        StringBuilder sb = new StringBuilder("<table border='1' cellpadding='5'>");
        sb.append("<tr>");
        sb.append("<td valign='middle'  align='center'>");
        sb.append("<form action='").append(req.getContextPath()).append("/list' method='post'>");
        sb.append("<input type='hidden' name='action' value='add'/>");
        sb.append("<input type='text' name='name' value='Write your name'/>");
        sb.append("<input type='text' name='login' value='Create login'/>");
        sb.append("<input type='text' name='email' value='Write your email'/>");
        sb.append("<input type='submit' value='Create'>");
        sb.append("</form>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");

        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = getStringParameters(req, "action");
        String id = getStringParameters(req, "id");
        String name = getStringParameters(req, "name");
        String login = getStringParameters(req, "login");
        String email = getStringParameters(req, "email");

        String apply = dispatchFunction.actionChecker(action,
                new User(Integer.parseInt(id), name, login, email));
        PrintWriter writer = resp.getWriter();
        writer.println(apply);
        writer.close();
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }
}