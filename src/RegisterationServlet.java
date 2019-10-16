


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.InvalidAadharException;
import exception.UserExistException;
import exception.WrongNumberException;
import pkg.User;
import pkg.Operations;

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
		out.println("reached1");
		Operations operate = new Operations();
		out.println("reached test");
		try {
			if(operate.usernameCheck(username) &&
			operate.mobileCheck(mobile) &&
			operate.aadharCheck(aadharno)) {
				
				flag= true;
				out.println("reached2");
			}
			
		}catch(UserExistException ue) {
			out.println("User Already Exists..try another username..");
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.html");
			rd.include(request,response);
		}catch(WrongNumberException ue) {
			out.println("Mobile number entered is incorrect..Check and enter again");
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.html");
			rd.include(request,response);
		}catch(InvalidAadharException ue) {
			out.println("Aadhar number seems to be incorrect...check and try again..");
			RequestDispatcher rd =  request.getRequestDispatcher("Registeration.html");
			rd.include(request,response);
		}
		
		if(flag) {
			User user  = new User();
			out.println("reached3");
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setAadharno(aadharno);
			user.setMobileno(mobile);
			user.setLicenseno(licenseno);
			out.println("reached4");
			boolean confirm=operate.registerUser(user);
			out.println("reached5");
			if(confirm) {
				out.println("Registered Successfully!");
				RequestDispatcher rd =  request.getRequestDispatcher("index.html");
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
