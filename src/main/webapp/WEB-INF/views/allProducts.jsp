<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h1>List of products below</h1>
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
                    /shoppingcarts/products/add?id=${product.id}">Add to shopping cart</a>

                </td>
            </tr>
        </c:forEach>
    </table>
</head>
<body>
</body>
</html>
