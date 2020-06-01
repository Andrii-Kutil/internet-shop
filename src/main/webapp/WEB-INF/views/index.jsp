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
<jsp:include page="head.jsp"/>
<h1 class="text-center">Hello, ${userName}!</h1>
<style>
    .fig {
        text-align: center;
    }
</style>
<html>
<head>
    <meta charset="utf-8">
    <title>Фотография с подписью</title>
    <style>
        .fig {
            display: block;
            text-align: center;
            margin-top: 0;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
<figure class="fig">
    <img style="-webkit-user-select: none;margin: auto;"
         src="https://alexsmokinof.lviv.ua/wp-content/uploads/2019/03/101.jpg" width="1000" height="600">
</figure>
<div class="container-md">
<a href="${pageContext.request.contextPath}/inject">
    <button type="button" class="btn btn-primary btn-lg btn-block">Inject ADMIN</button>
</a>
</div>
</body>
</html>
</body>
</html>
