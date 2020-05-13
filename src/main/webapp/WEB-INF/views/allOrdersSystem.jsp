<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<h1 class="container-md col-2 font-weight-bold">All orders</h1>
<div class="container-md">
    <table class="table table-bordered font-italic">
        <thead class="thead-dark">
        <th>Order ID</th>
        <th>User's login</th>
        <th>Details</th>
        <th>from system</th>
        </thead>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>
                    <c:out value="${order.id}"/>
                </td>
                <td>
                    <c:out value="${userLogin}"/>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}
                    /orders/details?id=${order.id}">Details</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}
                    /orders/delete?id=${order.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
