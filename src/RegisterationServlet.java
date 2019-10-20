


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.User;
import exception.InvalidAadharException;
import exception.UserExistException;
import exception.WrongNumberException;
import operations.RegisterOperations;

/**
 * Servlet implementation class RegisterationServlet
 */
@WebServlet("/RegisterationServlet")
public class RegisterationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobileno");
		String email = request.getParameter("email");
		String aadharno = request.getParameter("aadhar");
		String licenseno = request.getParameter("license");
		boolean flag=false;
		RegisterOperations operate = new RegisterOperations();
		try {
			if(operate.usernameCheck(username) &&
			operate.mobileCheck(mobile) &&
			operate.aadharCheck(aadharno)) {
				
				flag= true;
			}
			
		}catch(UserExistException ue) {
			String msg ="User Already Exists..try another username..";
			request.setAttribute("msg", msg);
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.jsp");
			rd.forward(request,response);
		}catch(WrongNumberException ue) {
			String msg="Mobile number entered is incorrect..Check and enter again";
			request.setAttribute("msg",msg);
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.html");
			rd.forward(request,response);
		}catch(InvalidAadharException ue) {
			String msg = "Aadhar number seems to be incorrect...check and try again..";
			request.setAttribute("msg", msg);
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.html");
			rd.forward(request,response);
		}
		
		if(flag) {
			User user  = new User();
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setAadharno(aadharno);
			user.setMobileno(mobile);
			user.setLicenseno(licenseno);
			
			boolean confirm=operate.registerUser(user);
			if(confirm) {
				out.println("Registered Successfully!");
				RequestDispatcher rd =  request.getRequestDispatcher("Login.jsp");
				rd.forward(request,response);
			}
		}
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
