

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.InvalidUserException;
import operations.LoginAdminOperations;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		LoginAdminOperations operation = new LoginAdminOperations();
		String rolename="";
		try {
			rolename = operation.getRolename(username);
		} catch (InvalidUserException e1) {
			String msg = "Can't Authenticate Username/Password is Invalid.";
			request.setAttribute("msg",msg);
			RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
			rd.forward(request,response);
		}
		if(!response.isCommitted()) {
			boolean flag=false;
			if(rolename.equals("admin")) {
				try {
					flag=operation.authenticateAdmin(username,password);
				} catch (InvalidUserException e) {
					String msg = "Username or password is Invalid.";
					request.setAttribute("msg",msg);
					RequestDispatcher rd = request.getRequestDispatcher("LoginAdmin.jsp");
					rd.forward(request,response);
				}
				if(flag) {
					RequestDispatcher rd = request.getRequestDispatcher("IndexAdmin.jsp");
					rd.forward(request,response);
				}
			}
			else {
				String msg = "Please login through User Portal";
				request.setAttribute("msg",msg);
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request,response);
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
