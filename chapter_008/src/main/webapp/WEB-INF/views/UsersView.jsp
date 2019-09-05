<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1" cellpadding="5">
    <c:forEach items="${users}" var="user">
        <tr>
            <td valign="middle " align="center">
                <c:out value="${user.toString()}"/>
            </td>
            <td valign="middle " align="center">
                <form action="${pageContext.servletContext.contextPath}/" method="post">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
            <td valign="middle " align="center">
                <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="3" valign="middle " align="center">
            <form action="${pageContext.servletContext.contextPath}/create" method="get">
                <input type="submit" value="Create"/>
            </form>
        </td>
    </tr>
</table>
</body>
</html>