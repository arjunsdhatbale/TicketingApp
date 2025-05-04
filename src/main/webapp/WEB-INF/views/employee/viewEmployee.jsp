 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Employee List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e0f7fa;
        }

        .btn {
            padding: 5px 10px;
            text-decoration: none;
            border-radius: 4px;
            color: white;
        }

        .btn-edit {
            background-color: #4CAF50;
        }

        .btn-delete {
            background-color: #f44336;
        }

        .add-button {
            display: block;
            text-align: center;
            margin: 20px;
        }

        .add-button a {
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

    </style>
</head>
<body>

<h2>List of Employees</h2>

<c:choose>
    <c:when test="${not empty employees}">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="emp" items="${employees}">
                <tr>
                    <td>${emp.empId}</td>
                    <td>${emp.firstName}</td>
                    <td>${emp.lastName}</td>
                    <td>
                        <a class="btn btn-edit" href="/employee/edit/${emp.empId}">Edit</a>
                        <a class="btn btn-delete" href="/employee/delete/${emp.empId}"
                           onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p style="text-align:center; color: red;">No employees found.</p>
    </c:otherwise>
</c:choose>

<div class="add-button">
    <a href="/employee/add">Add New Employee</a>
</div>

</body>
</html>
 