<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<table border="1" cellpadding="5">
    <tr>
        <td valign="middle " align="center">
            <form action="${pageContext.servletContext.contextPath}/create" method="post">
                <input type="hidden" name="action" value="add"/>
                <input type="text" name="name" value="Write your name"/>
                <input type="text" name="login" value="Write your login"/>
                <input type="text" name="email" value="Write your email"/>
                <input type="text" name="password" value="Write your password"/>
                <select name="role">
                    <option value="ADMIN">Administrator</option>
                    <option value="USER" selected>User</option>
                </select>
                <input type="submit" value="Create"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>