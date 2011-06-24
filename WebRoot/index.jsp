<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	
	
	
%>

<html>
  <head>
    
    
    <title>index.jsp</title>
   
	
  </head>
  
  <body>
    This is my JSP page. <br>
    <table width="800" height="300" border="1">
    	<tr >
    		<td >id</td><td>name</td>
    	</tr>
    	1111111
    <%
    	try{
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
			out.println("hellow");
    	}catch(Exception e){
    		out.println("hellll");
    	}
    %>
    	
		
    </table>
    
  </body>
</html>
