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
			<div class="col-md-11">
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

				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
				  <!-- Indicators -->
				  <ol class="carousel-indicators">
				    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
				    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
				  </ol>

				  <!-- Wrapper for slides -->
				  <div class="carousel-inner">
				    <div class="item active">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				      	<i class="fa fa-book fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Collection of Wide Range of Books</h3><br>
						<a href="books" class="btn btn-lg btn-info">Go to Books <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
				<c:choose>
				<c:when test="${ session!=null && user.getType()=='Publisher'}">
					<div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				        <i class="fa fa-list fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Add Books to Store</h3><br>
						<a href="add-book" class="btn btn-lg btn-success">Add Now <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
					<div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				        <i class="fa fa-pie-chart fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Business Intelligence</h3><br>
						<a href="dashboard" class="btn btn-lg btn-primary">View Dashboard <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
					</c:when>
					<c:when test="${session!=null && user.getType()=='User'}">
					<div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				      	<i class="fa fa-download fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Free Downloadable Books</h3><br>
						<a href="books" class="btn btn-lg btn-success">View Books <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
					<div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				      <i class="fa fa-history fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Book Download History</h3><br>
						<a href="history" class="btn btn-lg btn-primary">View History <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
					</c:when>
					
					<c:otherwise>
				    <div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				      <i class="fa fa-download fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Free Downloadable Books</h3><br>
						<a href="register" class="btn btn-lg btn-success">Register Now <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
				    <div class="item">
				      <img src="resources/images/bg.png" alt="...">
				      <div class="carousel-caption">
				      	<i class="fa fa-pie-chart fa-5x"></i><br>
				        <h1>PDF Book Store</h1>
						<h3>Business Intelligence</h3><br>
						<a href="login" class="btn btn-lg btn-primary">Login Now <i class="fa fa-angle-double-right"></i></a>
						<br>
						<br>
				      </div>
				    </div>
					</c:otherwise>
				    </c:choose>
				  </div>
				  <!-- Controls -->
				  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left"></span>
				  </a>
				  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right"></span>
				  </a>
				  
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#home").addClass("active");
			$("img").css({
				height: 400,
				width: 1050
			});
			$(".carousel").carousel({
			  interval: 3000
			});
		});
	</script>
</body>
</html>