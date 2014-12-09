<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | ${book.getBookName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<br>
	<br>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<c:if test="${book!=null }">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h1>Book Details</h1>
				</div>
				<div class="panel-body">
					<img src="image?id=<c:url value="${book.getImageId()}"/>"
						height="250px" width="300px" class="col-md-5 pull-right" />
					<div class="col-md-7">
						<table class="table table-striped">
							<tr>
								<th>Book Name</th>
								<td>: ${book.getBookName()}</td>
							<tr />
							<tr>
								<th>Author</th>
								<td>: ${book.getAuthorName() }</td>
							<tr />
							<tr>
								<th>ISBN No</th>
								<td>: ${book.getISBN()}</td>
							<tr />
							<tr>
								<th>Category</th>
								<td>: ${book.getCategory()}</td>
							<tr />
							<tr>
								<th>Description</th>
								<td>: ${book.getDescription().length() == 0 ? "Not available": book.getDescription()}</td>
							<tr />
							<tr>
								<th>Access Count</th>
								<td>: ${accessCount }</td>
							</tr>
							<tr>
								<th>Download Count</th>
								<td>: ${downloadCount }</td>
							</tr>
						</table>
						<div class="pull-right">
								<form method="post" action="delete-book">
									<a class="btn btn-primary"
									href="edit-book?id=${book.getBookId() }"><i class="fa fa-edit"></i> Edit</a>
									<input type="hidden" name="bookId" value="${book.getBookId() }" />
									<button type="submit" class="btn btn-danger"
										onclick="return confirm('Are you sure to delete book ${book.getBookName()}?')">
										<i class="fa fa-trash"></i> Delete
									</button>
								</form>
						</div>
					</div>
				</div>
			</div>
			</c:if>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sideBar").show();
			$(".active").removeClass();
			$("#dashBooks").parent().addClass("active");
			$("#dashboard").addClass("active");
		});
	</script>
</body>
</html>