package Servlet;

import java.util.Date;

public class hashtag {
	private String hashtag;
	private Date DateCreated;
	private int id;
	private String time;
	private String parentFeed;
	private String content;
	
	public String gethashtag(){
		return hashtag;
	}
	public void sethashtag(String hashtag) {
        this.hashtag = hashtag;
    }
	public Date getDateCreated() {
        return DateCreated;
    }
	public void setDateCreated(Date DateCreated){
		this.DateCreated=DateCreated;
	}
	public int getid() {
        return id;
    }
	public void setid(int id) {
        this.id = id;
    }
	public String gettime(){
		return time;
	}
	public void settime(String time){
		this.time= time;
	}
	public String getparentFeed(){
		return parentFeed;
	}
	public void setparentFeed(String parentFeed){
		this.parentFeed=parentFeed;
	}
	public String getcontent(){
		return content;
	}
	public void setcontent(String content){
		this.content= content;
	}
	   

}

