$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();

});

$(document).on("click", "#btnSave", function(event) {

	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide();

	 
	// Form validation-------------------
	 var status = validateProductForm(); 
	 if (status != true) 
	  { 
	  $("#alertError").text(status); 
	  $("#alertError").show(); 
	  return; 
	  }
	 
	// If valid-------------------------
	
		var type = ($("#hididSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "productAPI",
		type : type,
		data : $("#formProduct").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onProductSaveComplete(response.responseText, status);
		}
	});

});
function onProductSaveComplete(response, status) {
	if (status == "success") {

		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Product details successfully saved.");
			$("#alertSuccess").show();

			$("#divProductGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hididSave").val("");
	$("#formProduct")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hididSave").val($(this).data("projID")); 
  //projID, projName, description, area, resName, resID, price
 $("#projID").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#projName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#description").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#area").val($(this).closest("tr").find('td:eq(3)').text());
 $("#resName").val($(this).closest("tr").find('td:eq(4)').text());
  $("#resID").val($(this).closest("tr").find('td:eq(5)').text());
$("#price").val($(this).closest("tr").find('td:eq(6)').price());
//$("#price").val(parseFloat(tmpPrice).toFixed(2));

});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "productAPI",
		type : "DELETE",
		data : "projID=" + $(this).data("projID"),
		dataType : "text",
		complete : function(response, status) {
			onProductDeleteComplete(response.responseText, status);
		}
	});
});

function onProductDeleteComplete(response, status)
{ 
	if (status == "success") 
 { 
			var resultSet = JSON.parse(response); 
			if (resultSet.status.trim() == "success") 
			{ 
				$("#alertSuccess").text("Product details successfully deleted."); 
				$("#alertSuccess").show(); 
				
				$("#divProductGrid").html(resultSet.data); 
			} else if (resultSet.status.trim() == "error") 
			{ 
				$("#alertError").text(resultSet.data); 
				$("#alertError").show(); 
			} 
 	} else if (status == "error") 
 	{ 
 		$("#alertError").text("Error while deleting."); 
 		$("#alertError").show(); 
 	} else
	{ 
 		$("#alertError").text("Unknown error while deleting.."); 
 		$("#alertError").show(); 
	} 
}



//CLIENT-MODEL================================================================
function validateProductForm() 
{  //projID, projName, description, area, resName, resID, price
// ID
if ($("#projID").val().trim() == "") 
if (!$.isNumeric(projID))
 {
 return "Insert a numerical value for Project ID.";
 } 
// Project Name
if ($("#projName").val().trim() == "") 
 { 
 return "Insert Project Name."; 
 } 
// description-------------------------------
if ($("#description").val().trim() == "") 
 { 
 return "Insert description."; 
 } 

//Area------------------------
if ($("#area").val().trim() == "") 
{ 
return "Insert Area."; 
} 
//  Researcher ID------------------------
if ($("#resID").val().trim() == "") 
{ 
return "Insert Researcher ID."; 
} 
//price
if ($("#price").val().trim() == "") 
{ 
return "Insert price."; 
}

return true; 
}
