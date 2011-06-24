<%@ page language="java" import="java.util.*,java.net.*"
	pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<HEAD>
		<TITLE>WOW world of warcraft star craft2 diablo3 IZAR jsp</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META content="MSHTML 6.00.2900.5626" name=GENERATOR>
	</HEAD>
	<body>
		<div id='all' align='center'>
			<form action="url.jsp" method="get">
				url:
				<input name="url" value="<%=request.getParameter("url")==null?"www.google.com":request.getParameter("url") %>" />
				<input type="submit" value="È·¶¨">
			</form>
			<%
                String url = request.getParameter("url");
                if (url != null) {
                    try {

                        InetAddress[] addresses =

                        InetAddress.getAllByName(url);

                        for (int i = 0; i < addresses.length; i++) {

                            %><%=addresses[i].getHostAddress()+"  "+addresses[i].getHostName() %><br>
			<%

                        }

                    }

                    catch (UnknownHostException ex) {
                        ex.printStackTrace();
                        %><%="Could not find "+url %><br>
			<%
			    }

			    }
			%>
		</div>
	</BODY>
</HTML>
