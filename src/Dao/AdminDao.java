package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

	static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();

	public static void statusChange(String uname,Boolean flagVal)
	{
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql;
		try
		{
//			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
////			System.out.println("Datasource successful.");
//			ds.setServerName(System.getenv("ICSI518_SERVER"));
////			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//			ds.setDatabaseName(System.getenv("ICSI518_DB"));
////			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//			ds.setUser(System.getenv("ICSI518_USER"));
//	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
////			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//			// Get a connection object
//			con = ds.getConnection();
			con= DatabaseConnection.getConnection();
			if(flagVal == false)
			{
				sql = "update manager set flagbyadmin=" + 1 + " where user_id= '" + uname +"'";
			}
			else
			{
				sql = "update manager set flagbyadmin=" + 0 + " where user_id= '" + uname +"'";
			}
			System.out.println("Sql formed :: " + sql);
			ps= con.prepareStatement(sql); 
			ps.executeUpdate();
			

		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(con);
		ps.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
	}
}
