package Dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
		
	static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	
		public static Boolean update(String firstname, String lastname,String address,String email,int phonenumber, String role)
		{
			try
			{
				System.out.println("In Dao of Try Insert.");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set firstname= '"+ firstname + "' lastname = '"+ lastname + "' + address  + '"+ address + "' + email '"+ email + "' + phonenumber + '"+ phonenumber + "'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set firstname= '"+ firstname + "' lastname = '"+ lastname + "' + address  + '"+ address + "' + email '"+ email + "' + phonenumber + '"+ phonenumber + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		
		
		
		public static String getRole(String uname)
		{
			try
			{
				System.out.println("In Dao of getting Role");
					Connection con = null;
					ResultSet rs=null;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from user where username= '" + uname +"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 rs.next();
					 String rol = rs.getString("role");
					 System.out.println("Resultset Value is :: " + rs);
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return rol;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return null;
			}
		}
		
		
		
		public static Boolean updatefirstname(String firstname, String role, String username)
		{
			try
			{
				System.out.println("Inside DAO update firstname");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role after Connection :: " + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set firstname= '"+ firstname + "' where username= '" + username + "'";
						
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set firstname= '"+ firstname + "' where username= '" + username + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					System.out.println("Update SQL Formed :: " + sql);
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		
		
		public static Boolean updatelastname(String lastname, String role, String username)
		{
			try
			{
				System.out.println("Inside DAO update firstname");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role after Connection :: " + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set lastname= '"+ lastname + "' where username= '" + username + "'";
						
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set lastname= '"+ lastname + "' where username= '" + username + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					System.out.println("Update SQL Formed :: " + sql);
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		
		public static Boolean updateaddress(String address, String role, String username)
		{
			try
			{
				System.out.println("Inside DAO update firstname");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role after Connection :: " + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set address= '"+ address + "' where username= '" + username + "'";
						
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set address= '"+ address + "' where username= '" + username + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					System.out.println("Update SQL Formed :: " + sql);
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		public static Boolean updatephonenumber(String phonenumber, String role, String username)
		{
			try
			{
				System.out.println("Inside DAO update firstname");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role after Connection :: " + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set phonenumber= '"+ phonenumber + "' where username= '" + username + "'";
						
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set phonenumber= '"+ phonenumber + "' where username= '" + username + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					System.out.println("Update SQL Formed :: " + sql);
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		
		
		
		
		public static Boolean updateemail(String email, String role, String username)
		{
			try
			{
				System.out.println("Inside DAO update firstname");
					Connection con = null;
					boolean rs=true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();
					

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role after Connection :: " + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "update customer set email= '"+ email + "' where username= '" + username + "'";
						
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "update manager set email= '"+ email + "' where username= '" + username + "'";
						//sql = "insert into manager(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					System.out.println("Update SQL Formed :: " + sql);
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
//					st.setString(1,firstname);
//					st.setString(2,lastname);
//					st.setString(3,address);
//					st.setString(4,email);
//					st.setInt(5,phonenumber);
//					st.setString(6,username);
//					st.setString(7,password);
//					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
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
		
		
		public static Boolean insertintoWatchlist(String uname, String symbol)
		{
			System.out.println("Insert into Watchlist DAO.");
			try
			{
				System.out.println("In Dao of Stock Watchlist.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					PreparedStatement st;
						System.out.println("Inside Customer Insert");
						sql = "insert into customerwatchlist(username,stock_symbol) VALUES(?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
						st = con.prepareStatement(sql);
//						PreparedStatement st1 = con.prepareStatement(sqlLogin);
						System.out.println("PreparedStatement Successful" + st);
						st.setString(1,uname);
						st.setString(2, symbol);
				

//					st1.setString(1, username);
//					st1.setString(2,password);
		
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
		
		
		public static Boolean removeFromWatchlist(int id)
		{
			System.out.println("Delete Watchlist DAO.");
			try
			{
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					PreparedStatement st;
						System.out.println("Inside Customer Insert");
						sql = "delete from customerwatchlist where uid='" + id +"'";
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
		
		
		
		
		public static Boolean insertSelectedManager(String uname, String managerUsername)
		{
			System.out.println("Insert into Watchlist DAO.");
			try
			{
				System.out.println("In Dao of Stock Watchlist.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					PreparedStatement st;
						System.out.println("Inside Customer Insert");
						sql = "insert into customerselectedmanager(customername,managername,selectedFlag) VALUES(?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
						st = con.prepareStatement(sql);
//						PreparedStatement st1 = con.prepareStatement(sqlLogin);
						System.out.println("PreparedStatement Successful" + st);
						st.setString(1, uname);
						st.setString(2, managerUsername);
						st.setBoolean(3, true);
				

//					st1.setString(1, username);
//					st1.setString(2,password);
		
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




		public static boolean customermanagerbuyrequest(String manager,double amount, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of Stock Watchlist.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					PreparedStatement st;
						System.out.println("Inside Customer Insert");
						sql = "insert into customermanagerbuyrequest(customername,managername,amount,remainingAmount) VALUES(?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
						st = con.prepareStatement(sql);
//						PreparedStatement st1 = con.prepareStatement(sqlLogin);
						System.out.println("PreparedStatement Successful" + st);
						st.setString(1, name);
						st.setString(2, manager);
						st.setDouble(3, amount);
						st.setDouble(4, amount);
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
		
		
		public static boolean customermanagersellrequest(String manager,double amount, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of Stock Watchlist.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
//					System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//		
//					// Get a connection object
//					con = ds.getConnection();

					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					PreparedStatement st;
						System.out.println("Inside Customer Insert");
						sql = "insert into customermanagersellrequest(customername,managername,amount,remainingAmount) VALUES(?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
						st = con.prepareStatement(sql);
//						PreparedStatement st1 = con.prepareStatement(sqlLogin);
						System.out.println("PreparedStatement Successful" + st);
						st.setString(1, name);
						st.setString(2, manager);
						st.setDouble(3, amount);
						st.setDouble(4, amount);
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




		public boolean checkSelected(String name, String managerUsername) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of getting Selected Manager");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customerselectedmanager where customername= '" + name +"' && managername='"+managerUsername+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 while(rs.next())
					 {
						 System.out.println("Resultset Value is :: " + rs);
						 return true;
					 }
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return false;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
		}




		public static boolean checkentryforcustomermanagerbuyrequest(String username, String manager) {
			// TODO Auto-generated method stub
			
			try
			{
				System.out.println("In Dao of checking buy request.");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagerbuyrequest where customername= '" + username +"' && managername='"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 while(rs.next())
					 {
						 System.out.println("Resultset Value is :: " + rs);
						 return true;
					 }
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return false;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
			
		}	


		
		
		public static boolean checkentryforcustomermanagersellrequest(String username, String manager) {
			// TODO Auto-generated method stub
			
			try
			{
				System.out.println("In Dao of checking buy request.");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagersellrequest where customername= '" + username +"' && managername='"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 while(rs.next())
					 {
						 System.out.println("Resultset Value is :: " + rs);
						 return true;
					 }
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return false;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
			
		}	

		
		

		
		public static boolean updatecustomermanagerbuyrequest(String username, String manager) {
			// TODO Auto-generated method stub
			
			try
			{
				System.out.println("In Dao of checking buy request.");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customerselectedmanager where customername= '" + username +"' && managername='"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 while(rs.next())
					 {
						 System.out.println("Resultset Value is :: " + rs);
						 return true;
					 }
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return false;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
			
		}




		public static double getBuyRequestAmount(String manager, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of getting Role");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					double amount = 0;
				
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagerbuyrequest where customername= '" + name +"' && managername = '"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 
					 while(rs.next())
					 {
						 amount = rs.getDouble("amount");
					 }
					 
					 System.out.println("Resultset Value is :: " + rs);
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return amount;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return 0;
			}
		}	


		
		public static double getSellRequestAmount(String manager, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of getting Role");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					double amount = 0;
				
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagersellrequest where customername= '" + name +"' && managername = '"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 
					 while(rs.next())
					 {
						 amount = rs.getDouble("amount");
					 }
					 
					 System.out.println("Resultset Value is :: " + rs);
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return amount;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return 0;
			}
		}	

		
		
		
		
		
		
		
		public static double getBuyRequestRemainingAmount(String manager, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of getting Role");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					double amount = 0;
				
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagerbuyrequest where customername= '" + name +"' && managername = '"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 
					 while(rs.next())
					 {
						 amount = rs.getDouble("remainingAmount");
					 }
					 
					 System.out.println("Resultset Value is :: " + rs);
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return amount;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return 0;
			}
		}

		
		
		public static double getSellRequestRemainingAmount(String manager, String name) {
			// TODO Auto-generated method stub
			try
			{
				System.out.println("In Dao of getting Role");
					Connection con = null;
					ResultSet rs=null;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
					double amount = 0;
				
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "select * from customermanagersellrequest where customername= '" + name +"' && managername = '"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.executeQuery();
					 
					 while(rs.next())
					 {
						 amount = rs.getDouble("remainingAmount");
					 }
					 
					 System.out.println("Resultset Value is :: " + rs);
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return amount;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return 0;
			}
		}




		public static boolean updatecustomermanagerbuyrequest(String manager, String name, double updatedAmt,
				double updatedRemainingAmt) {
			// TODO Auto-generated method stub
			
			try
			{
				System.out.println("In Dao of changing Amount.");
					Connection con = null;
					boolean rs;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "update customermanagerbuyrequest set amount='"+ updatedAmt +"', remainingAmount='"+ updatedRemainingAmt +"' where customername= '" + name +"' && managername='"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.execute();
					 
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return true;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
		
		}	

		
		
		
		public static boolean updatecustomermanagersellrequest(String manager, String name, double updatedAmt,
				double updatedRemainingAmt) {
			// TODO Auto-generated method stub
			
			try
			{
				System.out.println("In Dao of changing Amount.");
					Connection con = null;
					boolean rs;
					con= DatabaseConnection.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
				
						System.out.println("Inside Customer Insert");
						sql = "update customermanagersellrequest set amount='"+ updatedAmt +"', remainingAmount='"+ updatedRemainingAmt +"' where customername= '" + name +"' && managername='"+manager+"'";
						//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
				
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					// Execute the statement
					 rs = st.execute();
					 
					 
					 st.close();
					 DatabaseConnection.close(con);
					 
					 return true;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
			}
			
			
			return false;
		
		}	

		
		
		
}