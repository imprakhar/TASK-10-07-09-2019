<%@page
	import="java.io.*,java.sql.*,javax.servlet.*,javax.servlet.http.*"%>
<html>
<body>
	<h3>Online Book Store</h3>
	<h4>Update Profile</h4>
	<hr>
<%
	String str = (String) session.getAttribute("user");
if(str==null)
{
	response.sendRedirect("welcome.jsp");
}
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abcd1234");
	String sql = "SELECT * FROM users where username=?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, str);
	ResultSet rs= ps.executeQuery();
	while(rs.next())
	{
		
%>
	<form action="UpdateProfile" >
		<pre>
		Userid		<input type="text" name="userid" value=<%=rs.getString(1)  %> readonly />
		Username	<input type="text" name="username" value=<%=rs.getString(2)  %> readonly />
		Password	<input type="password" name="password" value=<%=rs.getString(3) %> />
		Email-Id	<input type="text" name="email" value=<%= rs.getString(4)%> />
		Mobile		<input type="text" name="mobile" value=<%=rs.getString(5) %> />
		Address		<input type="text" name="address" value=<%=rs.getString(6) %> />
				<input type="submit" value="Update Profile" />
	</pre>
	</form>
	<hr>
	<% } 
	
	%>
	<a href="buyerpage.jsp">Home</a>
</body>
</html>