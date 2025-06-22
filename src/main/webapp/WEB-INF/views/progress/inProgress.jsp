<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>In Progress Tickets</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-4">

<!-- Nav Tabs -->
<ul class="nav nav-tabs mb-4">
    <li class="nav-item"><a class="nav-link ${currentPath == '/progress' ? 'active' : ''}" href="/progress">View All</a></li>
    <li class="nav-item"><a class="nav-link ${currentPath == '/progress/notAssigned' ? 'active' : ''}" href="/progress/notAssigned">Not Assigned</a></li>
    <li class="nav-item"><a class="nav-link ${currentPath == '/progress/inProgress' ? 'active' : ''}" href="/progress/inProgress">In Progress</a></li>
    <li class="nav-item"><a class="nav-link ${currentPath == '/progress/solved' ? 'active' : ''}" href="/progress/solved">Solved</a></li>
</ul>

<h2 class="text-warning mb-3">🔄 In Progress Tickets</h2>

<!-- Toast Message -->
<c:if test="${not empty msg}">
    <div class="alert alert-info alert-dismissible fade show" role="alert">
        ${msg}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
</c:if>

<!-- Modal for Attachment -->
<div class="modal fade" id="attachmentModal" tabindex="-1" aria-labelledby="attachmentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Attachment Viewer</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <p id="attachmentTitle"></p>
                <button id="viewBtn" class="btn btn-outline-primary me-2">View</button>
                <button id="downloadBtn" class="btn btn-outline-success">Download</button>
            </div>
        </div>
    </div>
</div>

<script>
    function openModal(ticketId, fileName) {
        document.getElementById("attachmentTitle").innerText = "Attachment: " + fileName;
        document.getElementById("viewBtn").onclick = function () {
            window.open("/ticket/viewAttachment/" + ticketId, "_blank");
        };
        document.getElementById("downloadBtn").onclick = function () {
            window.location.href = "/ticket/download/" + ticketId;
        };
        new bootstrap.Modal(document.getElementById("attachmentModal")).show();
    }
</script>

<!-- Ticket Table -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-warning">
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
                            <c:otherwise>
                                <span class="text-danger">Not Assigned</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div class="d-flex align-items-center">
                            <!-- Current Status Badge -->
                            <span class="badge bg-warning text-dark me-2">${ticket.status}</span>

                            <!-- Edit dropdown trigger -->
                            <div class="dropdown">
                                <button class="btn btn-sm btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                                    ✏️
                                </button>
                                <div class="dropdown-menu p-3" style="min-width: 220px;">
                                    <form method="post" action="/progress/updateStatus">
                                        <input type="hidden" name="ticketId" value="${ticket.ticketId}" />

                                        <label for="status-${ticket.ticketId}" class="form-label mb-1">Update Status</label>
                                        <select name="status" id="status-${ticket.ticketId}" class="form-select form-select-sm mb-2" required>
                                            <c:forEach items="${statuses}" var="status">
                                                <option value="${status}" ${ticket.status == status ? 'selected' : ''}>${status}</option>
                                            </c:forEach>
                                        </select>
                                        <button type="submit" class="btn btn-sm btn-primary w-100">Update</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </td>

                    <td>
                        <c:if test="${not empty ticket.attachmentName}">
                            <a href="javascript:void(0);" class="text-decoration-none text-primary"
                               onclick="openModal(${ticket.ticketId}, '${ticket.attachmentName}')">
                                📎 ${ticket.attachmentName}
                            </a>
                        </c:if>
                    </td>
                    <td>${ticket.active ? 'Yes' : 'No'}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
