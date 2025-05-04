<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>All Users</title>
</head>
<body>
    <h2>List of Users</h2>
    <c:if test="${not empty msg}">
    	<script>
        	alert("${msg}");
    	</script>
	</c:if>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Email Id</th>
            <th>Active</th>
            <th>Edit User</th>
            <th>Delete User</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.email}</td>
                <td>${user.active}</td>
                <td><a href="/users/edit/${user.id}">Edit</a></td>  
                <td><a href="/users/delete/${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="/users/add">Add Another User</a>
    
</body>
</html>
 