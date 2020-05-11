<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 28.04.2020
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file='../style/style.css' %>
    </style>
    <title>Пользователи</title>
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
        <li>
            <a href="">Для админов</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/buckets">Список корзин</a></li>
                <li><a href="${pageContext.request.contextPath}/users">Список пользователей</a></li>
            </ul>
        </li>
    </ul>
</div>
<div>
<table border="1">
        <tr>
            <td colspan="4" style="text-align: center">Список пользователей</td>
        </tr>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Пароль</th>
        <th>Роль</th>
        <th>Удалить?</th>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.password}"/></td>
        <td><c:out value="${user.roles}"/></td>
        <td><a href="${pageContext.request.contextPath}users/delete?user_id=${user.id}">Удалить</a></td>
    </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
