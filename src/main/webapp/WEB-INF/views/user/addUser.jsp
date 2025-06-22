<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Add User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            padding: 40px;
        }

        h2 {
            color: #1976d2;
            margin-bottom: 25px;
        }

        .form-container {
            max-width: 500px;
            margin: auto;
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        .checkbox-group {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
        }

        .checkbox-group label {
            margin-left: 8px;
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #1976d2;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #135ba1;
        }

        .toggle-password {
		    position: absolute;
		    right: 10px;
		    top: 40%;
		    transform: translateY(-50%);
		    cursor: pointer;
		    color: #888;
		    font-size: 16px;
		}


        .password-wrapper {
            position: relative;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #1976d2;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .error {
            color: red;
            font-size: 13px;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>

<div class="form-container">
    <h2>Add New User</h2>

    <form:form method="POST" action="/users/save" modelAttribute="user" onsubmit="return validateForm()">
        <label for="userName">User Name:</label>
        <form:input path="userName" id="userName" required="required"/>

        <label for="password">Password:</label>
        <div class="password-wrapper">
            <form:password path="password" id="password" required="required"/>
            <i class="fas fa-eye toggle-password" onclick="togglePassword()"></i>
        </div>

        <label for="email">Email:</label>
        <form:input path="email" id="email" type="email" required="required"/>

        <div class="checkbox-group">
            <form:checkbox path="active" id="active"/>
            <label for="active">Active</label>
        </div>

        <input type="submit" value="Add User" class="submit-btn"/>
    </form:form>

    <a href="/users/view" class="back-link"> Back to User List</a>
</div>

<script>
    function togglePassword() {
        const passwordField = document.getElementById("password");
        const icon = document.querySelector(".toggle-password");
        if (passwordField.type === "password") {
            passwordField.type = "text";
            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");
        } else {
            passwordField.type = "password";
            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");
        }
    }

    function validateForm() {
        const email = document.getElementById("email").value;
        const username = document.getElementById("userName").value;

        if (!username.trim()) {
            alert("User Name is required.");
            return false;
        }

        if (!email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
            alert("Please enter a valid email.");
            return false;
        }

        return true;
    }

    // Auto focus on username
    window.onload = () => document.getElementById("userName").focus();
</script>

</body>
</html>
