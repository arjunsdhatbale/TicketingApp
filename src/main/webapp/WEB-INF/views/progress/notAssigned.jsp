<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<html>
<head>
<title>View Tickets</title>
</head>
<body>
	<div>
		 
		<a href="/ticketStatus/inProgress" style="margin-right: 15px;">InProgress</a> 
		<a href="/ticketStatus/solved">Solved</a>
	</div>

	<br />
	<h2>List of Not Assigned Tickets</h2>
	

	<c:if test="${not empty msg}">
		<script>
			alert('${msg}');
		</script>
	</c:if>
	<div id="attachmentModal"
		style="display: none; position: fixed; top: 30%; left: 35%; background: white; border: 1px solid #ccc; padding: 20px; z-index: 999;">
		<p id="attachmentTitle"></p>
		<button id="viewBtn">View Attachment</button>
		<button id="downloadBtn">Download Attachment</button>
		<button onclick="closeModal()">Cancel</button>
	</div>
	<script>
		function openModal(ticketId, fileName) {
			document.getElementById("attachmentTitle").innerText = "Attachment: "
					+ fileName;
			document.getElementById("viewBtn").onclick = function() {
				window.open("/ticket/viewAttachment/" + ticketId, "_blank");
			};
			document.getElementById("downloadBtn").onclick = function() {
				window.location.href = "/ticket/download/" + ticketId;
			};
			document.getElementById("attachmentModal").style.display = "block";
		}

		function closeModal() {
			document.getElementById("attachmentModal").style.display = "none";
		}
	</script>
	<table border="1" cellpadding="8" cellspacing="0">
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
					<td><c:if test="${not empty ticket.attachmentName}">
							<a href="javascript:void(0);"
								onclick="openModal(${ticket.ticketId}, '${ticket.attachmentName}')">
								${ticket.attachmentName} </a>
						</c:if></td>
					<td>${ticket.active ? 'Yes' : 'No'}</td>
					 
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
