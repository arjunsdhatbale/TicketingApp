<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
    <title>View Tickets</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f6f8; padding: 20px; }
        .top-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px; }
        .add-ticket-btn { padding: 10px 16px; background-color: #1976d2; color: white; text-decoration: none; border-radius: 6px; font-size: 16px; }
        .add-ticket-btn:hover { background-color: #135ba1; }
        form.filter-form { display: flex; gap: 10px; flex-wrap: wrap; margin-bottom: 20px; }
        form.filter-form input, select { padding: 8px; border: 1px solid #ccc; border-radius: 5px; min-width: 150px; }
        table { width: 100%; border-collapse: collapse; background-color: white; box-shadow: 0 0 10px rgba(0, 0, 0, 0.05); }
        th, td { padding: 12px 16px; border: 1px solid #ddd; text-align: left; vertical-align: middle; max-width: 200px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; }
        th { background-color: #1976d2; color: white; font-weight: bold; cursor: pointer; }
        tr:hover { background-color: #f1f1f1; }
        .action-links a { margin-right: 10px; text-decoration: none; color: #1976d2; font-weight: bold; }
        .attachment-cell i { margin-right: 5px; color: #1976d2; }
    </style>
</head>
<body>

<div class="top-bar">
    <h2>List of Tickets</h2>
    <a href="/ticket/add" class="add-ticket-btn"><i class="fas fa-plus"></i> Add New Ticket</a>
</div>

<form class="filter-form" method="get" action="/ticket/viewTickets">
    <input type="number" name="ticketId" value="${ticketId}" placeholder="Ticket ID"/>
    
    <select name="project">
        <option value="">-- Project --</option>
        <c:forEach var="p" items="${projectList}">
            <option value="${p}" ${p == project ? 'selected' : ''}>${p}</option>
        </c:forEach>
    </select>

    <select name="status">
        <option value="">-- Status --</option>
        <c:forEach var="st" items="${statusList}">
            <option value="${st}" ${st == status ? 'selected' : ''}>${st}</option>
        </c:forEach>
    </select>

    <button type="submit">Filter</button>
</form>


<table>
    <thead>
        <tr>
            <th>Ticket ID</th>
            <th>Ticket Name</th>
            <th>App ID</th>
            <th>Project</th>
            <th>Priority</th>
            <th>Status</th>
            <th>Attachment</th>
            <th>Active</th>
            <th>Edit</th>
            <th>Delete</th>
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
                <td>${ticket.status}</td>
                <td class="attachment-cell">
                    <c:if test="${not empty ticket.attachmentName}">
                        <a href="javascript:void(0);" onclick="openModal(${ticket.ticketId}, '${ticket.attachmentName}')">
                            <i class="fas fa-paperclip"></i> ${ticket.attachmentName}
                        </a>
                    </c:if>
                </td>
                <td>${ticket.active ? 'Yes' : 'No'}</td>
                <td class="action-links"><a href="/ticket/edit/${ticket.ticketId}"><i class="fas fa-edit"></i></a></td>
                <td class="action-links"><a href="/ticket/delete/${ticket.ticketId}" onclick="return confirm('Are you sure?')"><i class="fas fa-trash-alt"></i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- Modal HTML -->
<div id="attachmentModal" style="display:none; position:fixed; top:30%; left:50%; transform:translate(-50%, -30%); background:white; border:1px solid #ccc; padding:20px; z-index:999;">
    <p id="attachmentTitle"></p>
    <button id="viewBtn">View</button>
    <button id="downloadBtn">Download</button>
    <button onclick="closeModal()">Cancel</button>
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
        document.getElementById("attachmentModal").style.display = "block";
    }

    function closeModal() {
        document.getElementById("attachmentModal").style.display = "none";
    }
</script>

</body>
</html>
