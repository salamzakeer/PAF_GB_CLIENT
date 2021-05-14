

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="model.product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projects Details</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/products.js"></script>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Add New Project</h1>

				<form id="formProduct" name="formProduct">

					Project ID : <input id="projID" name="projID" type="number"
						class="form-control form-control-sm"> <br> 
						
					Project Name: <input id="projName" name="projName" type="text"
						class="form-control form-control-sm"> <br> 
						
					description: <input id="description" name="description" type="text"
						class="form-control form-control-sm"> <br>
						
					Area: <input id="area" name="area" type="text"
						class="form-control form-control-sm"> <br> 
						
					Researcher Name: <input id="resName" name="resName" type="text"
						class="form-control form-control-sm"> <br> 
						
					Researcher ID: <input id="resID" name="resID" type="text"
						class="form-control form-control-sm"> <br> <br>
						
					price: <input id="price" name="price" type="number"
					class="form-control form-control-sm"> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						 <input type="hidden"
						id="hididSave" name="hididSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success">
					
				</div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProductGrid">
				<%
			 	 product Obj = new product();
					out.print(Obj.readProducts());
				%>
				</div>
			</div>
		</div>



	</div>

</body>
</html>