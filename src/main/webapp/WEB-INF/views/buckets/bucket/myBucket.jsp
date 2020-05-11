<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 30.04.2020
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/views/style/style.css" %>
    </style>
    <title>Users</title>
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
            <c:if test="${roles == 'ADMIN'}">
            <ul>
                <li><a href="${pageContext.request.contextPath}/buckets">Список корзин</a></li>
                <li><a href="${pageContext.request.contextPath}/users">Список пользователей</a></li>
            </ul>
            </c:if>
        </li>
    </ul>
</div>
<div>
<table border="1">
    <tr>
        <th colspan="3" style="text-align: center">МОЯ КОРЗИНА</th>
    </tr>
    <tr>
        <th>Название</th>
        <th>Цена</th>
        <th></th>
    </tr>
    <c:forEach var="product" items="${bucket.products}">
    <tr>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><a href="${pageContext.request.contextPath}mybucket/clean?product_id=${product.id}">Clear</a></td>
    </tr>
    </c:forEach>
    <tr>
        <th style="border: #001780">Сумма</th>
        <th colspan="1" style="font-style: italic;">${sum}</th>
        <th style="border: #001780"></th>
    <tr>
</table>
</div>
</body>
</html>
