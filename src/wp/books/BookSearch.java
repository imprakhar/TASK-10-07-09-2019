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

/**
 * Servlet implementation class BookSearch
 */
@WebServlet("/BookSearch")
public class BookSearch extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abcd1234");
		String[] subject = request.getParameterValues("subject"); 
		PrintWriter out=response.getWriter();
		String sql = "SELECT bookid,bname from books where subject=?";
		PreparedStatement ps = con.prepareStatement(sql);
		for(String s:subject)
		{
			ps.setString(1, s);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				out.println("<a href=BookDetailsServlet?code=" + rs.getString(1) + ">");
				out.println(rs.getString(2));
				out.println("</a><br>");
			}
		}
		out.println("<hr>");
		out.println("<a href=SubjectPageServlet>Subject-Page</a>");
		out.println("<a href=buyerpage.jsp>Buyer-Page</a>");
		out.println("</body></html>");
		}catch(Exception e)
		{
			
		}
	}

}
