<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 28.04.2020
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<head>
    <title>Регистрация</title>
    <style>
        <%@include file="style/button.css"%>
    </style>
</head>
<body class="bg-light text-dark"><br>
<div class="py-md-1" style="text-align:center;"><br>
    <br>
    <h1>Регистрация</h1>
</div>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <h4 style="color:red;text-align: center">${message}</h4>
    <form class="needs-validation" method="post" novalidate>
        <div class="mx-auto" style="width: 400px;">
            <div class="form-group px-md-5">
                <label for="log">Логин:</label>
                <input type="text" class="form-control" id="log" aria-describedby="loginHelp"
                       name="login" required>
                <small id="loginHelp" class="form-text text-muted">
                    We'll never share your data with anyone else.</small>
                <div class="invalid-feedback">
                    Это обязательное поле!
                </div>
            </div>
        </div>
        <div class="mx-auto" style="width: 400px;">
            <div class="form-group px-md-5">
                <label for="pass1">Пароль:</label>
                <input type="password" name="password"
                       class="form-control" id="pass1" aria-describedby="passHelp">
                <small id="passHelp" class="form-text text-muted" >
                    Пароль должен содержать 8-20 символов :)</small>
                <div class="invalid-feedback">
                    Это обязательное поле!
                </div>
            </div>
        </div>
        <div class="mx-auto" style="width: 400px;">
            <div class="form-group px-md-5">
                <label for="pass2">Повторите Пароль</label>
                <input type="password" name="password-repeat" id="pass2"
                       class="form-control" aria-describedby="passHelp">
                <small id="passHelp1" class="form-text text-muted" >
                    Старайся использовать буквы и цифры :)</small>
                <div class="invalid-feedback">
                    Это обязательное поле!
                </div>
            </div>
        </div>
        <div style="text-align: center">
            <button class="button" type="submit">Регистрация</button>
        </div>
    </form>

<%--    <h1>Pls Provide your user details!</h1>--%>
<%--    <form method="post" action="${pageContext.request.contextPath}/registration">--%>
<%--        <h4 style="color:red">${message}</h4>--%>
<%--        Pls provide your login: <input type="text" name="login">--%>
<%--        Pls provide your password: <input type="password" name="password">--%>
<%--        Please repeat your password: <input type="password" name="password-repeat">--%>

<%--        <button type="submit">Register</button>--%>
</form>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<br>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</form>
</body>
</html>
