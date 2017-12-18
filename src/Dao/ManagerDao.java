package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {

	static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();

	
	private static String stockname;
	public static String getStockname() {
		return stockname;
	}

	public static void setStockname(String stockname) {
		ManagerDao.stockname = stockname;
	}

	public static double getStockprice() {
		return stockprice;
	}
	public static void setStockprice(double stockprice) {
		ManagerDao.stockprice = stockprice;
	}
	public static int getStockqty() {
		return stockqty;
	}
	public static void setStockqty(int stockqty) {
		ManagerDao.stockqty = stockqty;
	}
	public static double getStockamt() {
		return stockamt;
	}
	public static void setStockamt(double stockamt) {
		ManagerDao.stockamt = stockamt;
	}
	private static double stockprice;
	private static int stockqty;
	private static double stockamt;

	public static boolean insertIntoManagerSell(String name,String symbol,int qty,double price,double amount,Date date, String time, String manager)
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
//			
			conr = DatabaseConnection.getConnection();
			sqlr = "insert into managersell(customername,stock_symbol,qty,price,amt,date,timestamp,purchasedbywhom,managername)	VALUES(?,?,?,?,?,?,?,?,?)";
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
			st.setString(8, manager);
			st.setString(9, manager);
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
		return false;
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
			
			sqlr = "select * from customermanagersellrequest where uid="+ stockid + "";
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
	
	
	
	
	
	
	
	
	public static List<StockAPIDao> getCurrentDetails(String sym, String username)
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
			sql = "select * from customerpurchase where stock_symbol='" + sym +"' && user='" + username +"'";
			
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
	
	
	
	public static Boolean insertintoWatchlist(String uname, String symbol)
	{
		System.out.println("Insert into Watchlist DAO.");
		try
		{
			System.out.println("In Dao of Stock Watchlist.");
				Connection con = null;
				boolean rs=true;
				boolean rs1 = true;
//				System.out.println("Datasource successful.");
//				ds.setServerName(System.getenv("ICSI518_SERVER"));
//				System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//				ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//				System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//				ds.setDatabaseName(System.getenv("ICSI518_DB"));
//				System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//				ds.setUser(System.getenv("ICSI518_USER"));
//				System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//				ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//				System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//	
//				// Get a connection object
//				con = ds.getConnection();
				con = DatabaseConnection.getConnection();
				System.out.println("Connection Successful");
				// Get a prepared SQL statement
				String sql;
				PreparedStatement st;
					System.out.println("Inside Customer Insert");
					sql = "insert into managerwatchlist(username,stock_symbol) VALUES(?,?)";
//					System.out.println("Before Inserting into user");
//					sqlLogin = "insert into user(username,password) VALUES(?,?)";
					st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					st.setString(1,uname);
					st.setString(2, symbol);
			

//				st1.setString(1, username);
//				st1.setString(2,password);
	
				// Execute the statement
				 rs = st.execute();
				 System.out.println("Resultset Value is :: " + rs);
				 System.out.println("Resultset Value is :: " + rs1);
				 st.close();
				 DatabaseConnection.close(con);
				 
				 return true;
		}
		catch(SQLException  e){
			// TODO Auto-generated catch block
			System.out.println("Catch of Dao");
			e.printStackTrace();
			return false;
		}
	}

	public boolean removeFromWatchlist(int id) {
		// TODO Auto-generated method stub
		System.out.println("Delete Watchlist DAO.");
		try
		{
				Connection con = null;
				boolean rs=true;
				boolean rs1 = true;
//				System.out.println("Datasource successful.");
//				ds.setServerName(System.getenv("ICSI518_SERVER"));
//				System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//				ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//				System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//				ds.setDatabaseName(System.getenv("ICSI518_DB"));
//				System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//				ds.setUser(System.getenv("ICSI518_USER"));
//				System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//				ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//				System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//	
//				// Get a connection object
//				con = ds.getConnection();
				con = DatabaseConnection.getConnection();
				System.out.println("Connection Successful");
				// Get a prepared SQL statement
				String sql;
				PreparedStatement st;
					System.out.println("Inside Customer Insert");
					sql = "delete from managerwatchlist where uid='" + id +"'";
					st = con.prepareStatement(sql);
					System.out.println("PreparedStatement Successful" + st);

				 rs = st.execute();
				 System.out.println("Resultset Value is :: " + rs);
				 System.out.println("Resultset Value is :: " + rs1);
				 st.close();
				 DatabaseConnection.close(con);
				 
				 return true;
		}
		catch(SQLException  e){
			// TODO Auto-generated catch block
			System.out.println("Catch of Dao");
			e.printStackTrace();
			return false;
		}
	}

	public double getManagerFee(String managerName) {
		// TODO Auto-generated method stub
		PreparedStatement psr = null;
		Connection conr = null;
		ResultSet rsr = null;
		String sqlr;
		double fee = 0.0;
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
			sqlr = "select * from manager where username='"+ managerName + "'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed :: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			System.out.println("Rs :: " + rsr);
			fee = rsr.getDouble("fee");
			System.out.println("Symbol Value in DAO :: " + fee);
			System.out.println("Symbol Value in DAO :: " + rsr.getString("fee"));
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
		
		return fee;
	}	
	
	
	
	public static double getTotalBalance(String uname)
	{
		System.out.println("Manager Getting Balance of User :: " + uname);
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
			
			sqlr = "select * from manager where username='"+ uname +"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			while(rsr.next())
			{
				balance = rsr.getDouble("totalbalance");
			}
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
			
			sqlr = "update manager set totalbalance =" + amt +" where username='"+ uname +"'";
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
	
	public static double getRequestBalanceBuy(String customer, String manager)
	{
		System.out.println("Manager Getting Requested Balance of User :: " + customer);
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
			
			sqlr = "select * from customermanagerbuyrequest where customername='"+ customer +"' && managername='"+manager+"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			balance = rsr.getDouble("remainingAmount");
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

	public static void updateRemainingBalanceBuy(double remainingBalance, String customer, String manager) {
		// TODO Auto-generated method stub
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
			
			sqlr = "update customermanagerbuyrequest set remainingAmount =" + remainingBalance +" where customername='"+ customer +"' && managername='"+manager+"'";
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

	public static double getRequestBalanceSell(String custName, String managerName) {
		// TODO Auto-generated method stub
		System.out.println("Manager Getting Requested Balance of User :: " + custName);
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
			
			sqlr = "select * from customermanagersellrequest where customername='"+ custName +"' && managername='"+managerName+"'";
					//+ " uid=" + stockid +"";
			
			System.out.println("Sql formed for getting Balance:: " + sqlr);
			psr= conr.prepareStatement(sqlr); 
			rsr = psr.executeQuery();
			rsr.next();
			balance = rsr.getDouble("remainingAmount");
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

	public void updateManagerProfiles(String value, String field, String managerName) {
		// TODO Auto-generated method stub
		PreparedStatement st = null;
		Connection conr = null;
		boolean rsr;
		String sqlr;
		conr = DatabaseConnection.getConnection();
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
			
			sqlr = "update manager set '"+ field + "'= '"+value+"' where username= '"+managerName+"' ";
			System.out.println("Manage profile update URL :: " + sqlr);
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
	
}
