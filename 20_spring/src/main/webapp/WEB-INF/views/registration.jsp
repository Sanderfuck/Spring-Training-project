<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/add-user.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <title>Registration</title>
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

<h2>Registration</h2>
<form action="registration" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>"></td>
            <td><c:if test="${errors.containsKey('login')}">
                <font color="red">${errors.get('login')}</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
            <td><c:if test="${errors.containsKey('password')}">
                <font color="red">${errors.get('password')}</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Password confirm:</td>
            <td>
                <input type="password" name="password_confirm">
            </td>
            <td><c:if test="${errors.containsKey('Password confirm is required')}">
                <font color="red">Password confirm is required</font>
            </c:if></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="<c:out value="${userDto.email}" />"></td>
            <td><c:if test="${errors.containsKey('email')}">
                <font color="red">${errors.get('email')}</font>
            </c:if></td>
        </tr>
        <tr>
            <td>First name:</td>
            <td><input type="text" name="first_name" value="<c:out value="${(userDto.firstName)}"/>"></td>
            <td><c:if test="${errors.containsKey('firstName')}">
                <font color="red">${errors.get('firstName')}</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><input type="text" name="last_name" value="<c:out value="${userDto.lastName}"/>"></td>
            <td><c:if test="${errors.containsKey('lastName')}">
                <font color="red">${errors.get('lastName')}</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Date:</td>
            <td><input type="date" name="birthday" value="${userDto.birthday}"></td>
            <td><c:if test="${errors.containsKey('Date is required')}">
                <font color="red">Birthday is required</font>
            </c:if></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <select name="role">
                    <c:forEach items="${rolesList}" var="userRole">
                        <option value="${userRole.name}"
                                <c:if test="${not empty userDto and userDto.role.id == userRole.id}">selected</c:if>>
                                ${userRole.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <%--            <td><c:if test="${errors.containsKey('Role is required')}">--%>
            <%--                <font color="red">Role is required</font>--%>
            <%--            </c:if></td>--%>
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