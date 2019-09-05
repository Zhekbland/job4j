<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<table border="1" cellpadding="5">
    <tr>
        <td valign="middle" align="center">
            <form action="<%=request.getContextPath()%>/edit" method="post">
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                Name: <input type="text" name="name" value="<c:out value="${user.name}"/>"/>
                Login: <input type="text" name="login" value="<c:out value="${user.login}"/>"/>
                Email: <input type="text" name="email" value="<c:out value="${user.email}"/>"/>
                &nbsp;
                <input type="submit" value="Save">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
