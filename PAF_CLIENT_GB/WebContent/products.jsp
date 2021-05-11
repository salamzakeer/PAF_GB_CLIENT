
<%

	//Initialize--------------------------------------------
	session.setAttribute("statusMsg", "");
	System.out.println("Trying to process....");

	//Save---------------------------------
	if (request.getParameter("projID") != null) 
	{
		
		product Obj = new product();
		String stsMsg = "";
		
		//Insert--------------------------
		if (request.getParameter("hididSave") == "") {
			//projID, projName, description, area, resName, resID, price
			stsMsg = Obj.insertproductdetails(Integer.parseInt(request.getParameter("projID")), 
								request.getParameter("projName"),
								request.getParameter("description"), 
								request.getParameter("area"), 
								request.getParameter("resName"),
								request.getParameter("resID"),
								Float.parseFloat(request.getParameter("price")));
			
		} else
		//Update----------------------
		{
			
			stsMsg = Obj.updateproductdetails(//request.getParameter("hididSave"), 
					Integer.parseInt(request.getParameter("projID")),
					request.getParameter("projName"),
					request.getParameter("description"),
					request.getParameter("area"),
					request.getParameter("resName"),
					request.getParameter("resID"),
					Float.parseFloat(request.getParameter("price")));
		}
		session.setAttribute("statusMsg", stsMsg);
	}

	//Delete-----------------------------
	if (request.getParameter("hididDelete") != null) {
		
	product Obj = new product();
	
		String stsMsg= Obj.deleteProduct(request.getParameter("projID"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>







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
<script src="Components/product.js"></script>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Add New Project</h1>

				<form id="formProduct" name="formProduct" method="post"
					action="Researchers.jsp">

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
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						
					<input type="hidden" id="hididSave" name="hididSave" value="">


				</form>

				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>

				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<%
			 	 product Obj = new product();
					out.print(Obj.readProducts());
				%>
			</div>
		</div>



	</div>

</body>
</html>