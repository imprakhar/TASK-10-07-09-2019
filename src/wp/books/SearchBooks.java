package wp.books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchBooks")
public class SearchBooks extends HttpServlet {

	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		String subject=req.getParameter("subject");
		try{
			ps.setString(1, subject+"%"); 
			rs=ps.executeQuery();
			while(rs.next())
			{
				String id=rs.getString(1);
				String name=rs.getString(2);
				String author=rs.getString(3);
				String price=rs.getString(4);
				String sub=rs.getString(5);
		        out.println("<table border=2>");
		        out.println("<tr>");
		        out.println("<th>Book Id</th>");
		        out.println("<th>Book Name</th>");
		        out.println("<th>Book Author</th>");
		        out.println("<th>Book Price</th>");
		        out.println("<th>Subject</th>");
		        out.println("</tr>");
		        out.println("<tr>");
		        out.println("<td>");
		        out.println(id);
		        out.println("</td>");
		        out.println("<td>");
		        out.println(name);
		        out.println("</td>");
		        out.println("<td>");
		        out.println(author);
		        out.println("</td>");
		        out.println("<td>");
		        out.println(price);
		        out.println("</td>");
		        out.println("<td>");
		        out.println(sub);
		        out.println("</td>");
		        out.println("</tr>");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void destroy() {
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			Connection con = DriverManager.getConnection(url, "system", "abcd1234");
			String sql = "select * from books where subject like ?";
			ps = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
