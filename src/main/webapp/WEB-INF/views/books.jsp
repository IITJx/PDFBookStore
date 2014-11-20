<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Books</title>
<style type="text/css">
img {
	height: 300px;
	width: 300px;
}
</style>
</head>

<body>
	<br>

	<div class="container">
		<div class="col-md-11">
			<h1>Books</h1>
			<hr />
		</div>
		<div class="row col-md-9">
			<c:forEach var="book" items="${requestScope.books }">
				<div class="col-md-4">
					<div class="thumbnail">
						<img src="image?id=<c:url value="${book.getImageId()}"/>"
							alt="Book Cover" />
						<div class="caption">
							<h3 class="img-heading">${book.getBookName()}</h3>
							<p>
								<b>Author</b>: ${book.getAuthorName()}<br /> <b>Category</b>:
								${book.getCategory()}
							</p>
							<form method="post" action="book-details">
								<input type="hidden" name="id" value="${book.getBookId()}" />
								<button type="submit" class="btn btn-primary">
									<span class="fa fa-expand"></span> Details
								</button>
								<a href="#" class="btn btn-warning"><span
									class="fa fa-share"></span> Share</a>
							</form>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row col-md-10">
			<ul class="pager">
				<c:if test="${page > 1 }">
					<li class="previous"><a href="books?page=${page-1}"
						style="border-color: #50A6E5">&larr; Previous</a></li>
				</c:if>
				<c:if test="${page < totalPages}">
					<li class="next"><a href="books?page=${page+1}"
						style="border-color: #50A6E5">Next &rarr;</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>