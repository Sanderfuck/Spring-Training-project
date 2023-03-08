<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

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
    <a href="<%=request.getContextPath()%>/logout"> Logout</a>

    <style>
        .table {
            text-align: center;
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 60%;
            align-content: center;
        }

        .table td, .table th {
            align-content: center;
            border: 1px solid #ddd;
            padding: 8px;
        }

        .table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .table tr:hover {
            background-color: #ddd;
        }

        .table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>

<h1>Users registered:</h1>
<table class="table">
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
<%--                <form:form method="DELETE" action="delete">--%>
<%--                    <input type="hidden" name="userId" value="${user.id}" />--%>
<%--                    <button onclick="return showConfirm()" type="submit">Delete</button>--%>
<%--&lt;%&ndash;                    <form:hidden path="userId" value="DELETE" />&ndash;%&gt;--%>
<%--                </form:form>--%>

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
<%--                <form:form method="DELETE"--%>
<%--                           action="/delete" modelAttribute="user">--%>
<%--                    <input type="hidden" name="userId" value="${user.id}">--%>
<%--                    <button onclick="return showConfirm()" type="submit">Delete</button>--%>
<%--                </form:form>--%>
