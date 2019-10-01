package ru.job4j.servlets.http.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @version 5.
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
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(req.getReader().readLine(), User.class);
        dispatchFunction.actionChecker("update", user);
    }
}