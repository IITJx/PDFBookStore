<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDFBookStore | User Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h1>User Login</h1>
				<hr />
				<c:if test="${errorMessage!=null}">
					<div class="alert alert-info">
						<a class="close" data-dismiss="alert">&times;</a>${errorMessage }
					</div>
				</c:if>
				<form action="" method="post" class="form-horizontal well">
					<div class="form-group">
						<label for="username" class="control-label col-md-2">User
							Name</label>
						<div class="col-md-3">
							<input type="text" name="username" class="form-control"
								required="true" placeholder="Enter User Name..." />
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="control-label col-md-2">Password</label>
						<div class="col-md-3">
							<input type="password" name="password" class="form-control"
								required="true" placeholder="Enter Password..." />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-offset-2 col-md-4">
							<button class="btn btn-primary" type="submit">Submit</button>
							<button class="btn btn-default" type="reset">Clear</button>
						</div>
					</div>
					<div class="col-md-offset-2">Don't have an account? <a href="register">Register Here!</a></div>
					<c:if test="${message!=null}">
						<br/>
						<div class="alert alert-warning">
							<a href="#" class="close" data-dismiss="alert">&times;</a>
							<p class="error" style="color: RED">${message}</p>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>