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
 * UserCreateServlet show form for create user.
 */
public class UserCreateServlet extends HttpServlet {


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
    }

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

        String apply = this.functions.getOrDefault(action, user -> "action not found")
                .apply(new User(Integer.parseInt(id), name, login, email));
        PrintWriter writer = resp.getWriter();
        writer.println(apply);
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
