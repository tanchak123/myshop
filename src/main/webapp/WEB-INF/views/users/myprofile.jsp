<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 09.05.2020
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        <%@include file='../style/style.css' %>
    </style>
    <title>Мой профиль</title>
    <link href="http://allfont.ru/allfont.css?fonts=open-sans" rel="stylesheet" type="text/css" />
</head>
<body class="text-center bg-light text-dark">
<div class="text-center">
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
<table border="1">
    <tr>
        <td colspan="4">Мой профиль</td>
    </tr>
    <tr>
        <th>Айди пользователя</th>
        <th>Имя пользователя</th>
        <th>Пароль</th>
        <th>Роль</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.password}</td>
        <td>${user.roles}</td>
    </tr>
    <div style="text-align: center">
        <button type="submit">Оплатить</button>
    </div>
</table>
</body>
</html>
