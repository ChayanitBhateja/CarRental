


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.InvalidUserException;
import operations.LoginOperations;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginOperations operation = new LoginOperations();
		boolean flag=false;
		try {
			flag=operation.validateUser(username,password);
		}
		catch(InvalidUserException ue) {
			String msg = "User Entered is Invalid.";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
		}
		
		if(flag) {
			
			HttpSession oldsession  = request.getSession(false);
			if(oldsession!=null) {
				System.out.println(oldsession.getAttribute("name"));
				System.out.println(oldsession.getAttribute("loginToken"));
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request,response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("name", operation.getName(username));
				session.setAttribute("loginToken", session.getId());
				session.setMaxInactiveInterval(24*60*60);
				Cookie cookie = new Cookie("loginToken",session.getId());
				cookie.setMaxAge(24*60*60);
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
