<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
</head>
<body>
    <h2>Employee List</h2>
    
    <!-- Displaying the list of employees -->
    <table border="1">
        <thead>
            <tr>
                <th>Emp ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Designation</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.empId}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.designation}</td>
                    <td>${employee.role}</td>
                    <td>
                        <!-- Edit link -->
                        <a href="${pageContext.request.contextPath}/employee/edit/${employee.empId}">Edit</a> 
                        |
                        <!-- Delete link -->
                        <a href="${pageContext.request.contextPath}/employee/delete/${employee.empId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        <br /> <a href="/employee/add">Add New Employee</a>  </br> </br>
    </table>

    <!-- Optionally add a form to add new employee or other functionalities -->
</body>
</html>
