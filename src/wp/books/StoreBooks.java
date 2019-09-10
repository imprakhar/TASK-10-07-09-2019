package wp.books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StoreBooks")
public class StoreBooks extends HttpServlet {

	private Connection con;
	private PreparedStatement ps;

	public void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			Connection con = DriverManager.getConnection(url, "system", "abcd1234");
			String sql = "insert into books values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		//reads-request
		String bookid=request.getParameter("bookid");
		String bname=request.getParameter("bname");
		String bauthor=request.getParameter("bauthor");
		String price=request.getParameter("price");
		String subject=request.getParameter("subject");
		//process
		try{
			ps.setString(1, bookid);
			ps.setString(2, bname);
			ps.setString(3, bauthor);
			ps.setString(4, price);
			ps.setString(5, subject);
			int n=ps.executeUpdate();
			out.println("Book Added successfully");
			
		}catch(Exception e){
			out.println(e);
		}
	}

}
