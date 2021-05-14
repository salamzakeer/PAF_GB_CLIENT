package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.product;
/**
 * Servlet implementation class productAPI
 */
@WebServlet("/productAPI")
public class productAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	product Obj = new product();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//projID, projName, description, area, resName, resID, price
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = Obj.insertproductdetails(Integer.parseInt(request.getParameter("projID")), 
				request.getParameter("projName"),
				request.getParameter("description"), 
				request.getParameter("area"),
				request.getParameter("resName"),
				request.getParameter("resID"),
				Float.parseFloat(request.getParameter("price")));

		response.getWriter().write(output);
		//doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		String output = Obj.updateproductdetails(//paras.get("hididSave").toString(), 
				 //Integer.parseInt(paras.get("projID").toString()), 
				paras.get("projID").toString(),
				 paras.get("projName").toString(), 
				 paras.get("description").toString(), 
				 paras.get("area").toString(),
				 paras.get("resName").toString(),
				 paras.get("resID").toString(),
				 Float.parseFloat(paras.get("price").toString())); 
		 response.getWriter().write(output); 
				 
	} 



	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request); 
		
		 //String output = Obj.deleteProduct(Integer.parseInt(paras.get("projID").toString())); 
		 String output = Obj.deleteProduct(paras.get("projID").toString()); 
		 
		response.getWriter().write(output);
	}
	
	
	
	private Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>(); 
		try
		{ 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			String queryString = scanner.hasNext() ? 
						scanner.useDelimiter("\\A").next() : ""; 
	
				scanner.close(); 
				String[] params = queryString.split("&"); 
				for (String param : params) 
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]); 
				} 
		} 
		catch (Exception e) 
	 	{ 
	 	} 
		return map; 
		}
	
	

}
