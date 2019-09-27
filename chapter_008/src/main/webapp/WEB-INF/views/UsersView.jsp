<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <style>
        body {
            font-family: 'Roboto', -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
        }
    </style>
</head>
<body>
<c:if test="${userRole == 'ADMIN'}">
    <div class="container">
        <div class="row d-flex justify-content-end mt-4">
            <form action="${pageContext.servletContext.contextPath}/signout" method="post">
                <button type="submit" class="btn btn-secondary">SignOut</button>
            </form>
        </div>
    </div>
    <div class="container my-4">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Role</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Email</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Update</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>
                        <c:out value="${user.role}"/>
                    </td>
                    <td>
                        <c:out value="${user.name}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.email}"/>
                    </td>
                    <td>
                        <c:out value="${user.country}"/>
                    </td>
                    <td>
                        <c:out value="${user.city}"/>
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                            <button type="submit" class="btn btn-secondary">Update</button>
                        </form>
                    </td>
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/users" method="post">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                            <input type="hidden" name="role" value="<c:out value="${user.role}"/>"/>
                            <c:if test="${user.role == 'ADMIN'}">
                                <button disabled type="submit" class="btn btn-secondary">Delete</button>
                            </c:if>
                            <c:if test="${user.role == 'USER'}">
                                <button type="submit" class="btn btn-secondary">Delete</button>
                            </c:if>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="container">
        <div class="row d-flex justify-content-center">
            <form action="${pageContext.servletContext.contextPath}/create" method="get">
                <button type="submit" class="btn btn-secondary">Create</button>
            </form>
        </div>
    </div>
</c:if>

<c:if test="${userRole == 'USER'}">
    <div class="container">
        <div class="row d-flex justify-content-end mt-4">
            <form action="${pageContext.servletContext.contextPath}/signout" method="post">
                <button type="submit" class="btn btn-secondary">SignOut</button>
            </form>
        </div>
    </div>
    <div class="container my-4">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Role</th>
                <th scope="col">Name</th>
                <th scope="col">Login</th>
                <th scope="col">Email</th>
                <th scope="col">Country</th>
                <th scope="col">City</th>
                <th scope="col">Update</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">${user.id}</th>
                <td>
                    <c:out value="${user.role}"/>
                </td>
                <td>
                    <c:out value="${user.name}"/>
                </td>
                <td>
                    <c:out value="${user.login}"/>
                </td>
                <td>
                    <c:out value="${user.email}"/>
                </td>
                <td>
                    <c:out value="${user.country}"/>
                </td>
                <td>
                    <c:out value="${user.city}"/>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                        <button type="submit" class="btn btn-secondary">Update</button>
                    </form>
                </td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/users" method="post">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="<c:out value="${user.id}"/>"/>
                        <input type="hidden" name="role" value="<c:out value="${user.role}"/>"/>
                        <c:if test="${user.role == 'ADMIN'}">
                            <button type="submit" class="btn btn-secondary">Delete</button>
                        </c:if>
                        <c:if test="${user.role == 'USER'}">
                            <button disabled type="submit" class="btn btn-secondary">Delete</button>
                        </c:if>
                    </form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>