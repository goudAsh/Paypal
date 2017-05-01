

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CityListServlet
 */
@WebServlet("/CityListServlet")
public class CityListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<Cities> al=new ArrayList<Cities>();
			try{
			Connection con=MySQLcon.DBManager();
			Statement stmt=con.createStatement();
			String sql="select * from cities";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
					Cities cities=new Cities();
					cities.setCid(rs.getInt(1));
					cities.setCname(rs.getString(2));
					al.add(cities);
				}
			
				request.setAttribute("citylist", al);
				String jsp="/index.jsp";
				RequestDispatcher rd=getServletContext().getRequestDispatcher(jsp);
				rd.forward(request,response);
			}
			catch(SQLException e){
				e.printStackTrace();
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
