<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 30.04.2020
  Time: 0:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Buckets</title>
    <style>
        <%@include file='../style/style.css' %>
    </style>
</head>
<body>
<div class="text-center">
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
        <li>
            <a href="">Для админов</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/buckets">Список корзин</a></li>
                <li><a href="${pageContext.request.contextPath}/users">Список пользователей</a></li>
            </ul>
        </li>
    </ul>
</div>
<table border="1">
    <tr>
        <th>id</th>
        <th>user</th>
        <th>products</th>
    </tr>
<c:forEach var="bucket" items="${buckets}">
    <tr>
        <td><c:out value="${bucket.id}"/></td>
        <td><c:out value="${bucket.user.login}"/></td>
        <td><c:forEach var="product" items="${bucket.products}">
            <c:out value="${product.name}"/>
        </c:forEach>
        </td>
    </tr>
</c:forEach>
</table>
</body>
</html>
