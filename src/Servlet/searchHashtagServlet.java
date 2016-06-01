package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class searchHashtagServlet
 */
@WebServlet("/searchHashtagServlet")
public class searchHashtagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchHashtagServlet() {
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
		ResultSet rs;
		String x = request.getParameter("hashtag");
		System.out.println(x);//display hashtag entered
        if(verify(x)) {//call verify method
        	request.setAttribute("error","Error");//if error exits
        	RequestDispatcher r= request.getRequestDispatcher("search.jsp");//redirect to search page
        	r.forward(request,response);	
        }
        else{
        HashtagDAO hdao = new HashtagDAO();
        String q = "Select * from hashtag where Name='"+x+"';";//check if hashtag exist in database
        Connection c=(Connection) hdao.create();//database connection
        try {
        	Statement st = c.createStatement();
        	rs =st.executeQuery(q);//execute query
            if(rs.next()) {
                  System.out.println("hashtag exists");//hashtag exists 
                  
                  int size=0;
                  String nih="Select content,time_created from hashtagFeed where hashtag='"+x+"' order by time_created desc";
                  ResultSet rst=st.executeQuery(nih);//execute query
                  rst.last();
                  size = rst.getRow();
                  rst.beforeFirst();
                  //System.out.println("Size:"+size);
                  request.setAttribute("hashname",x);
                  ArrayList<hashtag> htag=new ArrayList<hashtag>();
                  hashtag hta;
                  //int t=0;
                  while(rst.next())
                  {
                	  hta=new hashtag();
                	  System.out.println(rst.getString("content"));
                	  hta.setcontent(rst.getString("content"));
                	  hta.settime(rst.getString("time_created"));
                	  htag.add(hta);
                	  
                  }
                  request.setAttribute("hashvalues", htag);
                  RequestDispatcher r= request.getRequestDispatcher("viewhashtag.jsp");
              	r.forward(request,response);
                }
                else {
                  System.out.println("hashtag does not exist");
                  RequestDispatcher r= request.getRequestDispatcher("search.jsp");//redirect to search page
                	r.forward(request,response);
                }
        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}
        }
        
        
        }
       
       // RequestDispatcher r= request.getRequestDispatcher("viewhashtag.jsp");
    	//r.forward(request,response);
        public boolean verify(String n)
        {
    		if(n.equals("") || n==null || n.length()>45)//check if empty or string exceeds the length of the string that can be entered
        	return true;
    		else
    		{
    			Pattern p= Pattern.compile("[^A-Za-z0-9]");//regular expression
    			Matcher m= p.matcher(n);
    			if(m.find())//check whether it matches or not
    				return true;
    			else
    				return false;
    		}
	}

	}
