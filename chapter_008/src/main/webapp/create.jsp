<%--
  Created by IntelliJ IDEA.
  User: Жека
  Date: 30.08.2019
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<table border="1" cellpadding="5">
    <tr>
        <td valign="middle " align="center">
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="action" value="add"/>
                <input type="text" name="name" value="Write your name"/>
                <input type="text" name="login" value="Write your login"/>
                <input type="text" name="email" value="Write your email"/>
                <input type="submit" value="Create"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
