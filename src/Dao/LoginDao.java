package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
		
	static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	
		public static Boolean insert(String firstname, String lastname,String address,String email,String phonenumber,String username,String password,String role)
		{
			try
			{
				System.out.println("In Dao of Try Insert.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
					System.out.println("Datasource successful.");
					ds.setServerName(System.getenv("ICSI518_SERVER"));
					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
					ds.setDatabaseName(System.getenv("ICSI518_DB"));
					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
					ds.setUser(System.getenv("ICSI518_USER"));
					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
		
					// Get a connection object
					con = ds.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sql;
//					String sqlLogin;
					System.out.println("Role before Registration Insert." + role);
					if(role.equals("Customer"))
					{
						System.out.println("Inside Customer Insert");
						sql = "insert into customer(firstname,lastname,address,email,phonenumber,username,password,role)	VALUES(?,?,?,?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					else
					{
						System.out.println("Inside Manager Insert");
						sql = "insert into manager(firstname,lastname,address,email,phonenumber,username,password,role)	VALUES(?,?,?,?,?,?,?,?)";
//						System.out.println("Before Inserting into user");
//						sqlLogin = "insert into user(username,password) VALUES(?,?)";
					}
					PreparedStatement st = con.prepareStatement(sql);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);
					st.setString(1,firstname);
					st.setString(2,lastname);
					st.setString(3,address);
					st.setString(5,email);
					st.setString(4,phonenumber);
					st.setString(6,username);
					st.setString(7,password);
					st.setString(8,role);
//					st1.setString(1, username);
//					st1.setString(2,password);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
					 System.out.println("Resultset Value is :: " + rs1);
					 st.close();
					 con.close();
					 
					 return true;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return false;
			}
		}


		public static Boolean inserIntoUser(String username,String password, String role)
		{
			try
			{
				System.out.println("In Dao of User Insert.");
					Connection con = null;
					boolean rs=true;
					boolean rs1 = true;
					System.out.println("Datasource successful.");
					ds.setServerName(System.getenv("ICSI518_SERVER"));
					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
					ds.setDatabaseName(System.getenv("ICSI518_DB"));
					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
					ds.setUser(System.getenv("ICSI518_USER"));
					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
		
					// Get a connection object
					con = ds.getConnection();
					System.out.println("Connection Successful");
					// Get a prepared SQL statement
					String sqlLogin = "insert into user(username,password,role) VALUES(?,?,?)";
					PreparedStatement st = con.prepareStatement(sqlLogin);
//					PreparedStatement st1 = con.prepareStatement(sqlLogin);
					System.out.println("PreparedStatement Successful" + st);

					st.setString(1,username);
					st.setString(2,password);
					st.setString(3, role);
		
					// Execute the statement
					 rs = st.execute();
					 System.out.println("Resultset Value is :: " + rs);
				
					 st.close();
					 con.close();
					 
					 return true;
			}
			catch(SQLException  e){
				// TODO Auto-generated catch block
				System.out.println("Catch of Dao");
				e.printStackTrace();
				return false;
			}
		}
		
		
		
		
		public static String checkLogin(String user_name,String pass_word)
		{
			Connection con= null;
			PreparedStatement st= null;
			ResultSet rs= null;
			try
			{
				if(user_name!= null && pass_word!=null)
				{
					System.out.println("Datasource successful.");
					ds.setServerName(System.getenv("ICSI518_SERVER"));
					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
					ds.setDatabaseName(System.getenv("ICSI518_DB"));
					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
					ds.setUser(System.getenv("ICSI518_USER"));
					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
					



					con= ds.getConnection();
							System.out.println("Connection Successful");
							String sql= "select username,password from user WHERE username= '" + user_name + "'and password= '" + pass_word + "'";
							PreparedStatement st1 = con.prepareStatement(sql);
							
							System.out.println("Prepared Statement is :: " + st1);
							// Execute the statement
							ResultSet rs1 = st1.executeQuery();
							System.out.println("Resultset is :: " + rs1);
							// Iterate through results
							if (rs1.next()) {
								System.out.println("Dao Database User Name in rs is :: " + rs1.getString("username") + "Password is ::" + rs1.getString("password"));
								String username = rs1.getString("username");
								String password = rs1.getString("password");
								return password;

							}
						}

						
						}
			catch(Exception e)
			{
				 System.out.println("Exception for Not matched Username and Password." + e);
				 e.printStackTrace();
			}

			finally {
				try {
					con.close();
				}
				catch (SQLException e) {
				}
			}
			return null;
		}
		
		
		
		public static String getRoleValue(String username)
		{
			Connection con= null;
			PreparedStatement st= null;
			ResultSet rs= null;
			String sql = null;
			String role=null;
			try{
					System.out.println("Datasource successful.");
					ds.setServerName(System.getenv("ICSI518_SERVER"));
					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
					ds.setDatabaseName(System.getenv("ICSI518_DB"));
					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
					ds.setUser(System.getenv("ICSI518_USER"));
					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
					
		
		
		
					con= ds.getConnection();
							System.out.println("Connection Successful");
							System.out.println("Finding Role for the username :: " + username);
							sql= "select role from user where username= '"+ username +"'";
							PreparedStatement st1 = con.prepareStatement(sql);
							
							System.out.println("Prepared Statement is :: " + st1);
							// Execute the statement
							ResultSet rs1 = st1.executeQuery();
							System.out.println("Resultset is :: " + rs1);
							// Iterate through results
							if (rs1.next()) {
								
								role = rs1.getString("role");
								System.out.println("Role in rs is :: " + rs1.getString("role"));
							}
							
				}
			catch(Exception e)
			{
				 System.out.println("Exception for Not matched role" + e);
				 e.printStackTrace();
			}
			return role;
		}
		
		
		public static boolean chekFlagofManager(String manager)
		{
			System.out.println("Checking boolean flag for the Manager :: " + manager);
			
			Connection con= null;
			PreparedStatement st= null;
			ResultSet rs= null;
			String sql = null;
			Boolean flagbyadmin=null;
			try{
					System.out.println("Datasource successful.");
					ds.setServerName(System.getenv("ICSI518_SERVER"));
					System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
					System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
					ds.setDatabaseName(System.getenv("ICSI518_DB"));
					System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
					ds.setUser(System.getenv("ICSI518_USER"));
					System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
					System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
					
		
		
		
					con= ds.getConnection();
							System.out.println("Connection Successful");
							System.out.println("Finding Role for the username :: " + manager);
							sql= "select flagbyadmin from manager where username= '"+ manager +"'";
							PreparedStatement st1 = con.prepareStatement(sql);
							
							System.out.println("Prepared Statement is :: " + st1);
							// Execute the statement
							ResultSet rs1 = st1.executeQuery();
							System.out.println("Resultset is :: " + rs1);
							// Iterate through results
							if (rs1.next()) {
								
								flagbyadmin = rs1.getBoolean("flagbyadmin");

							}
							
				}
			catch(Exception e)
			{
				 System.out.println("Exception for Not matched role" + e);
				 e.printStackTrace();
			}
			return flagbyadmin;
		}
}
