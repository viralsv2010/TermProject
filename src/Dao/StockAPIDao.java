package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class StockAPIDao {

	static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();

	
	private static String stockname;
	public static String getStockname() {
		return stockname;
	}

	public static void setStockname(String stockname) {
		StockAPIDao.stockname = stockname;
	}

	public static double getStockprice() {
		return stockprice;
	}
	public static void setStockprice(double stockprice) {
		StockAPIDao.stockprice = stockprice;
	}
	public static int getStockqty() {
		return stockqty;
	}
	public static void setStockqty(int stockqty) {
		StockAPIDao.stockqty = stockqty;
	}
	public static double getStockamt() {
		return stockamt;
	}
	public static void setStockamt(double stockamt) {
		StockAPIDao.stockamt = stockamt;
	}
	private static double stockprice;
	private static int stockqty;
	private static double stockamt;
	public static List<StockAPIDao> getCurrentDetails(int stockid)
	{
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql;
		List<StockAPIDao> list = new ArrayList<StockAPIDao>();
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
			
			con = DatabaseConnection.getConnection();
			sql = "select * from customerpurchase where uid= '" + stockid +"'";
			
			System.out.println("Sql formed :: " + sql);
			ps= con.prepareStatement(sql); 
			rs = ps.executeQuery();
			
			rs.next();
			
			StockAPIDao obj = new StockAPIDao();
			
			obj.setStockname(rs.getString("stock_symbol"));
			obj.setStockprice(rs.getDouble("price"));
			obj.setStockqty(rs.getInt("qty"));
			obj.setStockamt(rs.getDouble("amt"));
			
			
			list.add(obj);
					
			
			

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
		rs.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
		return list;
	}
	
	
	
	
	
	public static String getSymbol(int stockid)
	{
		PreparedStatement psr = null;
		Connection conr = null;
		ResultSet rsr = null;
		String sqlr;
		String symr = null;
		List<StockAPIDao> list = new ArrayList<StockAPIDao>();
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			
			conr = DatabaseConnection.getConnection();
			sqlr = "select * from customerpurchase where uid="+ stockid + "";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed :: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			System.out.println("Rs :: " + rsr);
			symr = rsr.getString("stock_symbol");
			System.out.println("Symbol Value in DAO :: " + symr);
			System.out.println("Symbol Value in DAO :: " + rsr.getString("stock_symbol"));
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		psr.close();
		rsr.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
		return symr;
	}
	
	
	
	
	
	public static double getTotalBalance(String uname)
	{
		System.out.println("Getting Balance of User :: " + uname);
		PreparedStatement psr = null;
		Connection conr = null;
		ResultSet rsr = null;
		String sqlr;
		double balance=0.0;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			conr = DatabaseConnection.getConnection();
			
			sqlr = "select * from customer where username='"+ uname +"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			balance = rsr.getDouble("totalbalance");
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		psr.close();
		rsr.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
		return balance;
	}
	
	
	
	
	public static void updateBalance(double amt, String uname)
	{
		PreparedStatement st = null;
		Connection conr = null;
		boolean rsr;
		String sqlr;
		double balance=0.0;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			conr = DatabaseConnection.getConnection();
			
			sqlr = "update customer set totalbalance =" + amt +" where username='"+ uname +"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			st = conr.prepareStatement(sqlr);
			System.out.println("PreparedStatement Successful" + st);
			
			rsr = st.execute();
			st.close();
			
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		st.close();
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
	}
	
	
	
	public static void insertIntoCustomerSell(String name,String symbol,int qty,double price,double amount,Date date, String time)
	{
		PreparedStatement st = null;
		Connection conr = null;
		boolean rsr;
		String sqlr;
		double balance=0.0;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			
			conr = DatabaseConnection.getConnection();
			sqlr = "insert into customersell(user,stock_symbol,qty,price,amt,date,timestamp)	VALUES(?,?,?,?,?,?,?)";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			st = conr.prepareStatement(sqlr);
			
			st.setString(1,name);
			st.setString(2, symbol);
			st.setInt(3, qty);
			st.setDouble(4, price);
			st.setDouble(5, amount);
			st.setDate(6, date);
			st.setString(7, time);
			System.out.println("PreparedStatement Successful" + st);
			
			rsr = st.execute();
			st.close();
			
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		st.close();
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
	}
	
	
	
	
	public static int getDBQuantity(String sym, String user)
	{
		PreparedStatement psr = null;
		Connection conr = null;
		ResultSet rsr = null;
		String sqlr;
		int quan=0;
		String symr = null;
		List<StockAPIDao> list = new ArrayList<StockAPIDao>();
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			
			conr = DatabaseConnection.getConnection();
			sqlr = "select * from customerstocks where customername='"+ user + "' && stock_symbol='" + sym +"' ";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed :: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			System.out.println("Rs :: " + rsr);
			quan = rsr.getInt("quantity");
			System.out.println("Symbol Value in DAO :: " + symr);
			System.out.println("Symbol Value in DAO :: " + rsr.getString("stock_symbol"));
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		psr.close();
		rsr.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
		return quan;
	}
	
	
	
	public static boolean sellCommandTable(String sym, int quan, String uname)
	{
		int originalQuan = getQuantitycommonTable(sym, uname);
		int updatedQuan = originalQuan - quan;
		if(updatedQuan >= 0 && originalQuan >= quan)
		{
				PreparedStatement st = null;
				Connection conr = null;
				boolean rsr;
				String sqlr;
				double balance=0.0;
				try
				{
					com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
		//			conr = ds.getConnection();
					conr = DatabaseConnection.getConnection();
					
					sqlr = "update customerstocks set quantity="+updatedQuan+" where stock_symbol ='" + sym +"' && customername='"+ uname +"'";
							//+ " uid=" + stockid +"";
					
					System.out.println("Sql formed for getting Balance:: " + sqlr);
					st = conr.prepareStatement(sqlr);
					System.out.println("PreparedStatement Successful" + st);
					
					rsr = st.execute();
					st.close();
				return true;
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		st.close();
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
				}
		else
		{
			System.out.println("not enough.");
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You do not have enough quantity to sell.",""));
		}
		return false;
		
	}
	
	public static int getQuantitycommonTable(String sym, String uname)
	{
		PreparedStatement st = null;
		Connection conr = null;
		ResultSet rsr;
		String sqlr;
		int quan = 0;
		double balance=0.0;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
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
//			conr = ds.getConnection();
			conr = DatabaseConnection.getConnection();
			
			sqlr = "select * from customerstocks where stock_symbol ='" + sym +"' && customername='"+ uname +"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			st = conr.prepareStatement(sqlr);
			System.out.println("PreparedStatement Successful" + st);
			
			rsr = st.executeQuery();
			while(rsr.next())
			{
				quan = rsr.getInt("quantity");
			}
			
			st.close();
			return quan;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
		try
		{
		DatabaseConnection.close(conr);
		st.close();
		
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		return 0;
	}
	
	
}
