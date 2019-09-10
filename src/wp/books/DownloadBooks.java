package wp.books;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadBooks")
public class DownloadBooks extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File file=new File("C:\\Users\\Prakhar Fanse\\Desktop\\PROJECT FILE MAJOR");
		String[] str=file.list();
		PrintWriter out= response.getWriter();
		out.println("<html>");
		out.println("<body>");
		for(String s:str)
		{
			out.println("<a href='Download.java?choice="+s+"'>"+s+"</a>");
			out.println("<br>");
		}
		out.println("</body>");
		out.println("</html>");
	}

}
