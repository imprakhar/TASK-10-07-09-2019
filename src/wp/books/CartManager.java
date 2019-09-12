package wp.books;

import java.io.IOException;
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

@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	ArrayList<String> al= new ArrayList<>();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user=(String) session.getAttribute("user");
		String code=request.getParameter("code");
		al.add(code);
		session.setAttribute("ArrayList", al);
		response.sendRedirect("ViewCart");
	}

	@Override
	public void destroy() {
		try {
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abcd1234");
			String sql = "SELECT bookid,bname,bauthor,subject,price from books where bookid=?";
			PreparedStatement ps = con.prepareStatement(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
