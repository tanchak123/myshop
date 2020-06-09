<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 29.04.2020
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список продуктов</title>
    <style>
        <%@include file='../style/style.css' %>
    </style>
</head>
<body>
<div class="text-center" style="">
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
        <li><a href="${pageContext.request.contextPath}/products">Список продуктов</a></li>
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
        </li>
    </ul>
</div>
<table border="10">
    <tr>
        <th colspan="7" style="text-align: center">СПИСОК НАШИХ ТОВАРОВ</th>
    </tr>
    <tr>
        <th>id</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Купить?</th>
        <th>Картинка</th>
<c:if test="${roles == 'ADMIN'}">
        <th>Изменить картинку</th>
</c:if>
    </tr>
<c:forEach var="product" items="${products}">
    <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td><c:out value="${product.price}"/></td>
        <td><img src="${product.image}"></td>
        <td><a href="${pageContext.request.contextPath}products/add?product_id=${product.id}">В корзину</a></td>
        <c:if test="${roles == 'ADMIN'}">
        <td><a href="${pageContext.request.contextPath}/imagec?product_id=${product.id}" class="btn">Изменить картинку</a></td>
        </c:if>
    </tr>
</c:forEach>
    <c:if test="${roles == 'ADMIN'}">
    <tr><th colspan="6" style="text-align: center"><a href="${pageContext.request.contextPath}/products/create">Создать товар</a></th></tr>
    </c:if>
</table>
</body>
</html>
