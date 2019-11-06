

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Booking;
import entities.Brand;
import entities.Query;
import entities.User;
import entities.Vehicle;
import exception.InvalidUserException;
import exception.NoBrandAvailableException;
import exception.NoVehicleException;
import operations.IndexOperations;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =  response.getWriter();
		String queryres = request.getParameter("queryRaised");
		String username = request.getParameter("userName");
		String submitBtn = request.getParameter("submit");
		IndexOperations op = new IndexOperations();
		if(submitBtn!=null) {
		Query query = new Query();
		query.setStatus("0");
		query.setDescription(queryres);
		User user = null;
		try {
			user = op.getUserByUsername(username);
		} catch (InvalidUserException e) {
			out.println("User Not Found..");
		}
		query.setUser(user);
		boolean flag = op.addQuery(query);
		if(flag) {
			out.println("Query raised Successfully");
		}
		else {
			out.println("Query raise failed");
		}
		}
		//accepting booking request
		String submitBooking = request.getParameter("submitbooking");
		if(submitBooking!=null) {
			Brand brand=null;
			String username1=request.getParameter("vusername");
			String vName = request.getParameter("vName");
			String vNumber = request.getParameter("vNumber");
			String vBrand = request.getParameter("vBrand");
			boolean flag1=false;
			try {
				User user1=op.getUserByUsername(username1);
				brand = op.getBrandByBrandname(vBrand);
				Vehicle vehicle = op.displayVehicleByNumber(vNumber);
				Booking booking = new Booking();
				booking.setVehicle(vehicle);
				booking.setUser(user1);
				booking.setStatus("0");
				flag1 = op.createBooking(booking);
			} catch (InvalidUserException | NoBrandAvailableException | NoVehicleException e) {
				out.println("Booking unsuccessful..Choose another car!");
			}
			if(flag1) {
				out.println("Booking request Success...waiting for Administator response..");
			}
			else {
				out.println("booking unsuccessful...try after sometime..");
			}
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
