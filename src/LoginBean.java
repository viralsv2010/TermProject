import Dao.LoginDao;


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
		
		
		try{
			// Setup the DataSource object
			
			boolean valid =LoginDao.insert(firstname,lastname,address,email,phonenumber,username,password,role);
			if(valid)
			{
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered Successfully","Registered Successfully");        
//				FacesContext.getCurrentInstance().addMessage(null, message);      
//				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("loginform:temp", message);
				
				context.getExternalContext().getFlash().setKeepMessages(true);
				return "Login.xhtml?faces-redirect=true";
			}
			
		}

		catch(Exception e){
			e.printStackTrace();
		}

		return "Registration.xhtml?faces-redirect=true";


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

	}


	public void checkUsernameandPassword(String u_name)
	{
		System.out.println("Inside checkUsernameandPassword");
		
		boolean valid = LoginDao.checkLogin(u_name, password);

		
		}

	

	public String checkLogin()
	{
		System.out.println("Inside CheckLogin. Username is ::" + username + "Password is ::" + password); 
		checkUsernameandPassword(username);
		System.out.println("User_name is :: " + user_name + " Username is :: " + username + " Pass_word is :: " + pass_word + " Password is :: "+ password);
		String page1 = "customer/CustomerDashboard.xhtml";
		String page2 = "manager/ManagerDashboard.xhtml";
		if(username.equals(username) && password.equals(password))
		{
			String roleVal = LoginDao.getRoleValue(username);
			System.out.println("Role Value in Controller :: " + roleVal);
			if(roleVal.equals("Customer"))
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
