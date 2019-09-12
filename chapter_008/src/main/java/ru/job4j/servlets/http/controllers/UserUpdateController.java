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

/**
 * UserUpdateController show form for update user's information.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 4.
 * @since 28.08.2019.
 */
public class UserUpdateController extends HttpServlet {

    /**
     * Instance of singleton.
     */
    private final Validate validateService = ValidateService.getInstance();

    /**
     * Instance of singleton.
     */
    private final DispatchFunction dispatchFunction = DispatchFunction.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User editUser = validateService.findById(Integer.parseInt(id));
        HttpSession session = req.getSession();
        req.setAttribute("userRole", session.getAttribute("userRole"));
        req.setAttribute("user", editUser);
        req.getRequestDispatcher("/WEB-INF/views/UserEditView.jsp").forward(req, resp);
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

        dispatchFunction.actionChecker(action,
                new User(Integer.parseInt(id), name, login, email, password, Role.valueOf(role)));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
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