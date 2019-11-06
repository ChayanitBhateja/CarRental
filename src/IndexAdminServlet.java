import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Brand;
import entities.Vehicle;
import exception.NoBrandAvailableException;
import operations.IndexAdminOperations;

/**
 * Servlet implementation class IndexAdminServlet
 */
@WebServlet("/IndexAdminServlet")
public class IndexAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		IndexAdminOperations op = new IndexAdminOperations();
		String submitbrand = request.getParameter("submitbrand");
		String submitVehicle = request.getParameter("submitvehicle");
		if(submitbrand!=null) {
			String brandName = request.getParameter("brandname");
			Brand brand = new Brand();
			brand.setName(brandName);
			boolean flag = op.addBrand(brand);
			RequestDispatcher rd = request.getRequestDispatcher("IndexAdmin.jsp");
			String msgBrand;
			if(flag) {
				msgBrand = "Brand added successfully!";
			}
			else {
				msgBrand = "Brand adding unsuccessful!";	
			}
			request.setAttribute("msgbrand",msgBrand);
			rd.forward(request, response);
		}
		else if(submitVehicle!=null && submitbrand == null) {
			String name = request.getParameter("name");
			String number = request.getParameter("number");
			String brandname = request.getParameter("cars"); 
			Brand brand = null;
			Vehicle vehicle = new Vehicle(); 
			String msgvehicle;
			try {
				brand = op.getBrandByName(brandname);
				brand= vehicle.getBrand();
			} catch (NoBrandAvailableException e) {
				RequestDispatcher rd = request.getRequestDispatcher("IndexAdmin.jsp");
				msgvehicle = "No brand found with this name/it must have been deleted..";
				request.setAttribute("msgbrand",msgvehicle);
				rd.forward(request, response);
			}
			
			vehicle.setName(name);
			vehicle.setNumber(number);
			vehicle.setBrand(brand);
			boolean flag = op.addVehicle(vehicle);
			RequestDispatcher rd = request.getRequestDispatcher("IndexAdmin.jsp");
			if(flag) {
				msgvehicle="Vehicle Adding Successful";
			}
			else
				msgvehicle="Vehicle Adding Unsuccessful";
			
			request.setAttribute("msgvehicle",msgvehicle);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
