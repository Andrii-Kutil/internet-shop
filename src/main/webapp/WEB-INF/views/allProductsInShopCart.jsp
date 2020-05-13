<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of products in shopping cart</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<h1 class="container-md col-1 font-weight-bold">Cart</h1>
<div class="container-md">
    <table class="table table-bordered font-italic">
        <thead class="thead-dark">
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>from cart</th>
        </thead>
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
                    /shoppingcarts/products/delete?id=${product.id}">Delete</a>

                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="${pageContext.request.contextPath}
                    /confirm?id=${shoppingCart.id}">
        <button type="button" class="btn btn-primary btn-lg btn-block">CONFIRM</button>
    </a>
</div>
</body>
</html>
