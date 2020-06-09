<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Главная страница</title>
    <style>
        <%@include file='style/style.css' %>
    </style>
    <link href="http://allfont.ru/allfont.css?fonts=open-sans" rel="stylesheet" type="text/css" />
</head>
<body class="text-center bg-light text-dark">
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
</body>
</html>