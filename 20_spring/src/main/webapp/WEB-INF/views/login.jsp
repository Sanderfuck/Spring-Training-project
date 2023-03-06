<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            text-align: center;
            margin-top: 250px;
        }
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Login page</h1>
<form action="login" method="post">
    <c:if test="${param.registrationSuccess != null}">
        <div class="alert alert-success">
            <p>Registration was successful</p>
        </div>
    </c:if>
    <c:if test="${param.error != null}">
        <div class="alert alert-danger">
            <p>Entered invalid username or password</p>
        </div>
    </c:if>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            <p>You have successfully logged out</p>
        </div>
    </c:if>
    Username: <input type="text" name="username" required><br>
    <br>
    Password: <input type="password" name="password" required><br>
    <br>
    <input type="submit" value="Log in">
    <br>
    <br>
    <a href="registration"><input type="button" value="Sign Up"></a>
</form>
</body>

</html>