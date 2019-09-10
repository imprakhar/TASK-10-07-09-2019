package wp.books;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Download.java")
public class Download extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String book= request.getParameter("choice");
		if(book.endsWith("docx"))
		{
			response.setContentType("application/msword");
		}
		else
		{
			response.setContentType("application/pdf");
		}
		FileInputStream fis= new FileInputStream("C:\\Users\\Prakhar Fanse\\Desktop\\PROJECT FILE MAJOR\\"+book);
		byte[] b= new byte[fis.available()];
		fis.read(b);
		fis.close();
		ServletOutputStream sos= response.getOutputStream();
		sos.write(b);
		sos.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
