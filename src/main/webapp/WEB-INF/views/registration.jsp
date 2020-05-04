<html>
<head>
    <title>Registration</title>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<h2 style="color: crimson">
    ${message}
</h2>
<div class="container-md">
    <form method="post" action="${pageContext.request.contextPath}/registration">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" name="name" class="form-control" id="name" aria-describedby="emailHelp">
        </div>
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" name="login" class="form-control" id="login" aria-describedby="emailHelp">
        </div>
        <div class="form-group">
            <label for="pwd">Password</label>
            <input type="password" name="pwd" class="form-control" id="pwd">
        </div>
        <div class="form-group">
            <label for="pwd-repeat">Repeat password</label>
            <input type="password" name="pwd-repeat" class="form-control" id="pwd-repeat">
        </div>
        <button type="submit" class="btn btn-primary">Sign in</button>
        or
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/login" role="button">Log in</a>
    </form>
</div>
</body>
</html>
