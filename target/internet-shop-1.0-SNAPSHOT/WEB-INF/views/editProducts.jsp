<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<h1>Add product</h1>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    name: <input type="text" name="name">
    price: <input type="text" name="price">
    <button type="submit">add</button>
</form>
<h1>List of products</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
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
                    /product/delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
