<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <meta charset="UTF-8">
    <title>User Home</title>
</head>
<body>
<h1>Hello, <c:out value="${login}"/>!</h1>
<a href="<%=request.getContextPath()%>/logout">Logout</a>
</body>
</html>