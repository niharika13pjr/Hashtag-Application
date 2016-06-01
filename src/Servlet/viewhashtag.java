package Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class viewhashtag
 */
@WebServlet("/viewhashtag")
public class viewhashtag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewhashtag() {
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
		String x = request.getParameter("hashtagFeed");
		
		//System.out.println(x);
        System.out.println(x);//display content
        String h=request.getParameter("hide");
        String post=x;
        //String h=request.getParameter("hashtag");
       /*if(x.equals("")) {
        	request.setAttribute("error","Error");
        	RequestDispatcher r= request.getRequestDispatcher("viewHashtag.jsp");
        	r.forward(request,response);	
        } 
        else{ */
        HashtagDAO hdao = new HashtagDAO();//DAO 
        Connection c=(Connection) hdao.create();//connection to database
    	Statement st;
    	if(! x.equals("")) {
        String q = "Insert into hashtagFeed(time_created,parentFeedID,hashtag,content) values(current_timestamp,null,'"+h+"','"+post+"');";
        
        try {
        	st = c.createStatement();
			st.executeUpdate(q);//Update database
			 System.out.println(q);
        }
        catch (Exception ex) {
        	System.out.println(ex);
        }
        }
        try {
        request.setAttribute("hashname",h); 
        //int size=0;
        String nih="Select content,time_created from hashtagFeed where hashtag='"+h+"' order by time_created desc";
        st = c.createStatement();
        ResultSet rst=st.executeQuery(nih);
        
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
        }
        catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			
		}
        request.setAttribute("hashtagFeed", h);
        if(x.equals("")) {
        	request.setAttribute("error","Error");
        	RequestDispatcher r= request.getRequestDispatcher("viewhashtag.jsp");
        	r.forward(request,response);	
        } 
        else {
        RequestDispatcher r= request.getRequestDispatcher("viewhashtag.jsp");//redirect to viewhashtag page
    	r.forward(request,response);
        }
        //}
        
	
	}

}
