package ru.job4j.servlets.http.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.servlets.http.logic.ValidateService;
import ru.job4j.servlets.http.logic.ValidateStub;
import ru.job4j.servlets.http.persistent.Role;
import ru.job4j.servlets.http.persistent.User;
import ru.job4j.servlets.http.persistent.Validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class UserUpdateControllerTest is testing.
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 11.09.2019.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserUpdateControllerTest {

    @Test
    public void whenWeUpdateUser() throws IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("name")).thenReturn("Zheka");
        when(request.getParameter("login")).thenReturn("zhekbland");
        when(request.getParameter("email")).thenReturn("zheka@gmail.com");
        when(request.getParameter("password")).thenReturn("123123123");
        when(request.getParameter("role")).thenReturn(Role.ADMIN.toString());
        new UserUpdateController().doPost(request, response);
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("0");
        when(request.getParameter("name")).thenReturn("Evgeniy");
        when(request.getParameter("login")).thenReturn("Zhekbland");
        when(request.getParameter("email")).thenReturn("zhek@gmail.com");
        when(request.getParameter("password")).thenReturn("321321321");
        when(request.getParameter("role")).thenReturn(Role.USER.toString());
        new UserUpdateController().doPost(request, response);
        User result = validate.findById(0);
        assertThat(result.getName(), is("Evgeniy"));
        assertThat(result.getLogin(), is("Zhekbland"));
        assertThat(result.getEmail(), is("zhek@gmail.com"));
        assertThat(result.getPassword(), is("321321321"));
        assertThat(result.getRole(), is(Role.USER));
    }

}