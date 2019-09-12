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
                Password: <input type="text" name="password" value="<c:out value="${user.password}"/>"/>
                <c:if test="${userRole == 'ADMIN'}">
                    Role: <select name="role">
                    <c:if test="${user.role == 'ADMIN'}">
                        <option selected value="ADMIN">Administrator</option>
                        <option value="USER">User</option>
                    </c:if>
                    <c:if test="${user.role == 'USER'}">
                        <option value="ADMIN">Administrator</option>
                        <option selected value="USER">User</option>
                    </c:if>
                </select>
                </c:if>
                <c:if test="${userRole == 'USER'}">
                    Role: <select name="role" disabled>
                    <option selected value="USER">User</option>
                    <input type="hidden" name="role" value="USER"/>
                </select>
                </c:if>
                &nbsp;
                <input type="submit" value="Save">
            </form>
        </td>
    </tr>
</table>
</body>
</html>
