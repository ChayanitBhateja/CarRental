

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Query;
import entities.User;
import exception.InvalidUserException;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out =  response.getWriter();
		String queryres = request.getParameter("queryRaised");
		String username = request.getParameter("userName");
		IndexOperations op = new IndexOperations();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
