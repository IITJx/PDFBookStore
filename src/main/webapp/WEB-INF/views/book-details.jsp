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
		<div class="col-md-8">
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
		<c:if test="${suggestedBook!=null}">
		<div class="col-md-3">
		<div style="text-align:center; background-color: #DD4814; font-size:14px; color:white; padding: 5px;">
		<b>Similar category books...</b>
		</div>
			<div class="thumbnail">
				<img id="img" src="image?id=<c:url value="${suggestedBook.getImageId()}"/>"
							alt="Book Cover"/>
						<div class="caption">
							<b class="img-heading">${suggestedBook.getBookName()}</b>
							<p>
								<b>Author</b>: ${suggestedBook.getAuthorName()}<br /> <b>Category</b>:
								${suggestedBook.getCategory()}
							</p>
							<form method="post" action="book-details">
								<input type="hidden" name="id" value="${suggestedBook.getBookId()}" />
								<button type="submit" class="btn btn-primary">
									<span class="fa fa-expand"></span> Details
								</button>
								<a href="#" class="btn btn-warning"><span
									class="fa fa-share"></span> Share</a>
					</form>
				</div>
			</div>
		</div>
		</c:if>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#img").css("height","210px");
			$("#books").addClass("active");
		});
	</script>

</body>
</html>