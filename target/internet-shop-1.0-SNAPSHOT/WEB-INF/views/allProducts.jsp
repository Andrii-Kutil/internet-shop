<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get all product</title>
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
<<<<<<< HEAD
                    /add/products/shoppingcart?id=${product.id}">add in shopping cart</a>
=======
                    /add/products/shoppingcart?id=${product.id}">Add to shopping cart</a>
>>>>>>> origin/master
                </td>
            </tr>
        </c:forEach>
    </table>
</head>
<body>
</body>
</html>
<<<<<<< HEAD

=======
>>>>>>> origin/master
