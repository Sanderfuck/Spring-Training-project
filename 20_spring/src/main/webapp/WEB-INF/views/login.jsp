<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color:red">${errorMsg}</h4>
<form action="login" method="post">
    Username: <input type="text" name="username" required><br>
    <br>
    Password: <input type="password" name="password" required><br>
    <br>
    <input type="submit" value="Login">
</form>
</body>
</html>