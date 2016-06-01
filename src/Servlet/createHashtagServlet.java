package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class createHashtagServlet
 */
@WebServlet("/createHashtagServlet")
public class createHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createHashtagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		java.text.SimpleDateFormat sd= new java.text.SimpleDateFormat("yyyy-MM-dd");
		String x = request.getParameter("hashtag");
		Date dt=new java.util.Date();//date created
        System.out.println(sd.format(dt));
        System.out.println(x);//name of hashtag
        HashtagDAO hdao = new HashtagDAO();
        Connection c=(Connection) hdao.create();
        boolean y=false;
        String s = "Select * from hashtag where Name='"+x+"';";
        try {
        	Statement stmt = c.createStatement();
        	ResultSet rslt =stmt.executeQuery(s);
        	if(rslt.next())
        		y=true;
        	
        }
        catch(Exception e)
        {
        	
        }
        if(verify(x) || y==true) {
        	request.setAttribute("error","Error");
        	RequestDispatcher r= request.getRequestDispatcher("createHashtag.jsp");
        	r.forward(request,response);	
        }
        else{
        
        String q = "Insert into hashtag(Name,DateCreated) values('"+x+"','"+ sd.format(dt)+"');";
        //Statement st = hdao.create();
        try {
        	Statement st = c.createStatement();
			st.executeUpdate(q);//execute query and update database
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        ArrayList<hashtag> htag=new ArrayList<hashtag>();
        request.setAttribute("hashvalues", htag);
        
        request.setAttribute("hashname",x);
        RequestDispatcher r= request.getRequestDispatcher("search.jsp");
    	r.forward(request,response);
	}
	public boolean verify(String n)
    {
		if(n.equals("") || n==null || n.length()>45)//check if empty or string exceeds the length of the string that can be entered 
    	return true;
		else
		{
			Pattern p= Pattern.compile("[^A-Za-z0-9]");//regular expression
			Matcher m= p.matcher(n);
			if(m.find())//check if matched
				return true;
			else
				return false;
		}
    }

}
