package ru.job4j.servlets.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * UsersServlet show all users in table
 * and create button for create, delete, update.
 */
public class UsersServlet extends HttpServlet {

    private final ValidateService logic = ValidateService.getInstance();

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";

    /**
     * Map of IF-ELSE logic.
     */
    private final Map<String, Function<User, String>> functions = new HashMap<>();

    @Override
    public void init() {
        initDispatch();
        this.logic.add(new User(0, "name1", "login1", "email1"));
        this.logic.add(new User(0, "name2", "login2", "email2"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");

        StringBuilder sb = new StringBuilder("<table border='1' cellpadding='5'>");
        for (User user : this.logic.findAll()) {
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

        String apply = this.functions.getOrDefault(action, user -> "action not found")
                .apply(new User(Integer.parseInt(id), name, login, email));
        PrintWriter writer = resp.getWriter();
        writer.println(apply);
        doGet(req, resp);
        writer.close();
    }

    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }

    /**
     * Fill map of logic.
     */
    private void initDispatch() {
        functions.put(ADD, this.add);
        functions.put(UPDATE, this.update);
        functions.put(DELETE, this.delete);
    }

    /**
     * ADD
     */
    private Function<User, String> add = (user -> {
        User result = logic.add(user);
        return result != null ? "User " + user.getName() + " was create" : "Such User already exists";
    });

    /**
     * UPDATE
     */
    private Function<User, String> update = (user ->
            logic.update(user) ? "User was update" : "User wasn't update");

    /**
     * DELETE
     */
    private Function<User, String> delete = (user ->
            logic.delete(user) ? "User was delete" : "User wasn't delete");
}
