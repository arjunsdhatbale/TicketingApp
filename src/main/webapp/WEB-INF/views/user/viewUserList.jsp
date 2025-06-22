<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <title>All Users</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 20px;
        }

        h2 {
            color: #333;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .add-user-btn {
            padding: 10px 20px;
            background-color: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-size: 16px;
        }

        .add-user-btn:hover {
            background-color: #135ba1;
        }

        .filter-form {
            margin-bottom: 20px;
            background: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0,0,0,0.1);
        }

        .filter-form input,
        .filter-form select {
            padding: 8px;
            margin-right: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .filter-form button {
            padding: 8px 16px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .filter-form button:hover {
            background-color: #135ba1;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #1976d2;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        .btn {
            padding: 6px 14px;
            font-size: 14px;
            text-decoration: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .edit-btn {
            background-color: #4caf50;
            color: white;
        }

        .edit-btn:hover {
            background-color: #45a049;
        }

        .delete-btn {
            background-color: #f44336;
            color: white;
        }

        .delete-btn:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>

<div class="top-bar">
    <h2>List of Users</h2>
    <a href="/users/add" class="add-user-btn">+ Add New User</a>
</div>


<form method="get" action="/users/view" class="filter-form">
    <input type="text" name="userName" placeholder="Search Username" value="${param.userName}">
    <input type="text" name="email" placeholder="Search Email" value="${param.email}">
    <select name="active">
        <option value="">-- Active Status --</option>
        <option value="true" ${param.active == 'true' ? 'selected' : ''}>Active</option>
        <option value="false" ${param.active == 'false' ? 'selected' : ''}>Inactive</option>
    </select>
    <select name="sortField">
        <option value="id" ${param.sortField == 'id' ? 'selected' : ''}>ID</option>
        <option value="userName" ${param.sortField == 'userName' ? 'selected' : ''}>User Name</option>
        <option value="email" ${param.sortField == 'email' ? 'selected' : ''}>Email</option>
    </select>
    <select name="sortDir">
        <option value="desc" ${param.sortDir == 'desc' ? 'selected' : ''}>Desc</option>
        <option value="asc" ${param.sortDir == 'asc' ? 'selected' : ''}>Asc</option>
    </select>
    <button type="submit">Apply</button>
</form>

<c:if test="${not empty msg}">
    <script>alert("${msg}");</script>
</c:if>

 
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Email</th>
            <th>Active</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.active ? 'Yes' : 'No'}</td>
                <td>
                    <a href="/users/edit/${user.id}" class="btn edit-btn" title="Edit">
                        <i class="fas fa-pen-to-square"></i>
                    </a>
                </td>
                <td>
                    <a href="/users/delete/${user.id}" class="btn delete-btn" title="Delete">
                        <i class="fas fa-trash-alt"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
