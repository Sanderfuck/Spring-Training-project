<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>User Home</title>
</head>
<body>
<h1> <c:out value="${greeting}"/>!</h1>
<a href="<%=request.getContextPath()%>/logout">Logout</a>
</body>
</html>