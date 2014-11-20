<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>

<body>
	<br>
	<br>
	<div class="container">
		<div class="col-md-10">
			<c:choose>
				<c:when test="${statusCode == 404}">
					<h2>Sorry, the page you requested was not found.</h2>
					<br />
					<br />
					<a href="<c:url value="home"/>">Go Back</a>
				</c:when>
				<c:otherwise>
					<h2>There has been an error in processing your request.</h2>
					<br />
					<br />
					<a href="<c:url value="home"/>">Go Back </a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>