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
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	PreparedStatement ps;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out= response.getWriter();
			HttpSession session=request.getSession();
			String str = (String) session.getAttribute("user");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
					"abcd1234");
			String user= request.getParameter("userid");
			String username= request.getParameter("username");
			String userpass= request.getParameter("password");
			String useremail= request.getParameter("email");
			String usermobile= request.getParameter("mobile");
			String useraddress= request.getParameter("address");
			String sql1="Update users set userId= ?, username= ?, password= ?, emailId= ?, mobile= ?,address= ? where username=? ";
			ps=con.prepareStatement(sql1);
			ps.setString(1, user);
			ps.setString(2, username);
			ps.setString(3, userpass);
			ps.setString(4, useremail);
			ps.setString(5, usermobile);
			ps.setString(6, useraddress);
			ps.setString(7, str);
			ps.executeUpdate();
			out.println("Data Inserted");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
