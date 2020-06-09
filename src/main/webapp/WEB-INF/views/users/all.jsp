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
<div>
<table border="1">
        <tr>
            <td colspan="6" style="text-align: center">Список пользователей</td>
        </tr>
    <tr>
        <th>id</th>
        <th>Имя</th>
        <th>Пароль</th>
        <th>Роль</th>
        <th>Удалить?</th>
        <th>Администратор?</th>
    </tr>
    <c:forEach var="user" items="${users}">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.password}"/></td>
        <td><c:out value="${user.roles}"/></td>
        <td><a href="${pageContext.request.contextPath}users/delete?user_id=${user.id}">Удалить</a></td>
        <td><a href="${pageContext.request.contextPath}users/admin_role?user_id=${user.id}">Получить\Убрать</a></td>
        <td><a href="${pageContext.request.contextPath}users?user_id=${user.id}&case=post">Получить\Убрать</a></td>
    </tr>
    </c:forEach>
</table>
    <h1 style="background-color:rgba(248, 227, 145, 0.8);background-size: cover ;text-align:center;align-content:center">${message}</h1>
</div>
</body>
</html>
