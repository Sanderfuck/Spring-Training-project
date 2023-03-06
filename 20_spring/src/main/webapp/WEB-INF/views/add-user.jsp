<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/add-user.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <title>Add user</title>
</head>
<body>

<c:if test="${userDto.id == null}">
    <h2>Add User</h2>
</c:if>
<c:if test="${userDto.id != null}">
    <h2>Edit User</h2>
</c:if>

<form:form method="POST" modelAttribute="userDto">
    <form:errors path="*" cssClass="error"/>
</form:form>

<form action="add-user" method="post">
    <table>
        <c:choose>
            <c:when test="${userDto.id == null}">
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>"></td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>Login:</td>
                    <td><input type="text" name="login" value="<c:out value="${userDto.login}"/>" readonly></td>
                </tr>
            </c:otherwise>
        </c:choose>

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
            <td>Date:</td>
            <td><input type="date" name="birthday" value="${userDto.birthday}"></td>
        </tr>
        <tr>
            <td>Role:</td>
            <td>
                <select name="roleName">
                    <c:forEach items="${rolesList}" var="role">
                        <option value="${role.name}"
                                <c:if test="${not empty userDto and userDto.roleName == role.name}">selected</c:if>>
                                ${role.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
            <input type="text" name="id" value="${userDto.id}" hidden/>

        </tr>
        <tr>
            <td colspan="3" align="center">
                <c:choose>
                    <c:when test="${userDto.id == null}">
                        <input type="submit" value="Add" name="add" disabled>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Edit" name="edit">
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