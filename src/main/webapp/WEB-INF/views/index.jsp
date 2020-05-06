<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello, ${userName}!</h1>
<a href="${pageContext.request.contextPath}/injectData">Inject test products into the DB</a><br>
<a href="${pageContext.request.contextPath}/products/all">List of products</a><br>
<a href="${pageContext.request.contextPath}/shoppingcarts/products/all">List of product in shopping cart</a><br>
<a href="${pageContext.request.contextPath}/orders/all">My orders</a><br><br>
<a href="${pageContext.request.contextPath}/users/all">List of users</a><br>
<a href="${pageContext.request.contextPath}/products/manage">Product management</a><br>
<a href="${pageContext.request.contextPath}/orders/all/system">Orders all users</a><br>
<a href="${pageContext.request.contextPath}/logout">Logout</a>
</body>
</html>
