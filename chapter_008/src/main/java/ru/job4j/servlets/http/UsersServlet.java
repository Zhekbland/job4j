package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UsersServlet show all users in table
 * and create button for create, delete, update.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 2.
 * @since 28.08.2019.
 */
public class UsersServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    public void init() {
        this.validateService.add(new User(0, "name1", "login1", "email1"));
        this.validateService.add(new User(0, "name2", "login2", "email2"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        StringBuilder sb = new StringBuilder("<table border='1' cellpadding='5'>");
        for (User user : this.validateService.findAll()) {
            sb.append("<tr>");
            sb.append("<td valign='middle'  align='center'>");
            sb.append(user.toString());
            sb.append("</td>");
            sb.append("<td valign='middle'  align='center'>");
            sb.append("<form action='").append(req.getContextPath()).append("/list' method='post'>");
            sb.append("<input type='hidden' name='id' value='").append(user.getId()).append("'/>");
            sb.append("<input type='hidden' name='action' value='delete'/>");
            sb.append("<input type='submit' value='Delete'>");
            sb.append("</form>");
            sb.append("</td>");
            sb.append("<td valign='middle'  align='center'>");
            sb.append("<form action='").append(req.getContextPath()).append("/edit' method='get'>");
            sb.append("<input type='hidden' name='id' value='").append(user.getId()).append("'/>");
            sb.append("<input type='submit' value='Update'>");
            sb.append("</form>");
            sb.append("</td>");
            sb.append("</tr>");
        }
        sb.append("<tr>");
        sb.append("<td colspan='3' valign='middle'  align='center'>");
        sb.append("<form action='").append(req.getContextPath()).append("/create' method='get'>");
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
        doGet(req, resp);
        writer.close();
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }
}