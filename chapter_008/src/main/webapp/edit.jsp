<%@ page import="ru.job4j.servlets.http.User" %>
<%@ page import="ru.job4j.servlets.http.ValidateService" %><%--
  Created by IntelliJ IDEA.
  User: Жека
  Date: 30.08.2019
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<% String id = request.getParameter("id");
    User user = ValidateService.getInstance().findById(Integer.parseInt(id));
%>
<table border="1" cellpadding="5">
    <tr>
        <td valign="middle" align="center">
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                Name: <input type="text" name="name" value="<%=user.getName()%>"/>
                Login: <input type="text" name="login" value="<%=user.getLogin()%>"/>
                Email: <input type="text" name="email" value="<%=user.getEmail()%>"/>
                &nbsp;
                <input type="submit" value="Save">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
