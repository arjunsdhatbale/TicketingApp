<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Add Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
        }

        .form-container {
            width: 50%;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        label {
            display: block;
            margin-top: 10px;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        .submit-btn {
            margin-top: 20px;
            text-align: center;
        }

        .submit-btn input {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .submit-btn input:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h2>Add New Employee</h2>

<div class="form-container">
    <form:form action="${pageContext.request.contextPath}/employee/save" method="post" modelAttribute="employee">
        <label for="firstName">First Name:</label>
        <form:input path="firstName" id="firstName" />

        <label for="lastName">Last Name:</label>
        <form:input path="lastName" id="lastName" />

        <div class="submit-btn">
            <input type="submit" value="Save Employee" />
        </div>
    </form:form>
    </br>
    <a href="/employee">Back</a>
</div>

</body>
</html>
