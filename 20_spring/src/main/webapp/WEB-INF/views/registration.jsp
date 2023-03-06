<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <style>
        body {
            text-align: center;
            margin-top: 250px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="/css/add-user.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <title>Registration</title>
</head>
<body>
<form:form method="POST" modelAttribute="userDto">
    <form:errors path="*" cssClass="error"/>
</form:form>

<h2>Registration</h2>
<form action="registration" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Password confirm:</td>
            <td>
                <input type="password" name="password_confirm">
            </td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="<c:out value="${userDto.email}" />"></td>
        </tr>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="firstName" value="<c:out value="${(userDto.firstName)}"/>"></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="lastName" value="<c:out value="${userDto.lastName}"/>"></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><input type="date" name="birthday" value="${userDto.birthday}"></td>
        </tr>
        <tr>
        </tr>
        <tr>
            <td colspan="3" align="center">
                <input type="submit" value="Sign up" name="add" disabled>
                <input type="reset" value="Clear">
                <a href="login"><input type="button" value="Back"></a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>

<script>
    function validation() {
        var pass = document.getElementsByName('password')[0];
        var confirm = document.getElementsByName('password_confirm')[0];
        var add = document.getElementsByName('add')[0];
        confirm.onkeyup = function () {
            if (confirm.value !== pass.value) {
                pass.style.border = '3px solid red';
                confirm.style.border = '3px solid red';
                add.setAttribute('disabled', '');
            } else {
                pass.style.border = '3px solid green';
                confirm.style.border = '3px solid green';
                add.removeAttribute('disabled');
            }
        }
    }

    window.onload = validation;
</script>