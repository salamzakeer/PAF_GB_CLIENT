package com;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.product;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/product")
public class productService {

	product Obj = new product();
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String readProducts()
	 {

	 return Obj.readProducts();
	 }

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("projID") Integer projID,
	 @FormParam("projName") String projName,
	 @FormParam("description") String description,
	 @FormParam("area") String area,
	 @FormParam("resName") String resName,
	 @FormParam("resID") String resID,
	 @FormParam("price") float price)
	{

	 String output = Obj.insertproductdetails(projID, projName, description, area, resName, resID, price);
	return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProduct(String proj)
	{
	//Convert the input string to a JSON object
	 JsonObject Object = new JsonParser().parse(proj).getAsJsonObject();
	//Read the values from the JSON object
	 String projID = Object.get("projID").getAsString();
	 String projName = Object.get("projName").getAsString();
	 String description = Object.get("description").getAsString();
	 String area = Object.get("area").getAsString();
	 String resName = Object.get("resName").getAsString();
	 String resID = Object.get("resID").getAsString();
	 float price = Object.get("price").getAsFloat();

	 String output = Obj.updateproductdetails(projID, projName, description, area, resName, resID, price);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProduct(String proj)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(proj, "", Parser.xmlParser());

	 String projID = doc.select("projID").text();
	 String output = Obj.deleteProduct(projID);
	return output;
	}



}