<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 09.05.2020
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Оплата</title>
    <style>
        <%@include file='../style/style.css' %>
    </style>
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
<table border="10">
    <tr>
        <td colspan="4" style="text-align: center">Мой заказ</td>
    </tr>
    <tr>
        <th>Название продукта</th>
        <th>Кол-во</th>
        <th>Стоиомсть</th>
    </tr>
    <tr>
        <c:forEach items="${strings}"  var="string" varStatus="status">
        <c:forTokens items="${string}" delims="," var="stringa">
        <td><c:out value="${stringa}"/></td>
        </c:forTokens>
    </tr>
    </c:forEach>
    <td colspan="2" style="text-align: center">Сумма</td>
    <td type="number" style="background-color: rgba(81,136,166,0.81); margin-left: .2em">${sum} грн.</td>
</table>
</body>
</html>
