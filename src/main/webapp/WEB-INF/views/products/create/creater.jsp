<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 29.04.2020
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
      crossorigin="anonymous">
<link href="http://allfont.ru/allfont.css?fonts=open-sans" rel="stylesheet" type="text/css" />
<head>
    <style>
        <%@include file='/WEB-INF/views/style/style.css' %>
    </style>
    <title>Новый товар</title>
</head>
<body class="text-center bg-light text-dark">
<div class="text-center" style="padding-bottom: 200px">
    <ul id ="nav">
        <li>
            <a href="${pageContext.request.contextPath}/myprofile">Мой профиль</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mybucket">Моя корзина</a></li>
                <li><a href="${pageContext.request.contextPath}/order">Мои заказы</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Выйти</a></li>
            </ul>
        </li>
        <li>
            <a href="">Регистрация</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
                <li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
            </ul>
        </li>
        <li><a href="${pageContext.request.contextPath}/products">Список продуктов products</a></li>
        <c:choose>
            <c:when test="${roles == 'ADMIN'}">
                <li>
                    <a href="">Для админов</a>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/buckets">Список корзин</a></li>
                        <li><a href="${pageContext.request.contextPath}/users">Список пользователей</a></li>
                    </ul>
                </li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="/">Главная</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<br>
<div class="py-md-1" style="text-align:center;background: rgba(255,255,0,0.65);
     width: 600px;
     height: 300px;
     position: absolute;
     top: 50%;
     left: 50%;
     margin: -125px 0 0 -300px;">
    <h1>НОВЫЙ ТОВАР</h1>
    <form method="post" action="${pageContext.request.contextPath}/products/create">
        <h4 style="color:red">${message}</h4>
        <div class="mx-auto" style="width: 400px;">
            <label for="name1">Укажите имя товара:</label>
            <input type="text" name="name" id="name1"
                                       class="form-control" aria-describedby="passHelp">
        </div>
        <div class="mx-auto" style="width: 400px;">
            <label for="price1" style="background: rgba(255,205,0,0.47); color: #2D2020;">Укажите цену товара </label>
            <input type="number" min="0,01" step="0,01" max="1_000_000" name="price"
                   class="form-control" aria-describedby="passHelp" id="price1">
        </div><br>
        <div style="text-align: center">
            <button type="submit">Создать :)</button>
        </div>
    </form>
</div>
</body>
</html>
