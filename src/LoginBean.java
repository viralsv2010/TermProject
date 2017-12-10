import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@ManagedBean(name = "LoginBean")
@SessionScoped

public class LoginBean {

	private String username;
	private String password;
	private String firstname;
	private String lastname,address,email,phonenumber,pass_word,user_name,role;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
	public String getfirstname() {
		return firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String addUser()
	{
		Connection con = null;
		boolean rs=true;
		try{
			// Setup the DataSource object
			
		
			
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
			String sql = "insert into user(firstname,lastname,address,email,phonenumber,username,password,role)	VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println("PreparedStatement Successful");
			st.setString(1,firstname);
			st.setString(2,lastname);
			st.setString(3,address);
			st.setString(5,email);
			st.setString(4,phonenumber);
			st.setString(6,username);
			st.setString(7,password);
			st.setString(8,role);

			// Execute the statement
			 rs = st.execute();
			 System.out.println("Resultset Value is :: " + rs);
		}

		catch(Exception e){
			e.printStackTrace();
		}

		finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered Successfully","Registered Successfully");        
//		FacesContext.getCurrentInstance().addMessage(null, message);      
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("loginform:temp", message);
		
		context.getExternalContext().getFlash().setKeepMessages(true);

//		if(role.equals("Customer"))
//		{
//			System.out.println("Inside Customer Role.");
//			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//			try {
//				ec.redirect(ec.getRequestContextPath() + "/" + page1);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//return "./../customer/CustomerDashboard.xhtml?faces-redirect=true";
//			return null;
//		}
//		else
//		{
//			System.out.println("Inside Manager Role.");
//			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//			try {
//				ec.redirect(ec.getRequestContextPath() + "/" + page2);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//return "./../customer/CustomerDashboard.xhtml?faces-redirect=true";
//			return null;
//		}
		return "Login.xhtml?faces-redirect=true";
	}


	public void checkUsernameandPassword(String u_name)
	{
		System.out.println("Inside checkUsernameandPassword");
		if(u_name!= null && password!=null)
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
			
			Connection con= null;
			PreparedStatement st= null;
			ResultSet rs= null;


				try
				{
					con= ds.getConnection();
					System.out.println("Connection Successful");
					String sql= "select username,password from user where username= '"+ u_name +"'";
					PreparedStatement st1 = con.prepareStatement(sql);
					
					System.out.println("Prepared Statement is :: " + st1);
					// Execute the statement
					ResultSet rs1 = st1.executeQuery();
					System.out.println("Resultset is :: " + rs1);
					// Iterate through results
					if (rs1.next()) {
						System.out.println("User Name in rs is :: " + rs1.getString("username"));
						user_name = rs1.getString("username");
						pass_word = rs1.getString("password");

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
						} catch (SQLException e) {
					}
				}
			}
		
		}

	

	public String checkLogin()
	{
		System.out.println("Inside CheckLogin. Username is ::" + username + "Password is ::" + password); 
		checkUsernameandPassword(username);
		System.out.println("User_name is :: " + user_name + " Username is :: " + username + " Pass_word is :: " + pass_word + " Password is :: "+ password);
		String page1 = "customer/CustomerDashboard.xhtml";
		String page2 = "manager/ManagerDashboard.xhtml";
		if(user_name.equals(username) && pass_word.equals(password))
		{
			if(role.equals("Customer"))
			{
				System.out.println("Inside Customer Role.");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath() + "/" + page1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				System.out.println("Inside Manager Role.");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/" + page2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//return "./../customer/CustomerDashboard.xhtml?faces-redirect=true";
			return null;
		}
		else
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Username or Password is incorrect.",null);        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			return "invalidLogin";
		}
	}
//	public String ValidateUser()
//	{
//		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
//		{
//			clear();
//			return "matched";
//		}
//		else
//		{
//			clear();
//			return "unmatched";
//		}
//	}

	public void clear(){
		setPassword(null);
		setUsername(null);
	}
}
