<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Edit User</title>
</head>
<body>
    <h2>Edit User</h2>
    <form:form method="POST" action="/users/update" modelAttribute="user">
        <form:hidden path="id"/>
        
        <label>User Name:</label>
        <form:input path="userName" required="required"/><br/>

        <label>Password:</label>
        <form:input path="password" required="required"/><br/>

        <label>Email:</label>
        <form:input path="email" required="required"/><br/>

        <label>Active:</label>
        <form:checkbox path="active"/><br/>

        <input type="submit" value="Update User"/>
    </form:form>
    </br>
    <a href="/users/view">Back to UserList</a>
</body>
</html>
