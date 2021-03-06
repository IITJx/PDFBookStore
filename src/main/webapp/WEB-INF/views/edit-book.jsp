<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Edit Book</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<h1>Edit Book</h1>
				<hr />
				<c:if test="${message!=null}">
					<div class="alert alert-success">
						<a class="close" data-dismiss="alert">&times;</a>${message}</div>
				</c:if>
				<c:if test="${errorMessage!=null}">
					<div class="alert alert-danger">
						<a class="close" data-dismiss="alert">&times;</a>${errorMessage}</div>
				</c:if>
				<form method="post" class="form-horizontal well">
					<input type="hidden" value="${book.getBookId() }" name="id">
					<div class="form-group">
						<label for="bookName" class="control-label col-md-2">Book
							Name</label>
						<div class="col-md-3">
							<td><input type="text" name="bookName" class="form-control"
								placeholder="Enter Book Name..." required="true"
								value="${book.getBookName() }" /></td>
						</div>
					</div>
					<div class="form-group">
						<label for="author" class="control-label col-md-2">Author</label>
						<div class="col-md-3">
							<input type="text" name="author" class="form-control"
								placeholder="Enter Author Name..." required="true"
								value="${book.getAuthorName() }" />

						</div>
					</div>
					<div class="form-group">
						<label for="ISBN" class="control-label col-md-2">ISBN</label>
						<div class="col-md-3">
							<input type="text" name="ISBN" placeholder="Enter ISBN..."
								class="form-control" value="${book.getISBN() }" />
						</div>
					</div>
					<div class="form-group">
						<label for="category" class="control-label col-md-2">Category</label>
						<div class="col-md-3">
							<select name="category" class="form-control">
								<option>Novel</option>
								<option>Poetry</option>
								<option>Travel</option>
								<option>Mystery</option>
								<option>Romance</option>
								<option>Religious</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="description" class="control-label col-md-2">Description</label>
						<div class="col-md-6">
							<textarea class="form-control" rows="3" name="description"
								placeholder="Enter Book Description...">${book.getDescription() }</textarea>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-offset-2 col-md-4">
							<button class="btn btn-primary" type="submit">Change</button>
							<button class="btn btn-default" type="reset">Clear</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sideBar").show();
			$(".active").removeClass();
			$("#dashboard").addClass("active");
			$("#dashBooks").parent().addClass("active");
		});
	</script>
</body>
</html>