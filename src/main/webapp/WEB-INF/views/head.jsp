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
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <img style="-webkit-user-select: none;margin: auto;" src="https://image.freepik.com/free-vector/cute-unicorn-doing-dabbing_21799-168.jpg" width="80" height="80">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/products/all">Products</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/shoppingcarts/products/all">Cart</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/orders/all">My orders</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/users/all">All users</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/products/manage">Product management</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/orders/all/system">All orders</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/logout">Log out</a>
        </div>
    </div>
</nav>
</body>
</html>
