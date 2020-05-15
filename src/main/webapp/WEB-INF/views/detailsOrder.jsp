<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<h1>List of products below</h1>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>User</th>
    </tr>
    <td>
        <c:out value="${order.id}"/>
    </td>
    <td>
        <c:out value="ID: ${order.userId}"/><br>
        <c:out value="Login: ${user.login}"/><br>
        <c:out value="Name: ${user.name}"/>
    </td>
</table>
<table border="1">
    <tr>
        <th>Products</th>
    </tr>
    <c:forEach var="products" items="${order.products}">
        <td>
            <c:out value="ID: ${products.id}"/><br>
            <c:out value="Name: ${products.name}"/><br>
            <c:out value="Price: ${products.price}"/>
        </td>
    </c:forEach>
</table>
</body>
</html>
