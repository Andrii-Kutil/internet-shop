<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Dear User! Please, provide your details below:</h1>

<h2 style="color: crimson">
    ${message}
</h2>

<form method="post" action="${pageContext.request.contextPath}/registration">
    name: <input type="text" name="name">
    login: <input type="text" name="login">
    password: <input type="password" name="pwd">
    repeat password: <input type="password" name="pwd-repeat">
    <button type="submit">Register</button>
</form>
<a href="${pageContext.request.contextPath}/login">Login</a>
</body>
</html>
