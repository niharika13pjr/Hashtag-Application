package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ims.InventoryManagementSystem;
import ims.Items;
import ims.PickingResult;
import ims.RestockingResult;
public class Database implements ims.InventoryManagementSystem 
{
	@Override
	public PickingResult pickProduct(String productId, int amountToPick)
	{
		// TODO Auto-generated method stub
		PickingResult pr=new PickingResult();
		Connection conn=null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/IMS";
		String username="root";
		String password="root";
		conn= DriverManager.getConnection(url,username,password);
		String sql="Select * from product where prodname=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, productId);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int y=rs.getInt("numberofitems");
			if(y>=amountToPick)
			{
				pr.setproductname(rs.getString("prodname"));
				pr.setnumberofitems(rs.getInt("numberofitems"));
				pr.setroom(rs.getString("room"));
				pr.setmessage("Products picked: "+productId+"\nCount: "+amountToPick+ "\nRoom: "+pr.getroom());

				String sql1 = "UPDATE product SET numberofitems = ? WHERE prodname = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1); 
				int up=y-amountToPick;
				ps1.setInt(1,up);
				ps1.setString(2,productId);
				ps1.executeUpdate();
			}
			else
			{
				pr.setmessage("Required item number not available");
			}
		}
		else
		{
			pr.setmessage("Required product not available");
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return pr;
		
	}

	@Override
	public RestockingResult restockProduct(String productId, int amountToRestock)
	{
		// TODO Auto-generated method stub
		RestockingResult rp=new RestockingResult();
		Connection conn=null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/IMS";
		String username="root";
		String password="root";
		conn= DriverManager.getConnection(url,username,password);
		
		String sql="Select * from product where prodname=?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, productId);
		ResultSet rs=ps.executeQuery();
		
		if(rs.next())
		{
			int y=rs.getInt("numberofitems");
				rp.setproductname(rs.getString("prodname"));
				rp.setnumberofitems(rs.getInt("numberofitems"));
				rp.setroom(rs.getString("room"));
				rp.setmessage("Products Restocked: "+productId+"\nCount: "+amountToRestock+ "\nRoom: "+rp.getroom());
				String sql1 = "UPDATE product SET numberofitems = ? WHERE prodname = ?";
				PreparedStatement ps1 = conn.prepareStatement(sql1); 
				int up=y+amountToRestock;
				ps1.setInt(1,up);
				ps1.setString(2,productId);
				ps1.executeUpdate();
			//}
			
		}
		else
		{
			rp.setmessage("Required product not available");
		}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return rp;
		
	}
	public ArrayList<Items> initdata()
	{
		Items i1=new Items("apples",5,"room1");
		Items i2=new Items("oranges",5,"room1");
		Items i3=new Items("berries",5,"room1");
		ArrayList<Items> arr=new ArrayList<Items>();
		arr.add(i1);
		arr.add(i2);
		arr.add(i3);
		return arr;
	}
	
}
