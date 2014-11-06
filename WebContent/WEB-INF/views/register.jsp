<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDFBookStore | Register</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h1>User Registration</h1>
				<hr />
				<form action="" method="post" class="form-horizontal well">
					<div class="form-group">
						<label for="username" class="control-label col-md-3">User
							Name</label>
						<div class="col-md-4">
							<input type="text" name="username" class="form-control"
								required="true" placeholder="at least 6 characters..." />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="control-label col-md-3">Password</label>
						<div class="col-md-4">
							<input type="password" name="password" class="form-control"
								required="true" placeholder="at least 6 characters..." />
						</div>
					</div>
					<div class="form-group">
						<label for="confirm-password" class="control-label col-md-3">Confirm Password</label>
						<div class="col-md-4">
							<input type="password" name="confirm-password" class="form-control"
								required="true" placeholder="at least 6 characters..." />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Account Type</label>
						<div class="col-md-4">
							<select class="form-control" name="type">
								<option>Publisher</option>
								<option>User</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-3 col-md-4">
							<button class="btn btn-primary" type="submit">Submit</button>
							<button class="btn btn-default" type="reset">Clear</button>
						</div>
					</div>

					<c:if test="${message!=null}">
						<div class="alert alert-danger message">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<p> ${message}</p>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>