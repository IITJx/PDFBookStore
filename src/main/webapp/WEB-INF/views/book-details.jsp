<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDF Store| ${book.getBookName()}</title>
<style>
td {
	font-size: 16px;
}
</style>
</head>
<body>
	<br />
	<br />
	<div class="row">
		<div class="col-md-offset-2 col-md-7">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3>${book.getBookName()}</h3>
				</div>
				<div class="panel-body">
					<div style="text-align: center;">
						<img src="image?id=<c:url value="${book.getImageId()}"/>"
							height="240" width="280" />
					</div>
					<br />
					<table class="table table-striped">
						<tr>
							<td><label>Author</label></td>
							<td>: ${book.getAuthorName() }</td>
						</tr>
						<tr>
							<td><label>ISBN</label></td>
							<td>: ${book.getISBN()}</td>
						</tr>
						<tr>
							<td><label>Description</label></td>
							<td>: ${book.getDescription()}</td>
						</tr>
						<tr>
							<td colspan="2"><a
								href="download?id=<c:url value="${book.getPdfId()}"/>"
								class="btn btn-primary"><span
									class="glyphicon glyphicon-download-alt"></span> Download</a> <a href="#" class="btn btn-warning"><i
									class="fa fa-share"></i> Share</a></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>


</body>
</html>