package ru.job4j.servlets;

import ru.job4j.servlets.http.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Create Echo Servlet.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 07.05.2019.
 */
public class EchoServlet extends HttpServlet {

    private List<String> users = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter writer = new PrintWriter(resp.getOutputStream());
//        StringBuilder sb = new StringBuilder("<table>");
//        for (String login : this.users) {
//            sb.append("<tr><td>" + login + "</td></tr>");
//        }
//        sb.append("</table>");
//
//        writer.append("<!DOCTYPE html>"
//                + "<html lang=\"en\">"
//                + "<head>\n"
//                + "    <meta charset=\"UTF-8\">"
//                + "    <title>Title</title>"
//                + "</head>"
//                + "<body>"
//                + "<form action='" + req.getContextPath() + "/echo' method='post'>"
//                + "Name : <input type='text' name='login'/>"
//                + "<input type='submit'>"
//                + "</form>"
//                + "</br>"
//                + sb.toString()
//                + "</body>"
//                + "</html>");
//        writer.flush();

        req.setAttribute("users", UserStorage.getInstance().getUsers());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
//        UserStorage.getInstance().add(new User(Integer.parseInt(id), name, login, email));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
