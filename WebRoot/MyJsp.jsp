<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	

  </head>
  
  <body>
    This is my JSP aaaaa page. <br><input type="button" value="on" name="button1">

<table>
<%			try{
			Connection con;
    		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url="jdbc:sqlserver://localhost:1433;databaseName=javasql";
			String user="sa";
			String pwd="760079";
			Statement stmt;
			String query;		
			ResultSet rs;
			out.println(path+" "+basePath);
    		Class.forName(driver);
    		out.println("qqqqqqq");
			con=DriverManager.getConnection(url,user,pwd);
			stmt=con.createStatement();
			query="select id,name from emp";
			stmt.execute(query);
			rs=stmt.getResultSet();
    		while(rs.next()){
				%><tr>
					<td>
						<%=rs.getInt("id")%>
					</td>
					
					<td>
						<%=rs.getString("name")%>
					</td>
				</tr>
		<%}
			
			rs.close();
			stmt.close();
			con.close();
    	}catch(Exception e){
    		out.print("aaaaaaa");
    	}
    	 %>
 </table>   	
  </body>
</html>