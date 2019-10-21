

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Admin;
import exception.UserExistException;
import operations.RegisterAdminOperations;

/**
 * Servlet implementation class RegisterAdminServlet
 */
@WebServlet("/RegisterAdminServlet")
public class RegisterAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name = request.getParameter("adminname");
		String username = request.getParameter("adminusername");
		String password = request.getParameter("adminpassword");
		
		RegisterAdminOperations register = new RegisterAdminOperations();
		boolean flag =false;
		try {
			flag = register.validateUsername(username);
		} catch (UserExistException e) {
			String msg = "Username already exist!";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("RegisterAdmin.jsp");
			rd.forward(request, response);
		}
		
		if(flag) {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setUsername(username);
			admin.setPassword(password);
			boolean flag1 = register.adminRegister(admin);
			if(flag1) {
				RequestDispatcher rd = request.getRequestDispatcher("LoginAdmin.jsp");
				rd.forward(request, response);
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
