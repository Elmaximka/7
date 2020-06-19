<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Login Page</title>
</head>
<body>
<p>Login page</p>
<c:url value="/login" var="loginUrl" />
<form action="${loginUrl}" method="post">
    Name: <input type="text" class="form-control" name="j_username">
    <br>
    <br>
    Password: <input type="password" class="form-control" name="j_password">
    <br>
    <br>
    <input type="submit" name="Sign in"/>

</form>
</body>
</html>
