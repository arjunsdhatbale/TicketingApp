<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head><title>Login</title></head>
<body>
    <h2>Login Page</h2>
    <form method="post" action="/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <label>Username:</label>
        <input type="text" name="username" /><br/>
        <label>Password:</label>
        <input type="password" name="password" /><br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
