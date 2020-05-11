<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 08.05.2020
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Недоступно</title>
    <style>
        <%@include file="../style/style.css"%>
    </style>
</head>
<body>
<div class="text-center bg-light text-dark">
    <ul id ="nav">
        <li>
            <a href="${pageContext.request.contextPath}/myprofile">Мой профиль</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mybucket">Моя корзина</a></li>
                <li><a href="${pageContext.request.contextPath}/order">My Orders</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
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
            <li>
                <a href="/">Главная</a>
            </li>
    </ul>
</div>
<h1 style="text-align: center; background-color: rgba(255, 228, 99, 0.71)">Извините, доступ запрещен</h1>
</body>
</html>
