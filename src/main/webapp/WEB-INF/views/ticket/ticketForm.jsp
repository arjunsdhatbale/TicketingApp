<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<html>
<head>
    <title>Ticket Form</title>
</head>
<body class="container mt-4">

<c:choose>
    <c:when test="${empty ticket.ticketId}">
        <h2 class="mb-4 text-primary">📝 Add New Ticket</h2>
    </c:when>
    <c:otherwise>
        <h2 class="mb-4 text-warning">✏️ Edit Ticket</h2>
    </c:otherwise>
</c:choose>

<form:form method="POST" action="/ticket/save" modelAttribute="ticket" enctype="multipart/form-data" class="row g-3">
    <form:hidden path="ticketId" />

    <div class="col-md-6">
        <label class="form-label">Ticket Name <span class="text-danger">*</span></label>
        <form:input path="ticketName" cssClass="form-control" placeholder="Enter ticket title"/>
        <form:errors path="ticketName" cssClass="text-danger small" />
    </div>

    <div class="col-md-6">
        <label class="form-label">App ID <span class="text-danger">*</span></label>
        <form:input path="appId" cssClass="form-control" placeholder="Enter App ID"/>
        <form:errors path="appId" cssClass="text-danger small" />
    </div>

    <div class="col-md-6">
        <label class="form-label">Project <span class="text-danger">*</span></label>
        <form:select path="project" cssClass="form-select">
            <form:options items="${projects}" />
        </form:select>
        <form:errors path="project" cssClass="text-danger small" />
    </div>

    <div class="col-md-6">
        <label class="form-label">Priority <span class="text-danger">*</span></label>
        <form:select path="priority" cssClass="form-select">
            <form:options items="${priorities}" />
        </form:select>
        <form:errors path="priority" cssClass="text-danger small" />
    </div>

    <div class="col-12">
        <label class="form-label">Attachment (PDF/JPA/DOCX/XLSX):</label>
        <input type="file" name="file" class="form-control" accept=".pdf,.jpa,.txt,.docx,.xlsx" />
        <c:if test="${not empty ticket.attachmentName}">
            <div class="form-text">📎 Current: <strong>${ticket.attachmentName}</strong></div>
        </c:if>
    </div>

    <div class="col-12 form-check mt-2">
        <form:checkbox path="active" cssClass="form-check-input" id="activeCheck"/>
        <label class="form-check-label" for="activeCheck">Active</label>
        <form:errors path="active" cssClass="text-danger small" />
    </div>

    <div class="col-12 mt-4">
        <button type="submit" class="btn btn-success">
            <c:choose>
                <c:when test="${empty ticket.ticketId}"><i class="fas fa-plus"></i> Add Ticket</c:when>
                <c:otherwise><i class="fas fa-save"></i> Update Ticket</c:otherwise>
            </c:choose>
        </button>
        <a href="/ticket/viewTickets" class="btn btn-secondary ms-2"><i class="fas fa-arrow-left"></i> Back to List</a>
    </div>
</form:form>

<!-- Font Awesome for icons -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/js/all.min.js"></script>
</body>
</html>
