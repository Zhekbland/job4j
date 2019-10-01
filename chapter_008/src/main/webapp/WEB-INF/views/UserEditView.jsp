<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit</title>
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
        var userCountry = '${user.country}';
        var userCity = '${user.city}';

        function getCountries() {
            $.ajax({
                url: 'http://localhost:8080/chapter_008/country',
                method: 'get',
                contentType: 'text/json',
                complete: function (data) {
                    let countries = JSON.parse(data.responseText);
                    for (let value of countries) {
                        console.log(value);
                        console.log(userCountry);
                        if (value == userCountry) {
                            $('#country').append('<option selected value="' + value + '">' + value + '</option>');
                        } else {
                            $('#country').append('<option value="' + value + '">' + value + '</option>');
                        }
                    }
                    getCities();
                }
            });
        }


        function getCities() {
            $.ajax({
                url: 'http://localhost:8080/chapter_008/city',
                method: 'post',
                dataType: 'json',
                data: JSON.stringify($('#country option:selected').val()),
                complete: function (data) {
                    updateSelect();
                    let cities = JSON.parse(data.responseText);
                    for (let city of cities) {
                        if (city == userCity) {
                            $('#city').append('<option selected value="' + city + '">' + city + '</option>');
                        } else {
                            $('#city').append('<option value="' + city + '">' + city + '</option>');
                        }
                    }
                }
            });
        }

        function updateSelect() {
            $('#city').empty();
            $('#city').removeAttr('disabled');
        }

        function validate() {
            var result = true;
            for (let input of $(".form-control")) {
                if (input.value == '') {
                    alert("Fill " + input.getAttribute('name') + " form!");
                    result = false;
                }
            }
            return result;
        }

        function postData() {
            if (validate()) {
                let user = $('.form-control');
                $.ajax({
                    url: 'http://localhost:8080/chapter_008/edit',
                    method: 'POST',
                    dataType: 'JSON',
                    data: JSON.stringify({
                        name: user[0].value,
                        login: user[1].value,
                        email: user[2].value,
                        password: user[3].value,
                        role: user[4].value,
                        country: user[5].value,
                        city: user[6].value,
                    }),
                    complete: function () {
                        window.location.href = 'http://localhost:8080/chapter_008/users';
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row my-4">
        <div class="col-3">
            <form action="${pageContext.servletContext.contextPath}/edit" method="post">
                <div class="form-group">
                    <label for="name_input">Name</label>
                    <input id="name_input" type="text" class="form-control" name="name"
                           value="<c:out value="${user.name}"/>"/>
                </div>
                <div class="form-group">
                    <label for="login_input">Login</label>
                    <input id="login_input" type="text" class="form-control" name="login"
                           value="<c:out value="${user.login}"/>"/>
                </div>
                <div class="form-group">
                    <label for="email_input">Email</label>
                    <input id="email_input" type="text" class="form-control" name="email"
                           value="<c:out value="${user.email}"/>"/>
                </div>
                <div class="form-group">
                    <label for="password_input">Passowrd</label>
                    <input id="password_input" type="text" class="form-control" name="password"
                           value="<c:out value="${user.password}"/>"/>
                </div>

                <c:if test="${userRole == 'ADMIN'}">
                    <label>Role</label>
                    <select class="form-control" name="role">
                        <c:if test="${user.role == 'ADMIN' && user.login != sessionScope.get('login')}">
                            <option selected value="ADMIN">Administrator</option>
                            <option value="USER">User</option>
                        </c:if>
                        <c:if test="${user.role == 'ADMIN' && user.login == sessionScope.get('login')}">
                            <option selected value="ADMIN">Administrator</option>
                            <option disabled value="USER">User</option>
                        </c:if>
                        <c:if test="${user.role == 'USER'}">
                            <option value="ADMIN">Administrator</option>
                            <option selected value="USER">User</option>
                        </c:if>
                    </select>
                </c:if>
                <c:if test="${userRole == 'USER'}">
                    <label>Role</label>
                    <select disabled class="form-control" name="role">
                        <option selected value="USER">User</option>
                        <option disabled value="ADMIN">Administrator</option>
                    </select>
                    <input class="form-control" type="hidden" name="role" value="USER">
                </c:if>

                <div class="form-group my-3">
                    <label>Country</label>
                    <select class="form-control form-select" name="country" id="country" onchange="return getCities()">
                        <script>getCountries()</script>
                        <option selected value="<c:out value="${user.country}"/>"><c:out
                                value="${user.country}"/></option>
                    </select>
                </div>
                <div class="form-group my-3">
                    <label>City</label>
                    <select class="form-control form-select" name="city" id="city">
                    </select>
                </div>

                <div class="my-5">
                    <button type="button" class="btn btn-secondary" onclick="return postData()">Save</button>
                </div>
            </form>
        </div>
        <div class="col-9 d-flex justify-content-end mt-4">
            <form action="${pageContext.servletContext.contextPath}/signout" method="post">
                <button type="submit" class="btn btn-secondary">SignOut</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>