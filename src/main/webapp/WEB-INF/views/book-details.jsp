<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore| ${book.getBookName()}</title>
<style>
td {
	font-size: 14px;
}

.book-title {
	font-size: 16px;
}
</style>
</head>
<body>
	<br />
	<div class="row">
		<div class="col-md-offset-1 col-md-8">
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
								<td colspan="2"><a
									href="download?id=<c:url value="${book.getPdfId()}"/>"
									class="btn btn-primary"><span
										class="glyphicon glyphicon-download-alt"></span> Download</a> <a
									href="add-to-wishlist?id=${book.getBookId()}"
									class="btn btn-info"><i class="fa fa-eye"></i> Add to Wish
										List</a></td>
							</tr>
						</table>

					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#books").addClass("active");
		});
	</script>

</body>
</html>