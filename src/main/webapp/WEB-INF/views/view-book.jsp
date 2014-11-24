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
	<br>
	<div class="row">
		<div class="container">
			<c:if test="${book!=null }">
				<div class="col-md-5 col-md-offset-3">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h1>${book.getBookName()}</h1>
						</div>
						<div class="panel-body">
							<table class="table table-striped">
								<tr>
									<td>Author :</td>
									<td>${book.getAuthorName() }</td>
								</tr>
								<tr>
									<td>Category :</td>
									<td>${book.getCategory() }</td>
								</tr>
								<tr>
									<td>Description :</td>
									<td>${book.getDescription() }</td>
								</tr>
								<tr>
									<td>Access Count</td>
									<td>${accessCount }</td>
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
		});
	</script>
</body>
</html>