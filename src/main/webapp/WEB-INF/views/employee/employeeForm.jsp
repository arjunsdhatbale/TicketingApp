<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<!DOCTYPE html>
<html>
<head>
	<title>Add/Edit Employee</title>
	<style>
		body {
			font-family: 'Segoe UI', sans-serif;
			background-color: #f4f6f8;
			margin: 0;
			padding: 0;
		}

		.container {
			max-width: 600px;
			margin: 40px auto;
			background-color: #fff;
			border-radius: 12px;
			box-shadow: 0 2px 12px rgba(0,0,0,0.1);
			padding: 30px;
		}

		h2 {
			text-align: center;
			color: #333;
			margin-bottom: 25px;
		}

		label {
			font-weight: 600;
			margin-top: 15px;
			display: block;
			color: #555;
		}

		input[type="text"], select {
			width: 100%;
			padding: 10px 12px;
			margin-top: 6px;
			border-radius: 6px;
			border: 1px solid #ccc;
			box-sizing: border-box;
			font-size: 14px;
			transition: border 0.3s;
		}

		input[type="text"]:focus, select:focus {
			border-color: #007bff;
			outline: none;
		}

		.button-group {
			text-align: center;
			margin-top: 30px;
		}

		input[type="submit"] {
			background-color: #007bff;
			color: white;
			padding: 10px 20px;
			border: none;
			border-radius: 6px;
			font-size: 16px;
			cursor: pointer;
			transition: background-color 0.3s;
		}

		input[type="submit"]:hover {
			background-color: #0056b3;
		}

		.back-link {
			display: block;
			text-align: center;
			margin-top: 20px;
			text-decoration: none;
			color: #007bff;
			font-weight: bold;
		}

		.back-link:hover {
			text-decoration: underline;
		}

		.error {
			color: red;
			font-size: 12px;
			margin-top: 4px;
		}
	</style>
</head>
<body>

	<div class="container">
		<h2>
			<c:choose>
				<c:when test="${employee.empId == null}">Add New Employee</c:when>
				<c:otherwise>Edit Employee</c:otherwise>
			</c:choose>
		</h2>

		<form:form action="${pageContext.request.contextPath}/employee/save" method="post" modelAttribute="employee">
			<!-- Hidden field to retain empId during edit -->
			<form:hidden path="empId" />

			<label for="firstName">First Name:</label>
			<form:input path="firstName" id="firstName" />
			<form:errors path="firstName" cssClass="error"/>

			<label for="lastName">Last Name:</label>
			<form:input path="lastName" id="lastName" />
			<form:errors path="lastName" cssClass="error"/>

			<label for="designation">Designation:</label>
			<form:select path="designation" id="designation">
				<form:option value="" label="-- Select Designation --" />
				<c:forEach items="${designations}" var="designation">
					<form:option value="${designation}" label="${designation}" />
				</c:forEach>
			</form:select>
			<form:errors path="designation" cssClass="error"/>

			<label for="role">Role:</label>
			<form:select path="role" id="role">
				<form:option value="" label="-- Select Role --" />
				<c:forEach items="${roles}" var="role">
					<form:option value="${role}" label="${role}" />
				</c:forEach>
			</form:select>
			<form:errors path="role" cssClass="error"/>

			<div class="button-group">
				<input type="submit" value="Save Employee" />
			</div>
		</form:form>

		<a href="${pageContext.request.contextPath}/employee/viewEmployee" class="back-link">← Back to Employee List</a>
	</div>

</body>
</html>
