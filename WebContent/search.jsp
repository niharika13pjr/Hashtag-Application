<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<h2><b>Search hashtag</b></h2>
<body>
<form action="<%= request.getContextPath()%>/searchHashtagServlet" method="post">
<table>
<tbody>
<tr>
<td> Enter Hashtag : </td>
<td> <input type="text" name="hashtag" size=""></td>
</tr>
</tbody>
</table>
<input type="reset" value="clear" name="Clear">
<input type="Submit" value="submit">
<%String a = (String)request.getAttribute("error");
if(a!= null)
	out.println(a);
%>
</form>
</body>
</html>

