<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/add-user.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <title>Add user</title>
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
</head>
<body>

<h2>${namePage}</h2>
<form action="add-user" method="post">
    <table>
        <c:choose>
            <c:when test="${userDto.id == null}">
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>"></td>
                    <td><c:if test="${errors.contains('Login is required')}">
                        <font color="red">Login is required</font>
                    </c:if></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>" disabled></td>
                    <td><c:if test="${errors.contains('Login is required')}">
                        <font color="red">Login is required</font>
                    </c:if></td>
                </tr>
            </c:otherwise>
        </c:choose>

        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
            <td><c:if test="${errors.contains('Password is required')}">
                <font color="red">Password is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Password confirm:</td>
            <td>
                <input type="password" name="password_confirm">
            </td>
            <td><c:if test="${errors.contains('Password confirm is required')}">
                <font color="red">Password confirm is required</font>
            </c:if></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="<c:out value="${userDto.email}"/>"></td>
            <td><c:if test="${errors.contains('Email is required')}">
                <font color="red">Email is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="first_name" value="<c:out value="${userDto.firstName}"/>"></td>
            <td><c:if test="${errors.contains('Email is required')}">
                <font color="red">First name is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="last_name" value="<c:out value="${userDto.lastName}"/>"></td>
            <td><c:if test="${errors.contains('Email is required')}">
                <font color="red">Last name is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Date:</td>
            <td><input type="text" name="birthday" value="<c:out value="${userDto.birthday}"/>"></td>
            <td><c:if test="${errors.contains('Date is required')}">
                <font color="red">Birthday is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <select name="role">
                    <c:forEach items="${rolesList}" var="userRole">
                        <option value="${userRole.id}"
                                <c:if test="${not empty userDto and userDto.roleId == userRole.id}">selected</c:if>>
                                ${userRole.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <td><c:if test="${errors.contains('Role is required')}">
                <font color="red">Role is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td colspan="3" align="center">
                <c:choose>
                    <c:when test="${userDto.id == null}">
                        <input type="submit" value="${nameButton}" name="add" disabled>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="${nameButton}">
                    </c:otherwise>
                </c:choose>
                <input type="reset" value="Clear">
                <a href="admin-home"><input type="button" value="Back"></a>
            </td>
        </tr>
    </table>
</form>
</body>

</html>