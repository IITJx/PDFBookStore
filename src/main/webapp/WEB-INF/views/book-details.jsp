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
		<div class="col-md-offset-1 col-md-5">
			<div class="panel panel-default">
				<div class="panel-heading">
					<b class="book-title">${book.getBookName()}</b>
				</div>
				<div class="panel-body">
					<div style="text-align: center;">
						<img src="image?id=<c:url value="${book.getImageId()}"/>"
							height="200" width="240" />
					</div>
					<br />
					<table class="table table-striped">
						<tr>
							<td><label>Author</label></td>
							<td>: ${book.getAuthorName() }</td>
						</tr>
						<tr>
							<td><label>Category</label></td>
							<td>: ${book.getCategory()}</td>
						</tr>
						<tr>
							<td><label>ISBN</label></td>
							<td>: ${book.getISBN()}</td>
						</tr>
						<tr>
							<td><label>Description</label></td>
							<td>: ${book.getDescription().length() == 0 ? "Not available": book.getDescription()}</td>
						</tr>
						<tr>
							<td colspan="2"><a
								href="download?id=<c:url value="${book.getPdfId()}"/>"
								class="btn btn-primary"><span
									class="glyphicon glyphicon-download-alt"></span> Download</a> <a href="add-to-wishlist?id=${book.getBookId()}" class="btn btn-info"><i
									class="fa fa-eye"></i> Add to Wish List</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".active").removeClass();
			$("#books").addClass("active");
		});
	</script>

</body>
</html>