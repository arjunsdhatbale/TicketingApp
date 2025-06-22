<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />

<html>
<head>
    <title>View Tickets</title>
    <!-- Font Awesome (optional for icons) -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
</head>
<body class="container mt-4">

<!-- Tabs Navigation -->
<ul class="nav nav-tabs mb-4">
    <li class="nav-item">
        <a class="nav-link ${currentPath == '/progress' ? 'active' : ''}" href="/progress">All Tickets</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${currentPath == '/progress/notAssigned' ? 'active' : ''}" href="/progress/notAssigned">Not Assigned</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${currentPath == '/progress/inProgress' ? 'active' : ''}" href="/progress/inProgress">In Progress</a>
    </li>
    <li class="nav-item">
        <a class="nav-link ${currentPath == '/progress/solved' ? 'active' : ''}" href="/progress/solved">Solved</a>
    </li>
</ul>

<h2 class="mb-3 text-primary">🎫 Ticket List</h2>

<!-- Bootstrap Alert for Message -->
<c:if test="${not empty msg}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>

<!-- Ticket Table -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-primary">
            <tr>
                <th>Ticket ID</th>
                <th>Ticket Name</th>
                <th>App ID</th>
                <th>Project</th>
                <th>Priority</th>
                <th>Assigned To</th>
                <th>Status</th>
                <th>Attachment</th>
                <th>Active</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ticket" items="${tickets}">
                <tr>
                    <td>${ticket.ticketId}</td>
                    <td>${ticket.ticketName}</td>
                    <td>${ticket.appId}</td>
                    <td>${ticket.project}</td>
                    <td>${ticket.priority}</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty ticket.assignedTo}">
                                ${ticket.assignedTo}
                            </c:when>
                            <c:otherwise><span class="text-danger">Not Assigned</span></c:otherwise>
                        </c:choose>
                    </td>
                    <td>${ticket.status}</td>
                    <td>
                        <c:if test="${not empty ticket.attachmentName}">
                            <a href="javascript:void(0);" class="text-decoration-none text-primary"
                               onclick="openModal(${ticket.ticketId}, '${ticket.attachmentName}')">
                                <i class="fas fa-paperclip"></i> ${ticket.attachmentName}
                            </a>
                        </c:if>
                    </td>
                    <td>${ticket.active ? 'Yes' : 'No'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal for Attachment -->
<div class="modal fade" id="attachmentModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Attachment Viewer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p id="attachmentTitle" class="mb-3"></p>
                <button id="viewBtn" class="btn btn-outline-primary me-2">View</button>
                <button id="downloadBtn" class="btn btn-outline-success">Download</button>
            </div>
        </div>
    </div>
</div>

<!-- Scripts -->
<script>
    function openModal(ticketId, fileName) {
        document.getElementById("attachmentTitle").innerText = "Attachment: " + fileName;
        document.getElementById("viewBtn").onclick = function () {
            window.open("/ticket/viewAttachment/" + ticketId, "_blank");
        };
        document.getElementById("downloadBtn").onclick = function () {
            window.location.href = "/ticket/download/" + ticketId;
        };
        let modal = new bootstrap.Modal(document.getElementById('attachmentModal'));
        modal.show();
    }
</script>

</body>
</html>
