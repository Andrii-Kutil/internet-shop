<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome to my world!!!</h1>
<a href="${pageContext.request.contextPath}/injectData">Inject test data into the DB</a><br>
<a href="${pageContext.request.contextPath}/users/all">List of users</a><br>
<a href="${pageContext.request.contextPath}/products/all">List of products</a><br>
<a href="${pageContext.request.contextPath}/shoppingcart/products/all">List of product in shopping cart</a><br>
<a href="${pageContext.request.contextPath}/registration">Registration</a><br>
<a href="${pageContext.request.contextPath}/product/edit">Edit products</a><br>
<a href="${pageContext.request.contextPath}/orders/all">Orders</a>
</body>
</html>
