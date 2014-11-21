<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF Store | My Added Books</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="row">
		<div class="container">
			<div class="col-md-9 col-md-offset-1">
				<h1>My Added Books</h1>
				<hr />
				<c:if test="${message!=null}">
					<div class="alert alert-success">
						<a class="close" data-dismiss="alert">&times;</a>${message}</div>
					<%
						session.removeAttribute("message");
					%>
				</c:if>
				<c:if test="${books.size()!=0 }">
					<table class="table table-striped" border="1">
						<thead>
							<tr>
								<th>Book Name</th>
								<th>Author</th>
								<th>Action</th>
							</tr>
						</thead>
						<c:forEach var="book" items="${requestScope.books }">
							<tr>
								<td>${book.getBookName()}</td>
								<td>${book.getAuthorName()}</td>
								<td>
									<form method="post" action="view-book">
										<input type="hidden" name="bookId"
											value="${book.getBookId() }"> <input type="submit"
											value="View" class="btn btn-default">
									</form>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sideBar").show();
			$(".active").removeClass();
			$("#dashBooks").parent().addClass("active");
			$("th,td").css("text-align", "center");
		});
	</script>
</body>
</html>