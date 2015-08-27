import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Servlet implementation class Customers
 */
@WebServlet("/Customers")
public class Customers extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String name;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customers() {
        super();
        // TODO Auto-generated constructor stub
        name = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//URL of Oracle database server
	        String url = "jdbc:oracle:thin:testuser/password@localhost"; 

	        //properties for creating connection to Oracle database
	        Properties props = new Properties();
	        props.setProperty("user", "testdb");
	        props.setProperty("password", "password");
	        
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	        //creating connection to Oracle database using JDBC
	        Connection conn = DriverManager.getConnection(url, props);
	
	        String firstSql = "SELECT cust_first_name FROM demo_customers WHERE customer_id = 2";
	
	        //creating PreparedStatement object to execute query
	        PreparedStatement firstPreStatement = conn.prepareStatement(firstSql);
	        ResultSet result = firstPreStatement.executeQuery();
	        String firstName = "";
	        while (result.next()) {
	        	firstName = result.getString(1);
	        }
	        String lastSql = "SELECT cust_last_name FROM demo_customers WHERE customer_id = 2";
	        
	        PreparedStatement lastPreStatement = conn.prepareStatement(lastSql);
	        result = lastPreStatement.executeQuery();
	        String lastName = "";
	        while(result.next()){
	            lastName = result.getString(1);
	        }
	        name = firstName + " " + lastName;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("name", name);
		getServletContext().getRequestDispatcher("/BootstrapOutput.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
