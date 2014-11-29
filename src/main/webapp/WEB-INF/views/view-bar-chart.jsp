<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>PDFBookStore | View Bar Chart</title>
</head>
<body>
	<br/>
	<div class="container">
		<div class="row">
			<div class="col-md-10 col-md-offset-1">
				<div class="col-md-1">
				</div>
				<div class="col-md-8">
					<img src="barchart?chart=access" alt="Bar Chart" id="chartImage"/><br/><br/>
					<div class="col-md-offset-3">
						<label>Bar Chart &nbsp;&nbsp;</label> 
						<label class="radio-inline"><input type="radio" name="type" value="access" checked="checked">Access Count</label> 
						<label class="radio-inline"><input type="radio" name="type" value="download"> Download Count</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sideBar").show();
			$("input:radio[name=type]").click(function() {
				var value = $(this).val();
	            $('#chartImage').attr('src', "barchart?chart="+value);
	        });
		});
	</script>
</body>
</html>