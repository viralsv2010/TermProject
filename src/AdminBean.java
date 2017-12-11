import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ManagedBean(name = "AdminBean")
@SessionScoped
public class AdminBean {
	private String firstname;
	private String lastname;
	private String address;
	private Boolean flagVal;
	private int userID;

public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
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


	public Boolean getFlagVal() {
		return flagVal;
	}


	public void setFlagVal(Boolean flagVal) {
		this.flagVal = flagVal;
	}


	public void changeApprove(String uname)
	{
		System.out.println("Username :: " + uname);
		System.out.println("Flagvalu :: " + flagVal);
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String sql;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//			System.out.println("Datasource successful.");
			ds.setServerName(System.getenv("ICSI518_SERVER"));
//			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
			ds.setUser(System.getenv("ICSI518_USER"));
	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
		
			// Get a connection object
			con = ds.getConnection();
			if(flagVal == false)
			{
				sql = "update user set flagbyadmin=" + 1 + " where user_id= '" + uname +"'";
			}
			else
			{
				sql = "update user set flagbyadmin=" + 0 + " where user_id= '" + uname +"'";
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
		con.close();
		ps.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		
	}
public List<AdminBean> getUserList()
{
		List<AdminBean> list = new ArrayList<AdminBean>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		try
		{
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//			System.out.println("Datasource successful.");
			ds.setServerName(System.getenv("ICSI518_SERVER"));
//			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
			ds.setUser(System.getenv("ICSI518_USER"));
	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
		
			// Get a connection object
			con = ds.getConnection();
		
		String sql = "select * from user";
		ps= con.prepareStatement(sql); 
		rs= ps.executeQuery(); 
		while (rs.next())
		{
			AdminBean usr = new AdminBean();
			usr.setFirstname(rs.getString("firstname"));
			usr.setLastname(rs.getString("lastname"));
			usr.setAddress(rs.getString("address"));
			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
			usr.setUserID(rs.getInt("user_id"));
			
		list.add(usr);
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
		con.close();
		ps.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		return list;
	}
}
