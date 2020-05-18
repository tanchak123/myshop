<%--
  Created by IntelliJ IDEA.
  User: Paul
  Date: 18.05.2020
  Time: 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить картинку</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/imagec">
    <h4 style="text-align: center;color: red">Укажите ссылку</h4>
<input style="text-align: center" type="text" class="form-control" id="img_url"
       name="img_url">
    <div style="text-align: center">
        <button type="submit">Создать :)</button>
    </div>
</form>
</body>
</html>
