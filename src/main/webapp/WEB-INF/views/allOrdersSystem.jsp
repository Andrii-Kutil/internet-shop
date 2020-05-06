<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<h1>List of products below</h1>
<table border="1">
    <tr>
        <th>Order ID</th>
        <th>User login</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.user.login}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}
                    /orders/details?id=${order.id}">details</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}
                    /orders/delete?id=${order.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
