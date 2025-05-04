<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h2>Add New User</h2>
    <form:form method="POST" action="/users/save" modelAttribute="user">
        <label>User Name:</label>
        <form:input path="userName" required="required"/><br/>
        <label>Password:</label>
        <form:password path="password" required="required"/><br/>
        <label>Email:</label>
        <form:input path="email" required="required"/><br/>
        <label>Active:</label>
        <form:input path="active" required="required"/><br/>
    
        <input type="submit" value="Add User"/>
    </form:form>
    </br>
    <a href="/users/view">Back to UserList</a>
</body>
</html>
