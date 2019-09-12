<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
<table border="1" cellpadding="5">
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <input type="hidden" name="action" value="delete"/>
        <tr>
            <td valign="middle " align="center">
                <input type="text" name="login" value=""/>
            </td>
        </tr>
        <tr>
            <td valign="middle " align="center">
                <input type="password" name="password" value=""/>
            </td>
        </tr>
        <tr>
            <td valign="middle " align="center">
                <input type="submit" value="signIn"/>
            </td>
        </tr>
    </form>
</table>
</body>
</html>
