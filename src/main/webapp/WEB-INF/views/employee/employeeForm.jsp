<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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

	<h2>
		<c:choose>
			<c:when test="${employee.empId == null}">
                Add New Employee
            </c:when>
			<c:otherwise>
                Edit Employee
            </c:otherwise>
		</c:choose>
	</h2>

	<div class="form-container">
		<form:form action="${pageContext.request.contextPath}/employee/save"
			method="post" modelAttribute="employee">

			<!-- Hidden field to retain empId during edit -->
			<form:hidden path="empId" />

			<label for="firstName">First Name:</label>
			<form:input path="firstName" id="firstName" />

			<label for="lastName">Last Name:</label>
			<form:input path="lastName" id="lastName" />

            <label for="designation">Designation:</label>
			<form:select path="designation" id="designation">
				<form:option value="" label="-- Select Designation --"
					disabled="true" selected="true" />
				<c:forEach items="${designations}" var="designation">
					<form:option value="${designation}" label="${designation}" />
				</c:forEach>
			</form:select>

			<label for="role">Role:</label>
			<form:select path="role" id="role">
				<form:option value="" label="-- Select Role --" disabled="true"
					selected="true" />
				<c:forEach items="${roles}" var="role">
					<form:option value="${role}" label="${role}" />
				</c:forEach>
			</form:select>


			<div class="submit-btn">
				<input type="submit" value="Save Employee" />
			</div>
		</form:form>

		<br /> <a href="/employee/viewEmployee">Back</a>
	</div>

</body>
</html>
