<%@page
	import="java.io.*,java.sql.*,javax.servlet.*,javax.servlet.http.*"%>

<html>
<body>
	<h3>Online Book Store</h3>
	<h4>Subject Page List </h4>
	<hr>
	<% 
	String user=(String)session.getAttribute("user");
		
		if(user==null){
			response.sendRedirect("welcome.jsp");
		}
		Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abcd1234");
			String sql = "SELECT distinct subject from books";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String sub = rs.getString(1);
			%>
			<form action ="BookSearch">
			<pre>
				<%=sub %><input type="checkbox" name="subject" value="<%=sub %>" />
				</pre>
				
				<% } %>
				<input type="submit" value="View">
				</form>
	<hr>
</body>
</html>