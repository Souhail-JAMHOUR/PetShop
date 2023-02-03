package ma.petshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		    String username = request.getParameter("username");
		    String email = request.getParameter("email");
		    String password = request.getParameter("password");


		    // Connect to the database
		    Connection con = null;
		    Statement stmt = null;
		    try {
		      Class.forName("com.mysql.jdbc.Driver");
		      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PetShopDB", "root", "password");
		      User newUser = new User(username, email, password);
		      newUser.insertToDB(con);
		    } catch (ClassNotFoundException | SQLException e) {
		      e.printStackTrace();
		    } finally {
		      try {
		        if (stmt != null) {
		          stmt.close();
		        }
		        if (con != null) {
		          con.close();
		        }
		      } catch (SQLException e) {
		        e.printStackTrace();
		      }
		    }
 		    response.sendRedirect("myaccount.html");
		  }
	}

