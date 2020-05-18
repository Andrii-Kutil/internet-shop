<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<jsp:include page="head.jsp"/>
<h1 class="container-md col-2 font-weight-bold">Details</h1>
<div class="container-md">
    <table class="table table-bordered font-italic">
        <thead class="thead-dark">
        <th>Order ID</th>
        <th>Login</th>
        </thead>
        <tr>
            <td>
                <c:out value="${order.id}"/>
            </td>
            <td>
                <c:out value="${order.userId}"/>
            </td>
        </tr>
    </table>
</div>
<div class="container-md">
    <table class="table table-bordered font-italic">
        <thead class="thead-dark">
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        </thead>
        <c:forEach var="products" items="${order.products}">
            <tr>
                <td>
                    <c:out value="${products.id}"/>
                </td>
                <td>
                    <c:out value="${products.name}"/>
                </td>
                <td>
                    <c:out value="${products.price}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
