<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Help</title>
</head>
<body>
	<div class="row">
		<h1>Help</h1>
		<hr />
		<div class="col-md-10">
			<p style="font-size: 16px">
				User types and their corresponding facilities are listed below:
			</p>
			<table class="table table-striped" border="2">
				<tr>
					<th>Facility</th>
					<th>Visitor</th>
					<th>Registered as User</th>
					<th>Registered as Publisher</th>
				</tr>
				<tr>
					<td><b>View Books and Details</b></td>
					<td><i class="fa fa-check"></i></td>
					<td><i class="fa fa-check"></i></td>
					<td><i class="fa fa-check"></i></td>
				</tr>
				<tr>
					<td><b>Download Books</b></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-check"></i></td>
					<td><i class="fa fa-check"></i></td>
				</tr>
				<tr>
					<td><b>Download History</b></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-check"></i></td>
					<td><i class="fa fa-check"></i></td>
				</tr>
				<tr>
					<td><b>Upload Books</b></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-check"></i></td>
				</tr>
				<tr>
					<td><b>Books Statistics</b></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-close"></i></td>
					<td><i class="fa fa-check"></i></td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#help").addClass("active");
			$("th,td").css("text-align", "center");
			$(".fa-close").css("color", "red");
			$(".fa-check").css("color", "green");
		});
	</script>
</body>
</html>