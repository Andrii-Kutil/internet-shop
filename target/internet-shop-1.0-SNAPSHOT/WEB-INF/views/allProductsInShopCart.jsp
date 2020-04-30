<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of products in shopping cart</title>
</head>
<h1>List of products in shopping cart</h1>
<body>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${shoppingCart.products}">
    <tr>
        <td>
            <c:out value="${product.id}"/>
        </td>
        <td>
            <c:out value="${product.name}"/>
        </td>
        <td>
            <c:out value="${product.price}"/>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}
                    /shoppingcart/products/delete?id=${product.id}">delete</a>
        </td>
    </tr>
    </c:forEach>
    <a href="${pageContext.request.contextPath}
                    /confirm?id=${shoppingCart.id}">CONFIRM</a>
</body>
</html>
