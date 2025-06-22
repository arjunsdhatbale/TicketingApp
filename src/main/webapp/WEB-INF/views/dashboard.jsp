<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #1976d2;
            padding: 20px;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header h2 {
            margin: 0;
            font-size: 24px;
        }

        .logout-btn {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
        }

        .logout-btn:hover {
            background-color: #d32f2f;
        }

        .button-container {
            display: flex;
            justify-content: center;
            gap: 40px;
            margin-top: 100px;
        }

        .btn {
            padding: 15px 30px;
            font-size: 18px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #135ba1;
        }
    </style>
</head>
<body>

    <div class="header">
        <h2>Welcome to the Ticketing App</h2>
        <form action="/logout" method="post" style="margin: 0;">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit" class="logout-btn">Logout</button>
        </form>
    </div>

    <div class="button-container">
        <a href="/users/view" class="btn">Manage Users</a>
        <a href="/ticket/viewTickets" class="btn">Manage Tickets</a>
        <a href="/progress" class="btn">Ticket Progress</a>
        <a href="/employee/viewEmployee" class="btn">Employee</a>
    </div>

</body>
</html>
