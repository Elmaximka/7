
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<h3>${user}</h3>
<form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
