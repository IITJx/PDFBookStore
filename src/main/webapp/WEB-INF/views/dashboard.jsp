<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDFBookStore | Dashboard</title>
</head>
<body>
	<div class="container">
		<div class="col-md-10 col-md-offset-1">
		<h1>Overview</h1>
		<hr/>
		<c:if test="${books.size()!=0 }">
					<table class="table table-striped" border="1">
						<thead>
							<tr>
								<th>Book Name</th>
								<th>Author</th>
								<th>Description</th>
								<th>Total Access</th>
								<th>Total Downloads</th>
							</tr>
						</thead>
						<c:forEach var="book" items="${requestScope.books }" begin="0" varStatus="count">
							<tr>
								<td>${book.getBookName()}</td>
								<td>${book.getAuthorName()}</td>
								<td>${book.getDescription()}</td>
								<td>${accessCounts.get(count.index) }</td>
								<td>${downloadCounts.get(count.index) }</td>
							</tr>
						</c:forEach>
					</table>
		</c:if>
		
		
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sideBar").show();
			$(".active").removeClass();
			$("#dashboard").addClass("active");
			$("#overview").addClass("active");
			$("th,td").css("text-align","center");
		});
		
	</script>
</body>
</html>