<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="Servlet.hashtag" %>
     <%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Hashtag</title>
</head>
<%
String hashtag=request.getParameter("hashtagFeed");
%>
<body>
<h4>Hashtag Details :<b> <%out.println(request.getAttribute("hashname")); %></b></h4>
<form action="<%= request.getContextPath()%>/viewhashtag" method="post">
<table>
<tbody>
<tr>
<td> Enter Hashtagfeed : </td>
<td> <input type="text" name="hashtagFeed" size=""></td>
</tr>
</tbody>
</table>
<input type="reset" value="clear" name="Clear">
<input type="submit" value="submit" name="Submit">
<input type="hidden" name="hide" size="" value=<%= request.getAttribute("hashname")%>>
<%String a = (String)request.getAttribute("error");
if(a!= null)
	out.println(a);
%>
</form>
<%ArrayList<hashtag> ht=(ArrayList<hashtag>)request.getAttribute("hashvalues");
for(int i=0;i<ht.size();i++)
{
	
out.println(ht.get(i).gettime());
out.println(ht.get(i).getcontent());
%>
<br>
<input type="text" name="hashvalues">
<input type="button" value="Reply" name="Submit"><br> <%

}
%>
</body>
</html>