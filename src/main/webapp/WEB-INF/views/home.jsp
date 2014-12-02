<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PDFBookStore | Home</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<br> <br>
				<c:if test="${loggedUser!=null}">
					<div class="alert alert-success message">
						<a class="close" data-dismiss="alert">&times;</a>Welcome, <b>${loggedUser}</b>
					</div>
				</c:if>

				<c:if test="${logoutMessage!=null}">
					<div class="alert alert-success message">
						<a class="close" data-dismiss="alert">&times;</a>${logoutMessage}
						<%
							request.getSession().removeAttribute("logoutMessage");
						%>
					</div>
					<script type="text/javascript">
						$(document).ready(function() {
							setTimeout(function() {
								location.reload()
							}, 3000);
						});
					</script>
				</c:if>
				<c:if test="${registrationSuccess!=null}">
					<div class="alert alert-success message">
						<a class="close" data-dismiss="alert">&times;</a>${registrationSuccess}
					</div>
				</c:if>

				<div class="jumbotron"
					style="background-image: url('<c:url value="/resources/images/TitlePage.jpg"/>');">
					<h1>PDF Book Store</h1>
					<p>Habit of reading is a great asset...</p>
					<p>
						<a class="btn btn-success btn-lg" href="books">Go to Books <i
							class="fa fa-angle-double-right"></i></a>
					</p>
					<br /> <br /> <br />
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".active").removeClass();
			$("#home").addClass("active");
		});
	</script>
</body>
</html>