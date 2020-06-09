<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 03.05.2020
  Time: 19:33
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
    <title>Вход</title>
    <style>
        <%@include file="style/button.css"%>
    </style>
</head>
<body class="bg-light text-dark"><br>
<div class="py-md-1" style="text-align:center;"><br>
    <h1>Вход</h1>
</div>
<form method="post" action="${pageContext.request.contextPath}/login">
    <h4 style="color:red ; text-align: center">${message}</h4>
    <form class="needs-validation" method="post" novalidate>
        <div class="mx-auto" style="width: 400px;">
            <div class="form-group px-md-5">
                <label for="log">Логин:</label>
                <input type="text" class="form-control" id="log" aria-describedby="loginHelp"
                       name="login" required style="box-shadow: 0 5px rgb(255, 228, 99)">
                <small id="loginHelp" class="form-text text-muted">
                    We'll never share your data with anyone else.</small>
                <div class="invalid-feedback">
                    Это обязательное поле!
                </div>
            </div>
        </div>
        <div class="mx-auto" style="width: 400px;">
            <div class="form-group px-md-5">
                <label for="pass">Пароль:</label>
                <input type="password" style="box-shadow: 0 5px rgb(255, 228, 99)" class="form-control" id="pass" aria-describedby="passHelp"
                       name="password" required >
                <small id="passHelp" class="form-text text-muted">
                    Пароль должен содержать 8-20 символов</small>
                <div class="invalid-feedback">
                    Это обязательное поле!
                </div>
            </div>
        </div>
        <div style="text-align: center">
            <button class="button">Войти</button>
        </div>
    </form>
</form>
<br>
<div class="mx-auto" style="text-align: center"><p>Новенький?
    <a href="${pageContext.request.contextPath}/registration">Присоединиться!</a></p></div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
