<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Contact</title>
</head>
<body>
	<div class="row">
		<h1>Contact</h1><hr/>
		
		<c:if test="${success!=null }">
			<div class="alert alert-success">
						<a class="close" data-dismiss="alert">&times;</a>${success}</div>
			<script type="text/javascript">
				$(document).ready(function(){
					$("#form").hide();
				});
			</script>
		</c:if>
		
		<div id="form" class="col-md-10">
		<div style="font-size: 16px; padding-bottom: 12px;">
				If you have any suggestion, book request or complain; please let us know.
			</div>
			<form method="post" class="form-horizontal well">
				<div class="form-group">
					<label for="bookName" class="control-label col-md-2">Email</label>
							<div class="col-md-4">
								<td><input type="email" name="email" class="form-control"
									placeholder="Enter Your Email..." required="true" /></td>
							</div>
				</div>
				<div class="form-group">
					<label for="bookName" class="control-label col-md-2">Comment</label>
							<div class="col-md-6">
								<textarea rows="4" class="form-control" required></textarea>
							</div>
				</div>
				<div class="form-group">
						<div class="col-md-offset-2 col-md-4">
							<button class="btn btn-primary" type="submit">Submit</button>
							<button class="btn btn-default" type="reset">Clear</button>
						</div>
				</div>
				
			</form>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#contact").addClass("active");
	});
	</script>
</body>
</html>