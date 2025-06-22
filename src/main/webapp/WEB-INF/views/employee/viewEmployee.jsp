<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Employee Management</title>
	<style>
		body {
			font-family: 'Segoe UI', sans-serif;
			background-color: #f0f2f5;
			margin: 0;
			padding: 0;
		}

		.container {
			max-width: 1100px;
			margin: 40px auto;
			background-color: #fff;
			border-radius: 10px;
			box-shadow: 0 2px 10px rgba(0,0,0,0.1);
			padding: 20px 30px;
		}

		.header-row {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 20px;
		}

		h2 {
			color: #333;
			margin: 0;
		}

		.add-button {
			background-color: #28a745;
			color: white;
			padding: 10px 16px;
			border: none;
			border-radius: 5px;
			text-decoration: none;
			font-weight: bold;
			transition: background 0.3s;
		}

		.add-button:hover {
			background-color: #218838;
		}

		.alert {
			color: green;
			text-align: center;
			font-weight: bold;
			margin-bottom: 15px;
		}

		table {
			width: 100%;
			border-collapse: collapse;
		}

		th, td {
			padding: 12px 15px;
			text-align: left;
			border-bottom: 1px solid #ddd;
		}

		th {
			background-color: #007bff;
			color: white;
			text-transform: uppercase;
		}

		tr:hover {
			background-color: #f1f1f1;
		}

		.button {
			display: inline-block;
			padding: 6px 12px;
			margin: 2px;
			font-size: 14px;
			color: white;
			border-radius: 4px;
			text-decoration: none;
			transition: background-color 0.3s ease;
		}

		.button.edit {
			background-color: #17a2b8;
		}

		.button.edit:hover {
			background-color: #138496;
		}

		.button.delete {
			background-color: #dc3545;
		}

		.button.delete:hover {
			background-color: #c82333;
		}
	</style>
</head>
<body>

	<div class="container">
		<c:if test="${not empty msg}">
			<div class="alert">${msg}</div>
		</c:if>

		<div class="header-row">
			<h2>Employee List</h2>
			<a href="${pageContext.request.contextPath}/employee/add" class="add-button">+ Add New Employee</a>
		</div>

		<table>
			<thead>
				<tr>
					<th>Emp ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Designation</th>
					<th>Role</th>
					<th>Actions</th>
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
							<a class="button edit" href="${pageContext.request.contextPath}/employee/edit/${employee.empId}">Edit</a>
							<a class="button delete" href="${pageContext.request.contextPath}/employee/delete/${employee.empId}"
							   onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
