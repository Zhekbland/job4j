<%@ page import="ru.job4j.servlets.http.ValidateService" %>
<%@ page import="ru.job4j.servlets.http.User" %><%--
  Created by IntelliJ IDEA.
  User: Жека
  Date: 30.08.2019
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1" cellpadding="5">
    <%for (User user : ValidateService.getInstance().findAll()) {%>
    <tr>
        <td valign="middle " align="center">
            <%=user.toString()%>
        </td>
        <td valign="middle " align="center">
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
        <td valign="middle " align="center">
            <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Update"/>
            </form>
        </td>
    </tr>
    <%}%>
    <tr>
        <td colspan="3" valign="middle " align="center">
            <form action="<%=request.getContextPath()%>/create.jsp" method="get">
                <input type="submit" value="Create"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
