package ru.job4j.servlets.http.logic;

import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.persistent.Validate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Class DispatchFunction checks actions.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 29.08.2019.
 */
public class DispatchFunction {

    /**
     * Instance of singleton.
     */
    private final static DispatchFunction INSTANCE = new DispatchFunction();

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";

    /**
     * Instance of singleton.
     */
    private final Validate validateService = ValidateService.getInstance();

    private final Map<String, Function<User, String>> functions = new HashMap<>();

    private DispatchFunction() {
        init();
    }

    /**
     * ADD
     */
    private Function<User, String> add = (user -> {
        User result = validateService.add(user);
        return result != null ? "User " + user.getName() + " was create" : "Such User already exists";
    });

    /**
     * UPDATE
     */
    private Function<User, String> update = (user ->
            validateService.update(user) ? "User was update" : "User wasn't update");

    /**
     * DELETE
     */
    private Function<User, String> delete = (user ->
            validateService.delete(user) ? "User was delete" : "User wasn't delete");

    public static DispatchFunction getInstance() {
        return INSTANCE;
    }

    public void init() {
        functions.put(ADD, this.add);
        functions.put(UPDATE, this.update);
        functions.put(DELETE, this.delete);
    }

    public String actionChecker(String action, User user) {
        return this.functions.getOrDefault(action, user1 -> "Action not found!")
                .apply(user);
    }
}