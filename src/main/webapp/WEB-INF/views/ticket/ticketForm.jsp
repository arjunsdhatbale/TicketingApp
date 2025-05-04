<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>Ticket Form</title>
</head>
<body>
<style>
    .error {
        color: red;
        font-size: 0.9em;
    }
</style>

<h2>${ticket.ticketId == null ? 'Add New Ticket' : 'Edit Ticket'}</h2>

<form:form method="POST" action="/ticket/save" modelAttribute="ticket" enctype="multipart/form-data">
    <form:hidden path="ticketId" />

    <label>Ticket Name:</label>
    <form:input path="ticketName" required="required" /><br/>
    <form:errors path="ticketName" cssClass="error" /><br/>

    <label>App ID:</label>
    <form:input path="appId" required="required" /><br/>
    <form:errors path="appId" cssClass="error" /><br/>

    <label>Project:</label>
    <form:select path="project">
        <form:options items="${projects}" />
    </form:select><br/>
    <form:errors path="project" cssClass="error" /><br/>

    <label>Priority:</label>
    <form:select path="priority">
        <form:options items="${priorities}" />
    </form:select><br/>
    <form:errors path="priority" cssClass="error" /><br/>

    <label>Status:</label>
    <form:select path="status">
        <form:options items="${status}" />
    </form:select><br/>
    <form:errors path="status" cssClass="error" /><br/>

    <!-- ✅ File Upload Field -->
    <label>Attachment (PDF/JPA):</label>
    <input type="file" name="file" accept=".pdf,.jpa,.txt,.docx,.xlsx" /><br/><br/>

    <!-- ✅ Show existing file name in edit mode -->
    <c:if test="${not empty ticket.attachmentName}">
        <p>Current Attachment: ${ticket.attachmentName}</p>
    </c:if>
    
	<label>Active:</label>
    <form:checkbox path="active"/><br/>
    <form:errors path="active" cssClass="error" /><br/>
    
    <input type="submit" value="${ticket.ticketId == null ? 'Add Ticket' : 'Update Ticket'}" />
</form:form>

<br/>
<a href="/ticket/viewTickets">Back to List</a>
</body>
</html>
