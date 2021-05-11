package model;
import java.sql.*;
public class product {

	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");


			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}

	public String insertproductdetails(Integer projID, String projName, String description, String area, String resName, String resID, float price)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = "INSERT INTO product (projID, projName, description, area, resID, resName, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, projID);
			preparedStmt.setString(2, projName);
			preparedStmt.setString(3, description);
			preparedStmt.setString(4, area);
			preparedStmt.setString(5, resName);
			preparedStmt.setString(6, resID);
			preparedStmt.setFloat(7, price);
			// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the product.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readProducts()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed

			output = "<table border='1' align='center'>"+
			        "<tr><th>Product ID</th>" +
					"<th>Product Name</th>" +
					"<th>Product Description</th>"+
					"<th>Product Area</th>"+
					"<th>Researcher Name</th>"+
					"<th>Researcher ID</th>"+
					"<th>Price</th>"+
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from product";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
			{
				//projID projName description area resID resName price
				String projID = Integer.toString(rs.getInt("projID"));
				String projName = rs.getString("projName");
				String description = rs.getString("description");
				String area =rs.getString("area");
				String resID = rs.getString("resID");
				String resName = rs.getString("resName");
				String price = String.valueOf(rs.getFloat("price"));

				 output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + projID + "'>"
	            		 + projName + "</td>";
				
				output += "<td>" + description + "</td>";
				output += "<td>" + area + "</td>";
				output += "<td>" + resID + "</td>";
				output += "<td>" + resName + "</td>";
				output += "<td>" + price + "</td>";
				// buttons
				 output +="<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td>"
	        			 + "<td><form method='post' action='products.jsp'>"
	        			 +"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
	        			 + "<input name='hididDelete' type='hidden' value='" + projID + "'></form></td></tr>";
			}
			con.close();

			output += "</table>";
		} 
		catch (Exception e)
		{
			output = "Error while reading the products.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateproductdetails(Integer projID, String projName, String description, String area, String resName, String resID, float price)

	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }

			String query = "UPDATE product SET  projName=?, description=?, area=?, resID=?, resName=?, price=? WHERE projID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, projName);
			preparedStmt.setString(2, description);
			preparedStmt.setString(3, area);
			preparedStmt.setString(4, resName);
			preparedStmt.setString(5, resID);
			preparedStmt.setFloat(6, price);
			preparedStmt.setInt(7, projID);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String deleteProduct(String projID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "DELETE FROM product  WHERE projID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(projID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}  