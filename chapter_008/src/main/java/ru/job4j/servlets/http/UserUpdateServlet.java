package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserUpdateServlet show form for update user's information.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 28.08.2019.
 */
public class UserUpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        User user = this.validateService.findById(Integer.parseInt(id));

        StringBuilder sb = new StringBuilder("<table border='1' cellpadding='5'>");
        sb.append("<tr>");
        sb.append("<td valign='middle'  align='center'>");
        sb.append("<form action='" + req.getContextPath() + "/list' method='post'>");
        sb.append("<input type='hidden' name='action' value='update'/>");
        sb.append("<input type='hidden' name='id' value='" + id + "'/>");
        sb.append(" Name: <input type='text' name='name' value='" + user.getName() + "'/>");
        sb.append(" Login: <input type='text' name='login' value='" + user.getLogin() + "'/>");
        sb.append(" Email: <input type='text' name='email' value='" + user.getEmail() + "'/>");
        sb.append("&nbsp;");
        sb.append("<input type='submit' value='Save'>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</form>");
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
        doGet(req, resp);
        writer.close();
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }
}