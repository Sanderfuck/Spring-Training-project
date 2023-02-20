<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/admin-home.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <script type="text/javascript">
        function showConfirm() {
            if (window.confirm("Are you sure that you want to delete this user?")) {
                return true;
            } else {
                return false;
            }
        }
    </script>
    <meta charset="UTF-8">
    <title>User List</title>
    ${user.firstName}  ${user.lastName}
    <a href="<%=request.getContextPath()%>/logout"> Logout</a>
</head>
<body>
<h1>Users registered:</h1>
<table>
    <tr>
        <th>Login</th>
        <th>Email</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Role</th>
        <th>Age</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.roleName}</td>
            <td>${user.age}</td>
            <td>
                <form action="add-user" method="GET">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button type="submit">Edit</button>
                </form>
            </td>
            <td>
                <form action="delete" method="POST">
                    <input type="hidden" name="userId" value="${user.id}">
                    <button onclick="return showConfirm()" type="submit">Delete</button>
                </form>
            </td>
        </tr>

    </c:forEach>
</table>
<a href="add-user">Add User</a>
<br>
</body>
</html>
