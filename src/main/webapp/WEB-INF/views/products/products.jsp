<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 29.04.2020
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Products</title>
    <style>
        <%@include file='../style/style.css' %>
    </style>
</head>
<body>

<div class="text-center" style="padding-bottom: 200px; height: 100%">
    <ul id ="nav">
        <li>
            <a href="">Мой профиль</a>
            <ul>
                <li><a href="${pageContext.request.contextPath}/mybucket">Моя корзина</a></li>
                <li><a href="${pageContext.request.contextPath}/user/orders">My Orders</a></li>
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
<table border="10">
    <tr>
        <th colspan="4" style="text-align: center">СПИСОК НАШИХ ТОВАРОВ</th>
    </tr>
    <tr>
        <th>id</th>
        <th>Название</th>
        <th>Цена</th>
        <th>Купить?</th>
    </tr>
<c:forEach var="product" items="${products}">
    <tr>
        <td><c:out value="${product.id}"/></td>
        <td><c:out value="${product.name}"/></td>
        <td charset="Java"><c:out value="${product.price}"/></td>
        <td><a href="${pageContext.request.contextPath}products/add?product_id=${product.id}">В корзину</a></td>
    </tr>
</c:forEach>
    <tr><th colspan="4" style="text-align: center"><a href="${pageContext.request.contextPath}/products/create">Создать товар</a></th></tr>
</table>
</body>
</html>
