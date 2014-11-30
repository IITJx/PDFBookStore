<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF Store | Wish List</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="row">
		<div class="container">
			<div class="col-md-9">
				<h1>Wish List</h1>
				<hr />
			<c:choose>
				<c:when test="${wishBooks.size()!=0 }">
					<table class="table table-striped" border="1">
						<thead>
							<tr>
								<th>Book Name</th>
								<th>Author</th>
								<th>Action</th>
							</tr>
						</thead>
						<c:forEach var="book" items="${wishBooks}">
							<tr>
								<td>${book.getBookName()}</td>
								<td>${book.getAuthorName()}</td>
								<td><form method="post" action="book-details">
										<input type="hidden" name="id" value="${book.getBookId()}" />
										<input type="submit" value="Details" class="btn btn-info"/>
										<a class="btn btn-primary"href="download?id=${book.getPdfId()}">Download</a>
										<a class="btn btn-danger" href="delete-wishlist-book?id=${book.getBookId()}">Remove</a>
									</form> 
									
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h3>
						Sorry, you have no books in wish list.
					</h3>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("th,td").css("text-align", "center");
		});
	</script>
</body>
</html>