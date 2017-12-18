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
import javax.servlet.http.HttpSession;






@ManagedBean(name = "LoginBean")
@SessionScoped

public class LoginBean {

	private String username;
	private String password;
	private String firstname;
	private String lastname,address,email,phonenumber,role;
	private double fee;
	private boolean isManager;
	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getRole() {
		System.out.println("inside getrole with value :" + role);
		return role;
	}

	public void setRole(String role) {
		System.out.println("inside getrole with value :" + role);
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
			
			boolean valid =LoginDao.insert(firstname,lastname,address,email,phonenumber,username,password,role,fee);
			boolean validUser = LoginDao.inserIntoUser(username,password,role);
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


	public String checkUsernameandPassword(String u_name,String pass_word) throws SQLException
	{
		System.out.println("Inside checkUsernameandPassword");
		
		String pass = LoginDao.checkLogin(u_name, pass_word);

		return pass;
		
		}

	

	public String checkLogin() throws SQLException
	{
		System.out.println("Inside CheckLogin. Username is ::" + username + "Password is ::" + password); 
		String pass = checkUsernameandPassword(username,password);
		System.out.println("Password from Database :: " + pass);
		System.out.println(" Username is :: " + username + " Password is :: "+ password);
		String page1 = "customer/CustomerDashboard.xhtml";
		String page2 = "manager/ManagerDashboard.xhtml";
		String page3 = "admin/AdminDashboard.xhtml";
		if(username.equals(username) && password.equals(pass))
		{
//			FacesContext context1 = FacesContext.getCurrentInstance();
//			context1.getExternalContext().getSessionMap().put("user", username);
//			   HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//			   session.setAttribute("user", username);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("user", username);
			
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
			else if(roleVal.equals("admin"))
			{
				System.out.println("Inside Admin Role.");
				System.out.println("Inside Customer Role.");
				ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
				try {
					ec.redirect(ec.getRequestContextPath() + "/" + page3);
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				System.out.println("Inside Manager Role.");
				boolean flagAcceptancebyAdmin = LoginDao.chekFlagofManager(username);
				System.out.println("Flag of Manager by Admin in Controller :: " + flagAcceptancebyAdmin);
				
				if(flagAcceptancebyAdmin)
				{
					ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
					try {
						ec.redirect(ec.getRequestContextPath() + "/" + page2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Admin did not approved your request Yet.","Admin did not approved your request Yet.");        
//					FacesContext.getCurrentInstance().addMessage(null, message);      
//					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage("loginform:temp", message);
					
					context.getExternalContext().getFlash().setKeepMessages(true);
					System.out.println("Manager is not accepted so cannot able to Login.");
					return "Login.xhtml?faces-redirect=true";
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
