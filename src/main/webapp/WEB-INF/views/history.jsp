<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDFBookStore | History</title>
</head>
<body>
	<div class="container">
		<div class="col-md-11">
		<h1>Download History</h1>
		<hr/>
		<c:if test="${downloadInfo.size()!=0 }">
					<table class="table table-striped" border="1">
						<thead>
							<tr>
								<th>Book Name</th>
								<th>Author</th>
								<th>Download Time</th>
								<th>Action</th>
							</tr>
						</thead>
						<c:forEach var="book" items="${requestScope.downloadInfo }" begin="0" varStatus="count">
							<tr>
								<td>${book[1]}</td>
								<td>${book[2]}</td>
								<td>${book[4]}</td>
								<td><form method="post" action="book-details">
										<input type="hidden" name="id" value="${book[0]}" />
										<input type="submit" value="View" class="btn btn-info"/>
										<a href="download?id=${book[3]}" class="btn btn-primary">Download</a>
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
		</c:if>
		<c:if test="${downloadInfo.size()==0}">
		<h3>Sorry, you have no download history</h3>
		</c:if>
		
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#history").addClass("active");
			$("th,td").css("text-align","center");
		});
		
	</script>
</body>
</html>