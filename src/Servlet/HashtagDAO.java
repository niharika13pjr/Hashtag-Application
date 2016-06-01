package Servlet;
import java.sql.*;
public class HashtagDAO {
	
Connection conn=null;
Statement statement = null;
Connection create()
{
	try
	{
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	String url="jdbc:mysql://localhost:3306/hashtag";
	String username="root";
	String password="root";
	conn= DriverManager.getConnection(url,username,password);
	System.out.println("connected to db");
	
}catch(Exception e)
	{
	e.printStackTrace();
	}
	return conn;
}

}
