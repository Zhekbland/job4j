package ru.job4j.servlets.http.controllers;

import ru.job4j.servlets.http.logic.DispatchFunction;
import ru.job4j.servlets.http.persistent.Role;
import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.logic.ValidateService;
import ru.job4j.servlets.http.persistent.Validate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * UsersController show all users in table
 * and create button for create, delete, update.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 5.
 * @since 28.08.2019.
 */
public class UsersController extends HttpServlet {

    /**
     * Instance of singleton.
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Instance of singleton.
     */
    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        User activeUser = validateService.findByLogin(login);
        session.setAttribute("userRole", activeUser.getRole());
        if (activeUser.getRole().equals(Role.ADMIN)) {
            Map<Integer, User> users = new TreeMap<>();
            validateService.findAll().forEach(user -> users.put(user.getId(), user));
            req.setAttribute("users", users.values());
        } else {
            req.setAttribute("user", activeUser);
        }
        req.setAttribute("userRole", session.getAttribute("userRole"));
        req.getRequestDispatcher("/WEB-INF/views/UsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String action = getStringParameters(req, "action");
        String id = getStringParameters(req, "id");
        String name = getStringParameters(req, "name");
        String login = getStringParameters(req, "login");
        String email = getStringParameters(req, "email");
        String password = getStringParameters(req, "password");
        String role = getStringParameters(req, "role");
        String country = getStringParameters(req, "country");
        String city = getStringParameters(req, "city");
        User user = new User(Integer.parseInt(id), name, login, email, password, Role.valueOf(role));
        user.setCountry(country);
        user.setCity(city);
        dispatchFunction.actionChecker(action, user);
        resp.sendRedirect(String.format("%s/users", req.getContextPath()));
    }

    /**
     * Fills empty fields.
     *
     * @param req  - request.
     * @param name - name of param.
     * @return result.
     */
    private String getStringParameters(HttpServletRequest req, String name) {
        String result = req.getParameter(name);
        return result != null ? result : "0";
    }
}