<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
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
    <script>
        function validate() {
            for (let input of $(".form-control")) {
                var result = true;
                if (input.value == '') {
                    alert("Fill " + input.getAttribute('name') + " form!");
                    result = false;
                }
            }
            return result;
        }
    </script>
</head>
<body>
<c:if test="${error != ''}">
    <div class="alert-danger" role="alert" align="center">
        <c:out value="${error}"/>
    </div>
</c:if>

<div class="container">
    <div class="row my-4">
        <div class="col-3">
            <form action="${pageContext.servletContext.contextPath}/signin" method="post">
                <div class="form-group">
                    <label for="name_input">Login</label>
                    <input id="name_input" type="text" class="form-control" name="login" placeholder="Enter login">
                </div>
                <div class="form-group">
                    <label for="password_input">Password</label>
                    <input id="password_input" type="password" class="form-control" name="password"
                           placeholder="Enter password">
                </div>
                <button type="submit" class="btn btn-secondary" onclick="return validate()">SignIn</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
