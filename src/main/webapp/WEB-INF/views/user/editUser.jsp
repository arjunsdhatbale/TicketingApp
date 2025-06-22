<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            padding-top: 40px;
        }

        .form-container {
            background: white;
            padding: 30px 40px;
            border-radius: 8px;
            box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        input[type="checkbox"] {
            margin-top: 10px;
        }

        .actions {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .btn {
            padding: 10px 18px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #125da0;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Edit User</h2>
    <form:form method="POST" action="/users/update" modelAttribute="user">
        <form:hidden path="id"/>

        <label>User Name:</label>
        <form:input path="userName" required="required"/>

        <label>Password:</label>
        <form:input path="password" required="required"/>

        <label>Email:</label>
        <form:input path="email" required="required"/>

        <label>Active:</label>
        <form:checkbox path="active"/>

        <div class="actions">
            <input type="submit" value="Update User" class="btn"/>
            <a href="/users/view" class="btn">Back</a>
        </div>
    </form:form>
</div>
</body>
</html>
