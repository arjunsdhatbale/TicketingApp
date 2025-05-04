<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            text-align: center;
            margin-top: 100px;
        }
        h2 {
            margin-bottom: 40px;
            color: #333;
        }
        .button-container {
            display: flex;
            justify-content: center;
            gap: 40px;
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
    <h2>Welcome to the Ticketing App</h2>
    <div class="button-container">
        <a href="/users/view" class="btn">Manage Users</a>
        <a href="/ticket/viewTickets" class="btn">Manage Tickets</a>
        <a href="/progress" class="btn">Ticket Progress</a>
    </div>
</body>
</html>
