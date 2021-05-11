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
	 var status = validateResearcherForm(); 
	 if (status != true) 
	  { 
	  $("#alertError").text(status); 
	  $("#alertError").show(); 
	  return; 
	  }
	 
	// If valid-------------------------
	 $("#formProduct").submit();
});


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hididSave").val($(this).closest("tr").find('#hididUpdate').val()); 
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













/**
 * 
 */