<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><decorator:title /></title>

<!-- All JS here 
=====================================-->

<script type="text/javascript"
	src="<c:url value="/resources/scripts/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/jquery-ui.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/popover.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/bootstrap.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/scripts/tooltip.js"/>"></script>

<!-- All CSS here 
=====================================-->

<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/css/bootstrap.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/dashboard.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui.theme.min.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui.min.css"/>" />
<!--<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap/css/bootstrap-theme.css"/>" />-->
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome/css/font-awesome.css"/>" />
<style type="text/css">
body {
	padding-bottom: 40px;
	margin-right: 10%;
	margin-left: 10%;
}

.navbar-brand {
	font-size: 25px;
}

.sidebar {
	height: 500px;
	margin-top: 30px;
	margin-left: 10px;
	width: 200px;
	background-color: #EFEFEF;
}

.error {
	color: red;
	display: block;
}
</style>
</head>
<body>
	<!-- Navbar Top
	 =====================================================-->
	<div class="navbar navbar-inverse navbar-fixed-top"
		style="">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="home" style="color: white;">BookStore</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li id="home"><a href="home">Home</a></li>
					<li id="books"><a href="books">Books</a></li>
					<c:if test="${user.getType()=='Publisher'}">
						<li id="dashboard"><a href="dashboard">Dashboard</a></li>
					</c:if>
					<c:if test="${session!=null }">
						<li id="history"><a href="history">History</a></li>
					</c:if>
					<li id="contact"><a href="contact">Contact</a></li>
					<li id="help"><a href="help">Help</a></li>
				</ul>
				<c:if test="${session==null}">
					<div class="pull-right">
						<div class="navbar-form">
							<a href="login" class="btn btn-primary">Login</a> <a
								href="register" class="btn btn-success">Register</a>
						</div>
					</div>
				</c:if>
				<c:if test="${session!=null}">
					<div class="navbar-form pull-right">
						<a href="wishlist" class="btn btn-success">Wish List <span class="badge">${wishBooks.size()}</span></a>
						&nbsp;&nbsp;&nbsp;
						<a href="#" class="dropdown-toggle btn btn-primary"
							data-toggle="dropdown"> ${user.getUserName()} <b
							class="caret"></b>
						</a>

						<ul class="dropdown-menu">
							<li><a href="edit-account"><span
									class="glyphicon glyphicon-edit"></span> Edit Profile</a></li>
							<li><a href="logout"><span
									class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
						</ul>

					</div>
				</c:if>
			</div>
		</div>
	</div>
	<c:if test="${ session==null}">
		<br />
		<br />
		<br />
	</c:if>
	<c:if test="${ session==true && user.getType()=='User'}">
		<br />
		<br />
		<br />
	</c:if>
	<!-- Side bar left
	 =====================================================-->

	<c:if test="${session==true && user.getType()=='Publisher'}">
		<div class="sidebar" id="sideBar">
			<ul class="nav nav-sidebar">
				<li id="overview"><a href="dashboard">Overview</a></li>
				<li><a href="#" data-toggle="collapse" data-target="#bookMenu"
					id="dashBooks">Books &nbsp;<span
						class="fa fa-angle-double-right" id="dashDropdown"></span></a>
					<ul class="nav collapse" id="bookMenu">
						<li><a href="add-book">&nbsp;&nbsp;&nbsp;<i
								class="fa fa-plus-square"></i> Add New Book
						</a></li>
						<li><a href="added-books">&nbsp;&nbsp;&nbsp;<i
								class="fa fa-list"></i> My Added Books
						</a></li>
					</ul></li>
				<li id="reports"><a href="#" data-toggle="collapse"
					data-target="#reportMenu" id="dashReport">Reports &nbsp;<i
						class="fa fa-angle-double-right" id="reportDropdown"></i></a>
					<ul class="nav collapse" id="reportMenu">
						<li><a href="view-bar-chart">&nbsp;&nbsp;&nbsp;<i
								class="fa fa-bar-chart"></i> Bar Charts
						</a></li>
						<li><a href="view-pie-chart">&nbsp;&nbsp;&nbsp;<i
								class="fa fa-pie-chart"></i> Pie Charts
						</a></li>
					</ul></li>
			</ul>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#sideBar").hide();
			});
		</script>
	</c:if>
	<!-- Sidebar ends here -->
	<decorator:body />

	<div class="col-md-12 navbar-inverse navbar-fixed-bottom" style="padding-top:10px; padding-bottom:10px; color: white;">
		<div class="row" align="center">
		&copy; All Rights Reserved by <b>IIT Jx</b>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#dashBooks")
									.click(
											function() {

												$("#dashDropdown")
														.toggleClass(
																'fa fa-angle-double-right fa fa-angle-double-up');
											});
							$("#dashReport")
									.click(
											function() {

												$("#reportDropdown")
														.toggleClass(
																'fa fa-angle-double-right fa fa-angle-double-up');
											});
						});
	</script>
</body>
</html>