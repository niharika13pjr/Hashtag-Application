<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Create Hashtag </title>
</head>
<script type="text/javascript">
</script>
<h2><b>Create hashtag</b></h2>
<body>
<form action="<%= request.getContextPath()%>/createHashtagServlet" method="post">
<table>
<tbody>
<td> Name of Hashtag : </td>
<td> <input type="text" name="hashtag" size=""></td>
</tbody>
</table>

<input type="Submit" value="Submit">
<%String a = (String)request.getAttribute("error");
if(a!= null)
	out.println(a);
%>

</form>

</body>
</html>