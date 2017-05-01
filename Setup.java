

import java.io.IOException;
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
 * Servlet implementation class Setup
 */
@WebServlet(description = "Creates DB instance and gets City list", urlPatterns = { "/Setup" })
public class Setup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection mySqlCon = SetupDBConnection();
    private ArrayList myCityList;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Setup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("test");
		myCityList = GetCityList();
		request.setAttribute("citylist", myCityList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String selectedvalue  = request.getParameter("selectedName");
		System.out.println(selectedvalue);

		// get movies list in the selected City.
		int city_id = GetCityId(selectedvalue);
		//GetMoviesList(city_id);
		request.setAttribute("movielist", GetMoviesList(city_id));
		response.getWriter().write("movielist");
		
	}
	
	private Connection SetupDBConnection ()
	{
		Connection con= MySQLcon.DBManager();
		return con;
	}
	
	private ArrayList<Cities> GetCityList( )
	{
		ArrayList<Cities> arList = new ArrayList<Cities>();
		try {
			Statement stmt	=	mySqlCon.createStatement();
			String sql= "select * from cities";
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()){
					Cities cities=new Cities();
					cities.setCid(rs.getInt(1));
					cities.setCname(rs.getString(2));
					arList.add(cities);
			}
		}
		catch(SQLException e){
				e.printStackTrace();
		}
		return arList;
	}
	
	private int GetCityId( String name){
		int city_id = 0;
		try {
			Statement stmt	= mySqlCon.createStatement();
			String sql= "select city_id from cities where city_name=" + name;
			ResultSet rs= stmt.executeQuery(sql);
			city_id = rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return city_id;
	}
	private ArrayList<Movies> GetMoviesList ( int id)
	{
		ArrayList<Movies> arList = new ArrayList<Movies>();
		try {
			Statement stmt	=	mySqlCon.createStatement();
			String sql= "select * from movies where city_id=" + id;
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()){
					Movies movie =new Movies();
					movie.setMovieId(rs.getInt(1));
					movie.setMovieName(rs.getString(2));
					arList.add(movie);
			}
		}
		catch(SQLException e){
				e.printStackTrace();
		}
		return arList;
	}
}
