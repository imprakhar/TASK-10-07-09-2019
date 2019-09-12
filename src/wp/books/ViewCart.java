package wp.books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		ArrayList<String> alist = (ArrayList<String>) session.getAttribute("ArrayList");
		// out.println(alist);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abcd1234");
			for(int i=0;i<alist.size();i++)
			{
				String sql = "SELECT bookid,bname,bauthor,subject,price from books where bookid=?";
				PreparedStatement ps = con.prepareStatement(sql);
				String id= alist.get(i);
				//out.println(id);
				ps.setString(1, id);
				rs=ps.executeQuery();
				while(rs.next())
				{
					String bcode = rs.getString(1);
					String title = rs.getString(2);
					String author = rs.getString(3);
					String subject = rs.getString(4);
					String price = rs.getString(5);
					out.println("<table border=2>");
					out.println("<tr>");
					out.println("<td>BCode</td>");
					out.println("<td>" + bcode + "</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Title</td>");
					out.println("<td>" + title + "</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Author</td>");
					out.println("<td>" + author + "</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Subject</td>");
					out.println("<td>" + subject + "</td>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>Price</td>");
					out.println("<td>" + price + "</td><br>");
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td>");
					out.println("<a href=''>");
					out.println("Remove From Cart");
					out.println("</a>");
					out.println("<td>");
					out.println("</tr>");
					out.println("</table>");
				}
				out.println("<a href='buyerpage.jsp'>");
				out.println("Buyer Home");
				out.println("</a>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
