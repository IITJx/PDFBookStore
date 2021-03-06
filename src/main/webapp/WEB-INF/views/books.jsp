<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Books</title>
</head>

<body>
	<div class="row">
		<div class="btn-group-vertical pull-left col-md-2" style="padding-top:80px; margin-left:-40px;">
	      <button type="button" class="btn btn-default disabled ">Category</button>
	      <a href="books?type=Novel" class="btn btn-default" style="border-top: 1px solid white;">Novel</a>
	      <a href="books?type=Religious" class="btn btn-default" style="border-top: 1px solid white;">Religious</a>
	      <a href="books?type=Magazine" class="btn btn-default" style="border-top: 1px solid white;">Magazine</a>
	      <a href="books?type=Poetry" class="btn btn-default" style="border-top: 1px solid white;">Poetry</a>
	      <a href="books?type=Biography" class="btn btn-default" style="border-top: 1px solid white;">Biography</a>
	      <a href="books?type=Text Book" class="btn btn-default" style="border-top: 1px solid white;">Text Book</a>
    	</div>
	<div class="col-md-9">
		<h1>Books</h1>
		<hr />
			<c:forEach var="book" items="${requestScope.books }">
				<div class="col-md-4">
					<div class="thumbnail">
						<img src="image?id=<c:url value="${book.getImageId()}"/>"
							alt="Book Cover"/>
						<div class="caption">
							<b class="img-heading">${book.getBookName()}</b>
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
	<div class="col-md-10 col-md-offset-2">
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
	<script type="text/javascript">
	$(document).ready(function(){
		$(".active").removeClass();
		$("#books").addClass("active");
		$("img").css({
			height: 160,
			width: 220
		});
	});
	</script>
</body>
</html>