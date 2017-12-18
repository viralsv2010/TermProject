

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Iterator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Dao.CustomerDao;
import Dao.LoginDao;
import Dao.StockAPIDao;

@ManagedBean(name = "CustomerBean")
@SessionScoped

public class CustomerBean {

    private static final long serialVersionUID = 1L;
    static final String API_KEY = "GJN8GUXV0BINMDWI";

    private String username;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phonenumber;
    private String role;
    private String tempRole;
	private String symbol;
    private double price;
    private int qty;
    private double amt;
    private String manager;
    private int id;
	private String table1Markup;
    private String table2Markup;
    private double fee;
    private Date date;
    private String timestamp;
    private int stockidforwatchlist;
	private String selectedSymbol;
    private List<SelectItem> availableSymbols;
    private String selectedInterval;
    private List<SelectItem> availableIntervals;
    private int stock_id;
    private double totalbal;
    private String purchasedByWhom;
    public String getPurchasedByWhom() {
		return purchasedByWhom;
	}

	public void setPurchasedByWhom(String purchasedByWhom) {
		this.purchasedByWhom = purchasedByWhom;
	}



	private List<CustomerBean> getManagerDatalist;
    

	public List<CustomerBean> getGetManagerDatalist() {
		return getManagerDatalist;
	}

	public void setGetManagerDatalist(List<CustomerBean> getManagerDatalist) {
		this.getManagerDatalist = getManagerDatalist;
	}

	public int getStockidforwatchlist() {
		return stockidforwatchlist;
	}

	public void setStockidforwatchlist(int i) {
		this.stockidforwatchlist = i;
	}

	
	
    public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}


    public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	    

    public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}




	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
    public String getPurchaseSymbol() {
        if (getRequestParameter("symbol") != null) {
            symbol = getRequestParameter("symbol");
        }
        return symbol;
    }

    public void setPurchaseSymbol(String purchaseSymbol) {
        System.out.println("func setPurchaseSymbol()");  //check
    }

    public double getPurchasePrice() {
        if (getRequestParameter("price") != null) {
            price = Double.parseDouble(getRequestParameter("price"));
            System.out.println("price: " + price);
        }
        return price;
    }

    public void setPurchasePrice(double purchasePrice) {
        System.out.println("func setPurchasePrice()");  //check
    }
    
    private String getRequestParameter(String name) {
        return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter(name);
    }

 

	public double getTotalbal() {
		return totalbal;
	}

	public void setTotalbal(double totalbal) {
		this.totalbal = totalbal;
	}

	
    @PostConstruct
    public void init() {
        //initially populate stock list
        availableSymbols = new ArrayList<SelectItem>();
        availableSymbols.add(new SelectItem("AAPL", "Apple"));
        availableSymbols.add(new SelectItem("AMZN", "Amazon LLC"));
        availableSymbols.add(new SelectItem("AR", "Antero Resources"));
        availableSymbols.add(new SelectItem("EBAY", "Ebay"));
        availableSymbols.add(new SelectItem("FB", "Facebook, Inc."));
        availableSymbols.add(new SelectItem("GOLD", "Gold"));
        availableSymbols.add(new SelectItem("GOOGL", "Google"));
        availableSymbols.add(new SelectItem("MSFT", "Microsoft"));
        availableSymbols.add(new SelectItem("SLV", "Silver"));
        availableSymbols.add(new SelectItem("TWTR", "Twitter, Inc."));

        //initially populate intervals for stock api
        availableIntervals = new ArrayList<SelectItem>();
        availableIntervals.add(new SelectItem("1min", "1min"));
        availableIntervals.add(new SelectItem("5min", "5min"));
        availableIntervals.add(new SelectItem("15min", "15min"));
        availableIntervals.add(new SelectItem("30min", "30min"));
        availableIntervals.add(new SelectItem("60min", "60min"));
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		System.out.println("Before setting Role :: " + role);
		this.role = role;
		System.out.println("After Setting role :: " + role);
		
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

	public void setPhonenumber(String string) {
		this.phonenumber = string;
	}
    public String getSelectedInterval() {
        return selectedInterval;
    }

    public void setSelectedInterval(String selectedInterval) {
        this.selectedInterval = selectedInterval;
    }

    public List<SelectItem> getAvailableIntervals() {
        return availableIntervals;
    }

    public void setAvailableIntervals(List<SelectItem> availableIntervals) {
        this.availableIntervals = availableIntervals;
    }

    public String getSelectedSymbol() {
        return selectedSymbol;
    }

    public void setSelectedSymbol(String selectedSymbol) {
        this.selectedSymbol = selectedSymbol;
    }

    public List<SelectItem> getAvailableSymbols() {
        return availableSymbols;
    }

    public void setAvailableSymbols(List<SelectItem> availableSymbols) {
        this.availableSymbols = availableSymbols;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTable1Markup() {
        return table1Markup;
    }

    public void setTable1Markup(String table1Markup) {
        this.table1Markup = table1Markup;
    }

    public String getTable2Markup() {
        return table2Markup;
    }

    public void setTable2Markup(String table2Markup) {
        this.table2Markup = table2Markup;
    }

   
    public void logout()
    {
    	String page2 = "core/Login.xhtml";
		//System.out.println("Inside Logout.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void dashboardRedirect()
    {
    	String page2 = "customer/CustomerDashboard.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    
    public void sellStocksRedirect()
    {
    	String page2 = "customer/CustomerSell.xhtml";
	//	System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void profileRedirect()
    {
    	String page2;
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	String rol = CustomerDao.getRole(name);
    	System.out.println("getting role :: " + rol);
    	if(rol.equals("Customer"))
    	{
    		page2 = "customer/CustomerProfile.xhtml";
    	}
    	else
    	{
    		page2 = "manager/ManagerProfile.xhtml";
    	}
	//	System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void profileRedirectManager()
    {
    	String page2 = "customer/ManagerProfile.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void redirectManagerSelection()
    {
    	String page2 = "customer/CustomerSelectManager.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void redirectToWatchList()
    {
    	String page2 = "customer/CustomerWatchlist.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void redirectBalanceandHistory()
    {
    	String page2 = "customer/CustomerBalanceandHistory.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public void requestManagerforstockpurchasebuy()
    {
    	String page2 = "customer/CustomerRequestManagerBuy.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void requestManagerforstockpurchasesell()
    {
    	String page2 = "customer/CustomerRequestManagerSell.xhtml";
		//System.out.println("Inside Customer Dashboard.");
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			ec.redirect(ec.getRequestContextPath() + "/" + page2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void installAllTrustingManager() {
        TrustManager[] trustAllCerts;
        trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
    
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            System.out.println("Exception :" + e);
        }
        return;
    }
        
    public void timeseries() throws MalformedURLException, IOException {

        installAllTrustingManager();

        //System.out.println("selectedItem: " + this.selectedSymbol);
        //System.out.println("selectedInterval: " + this.selectedInterval);
        String symbol = this.selectedSymbol;
        String interval = this.selectedInterval;
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;

     //   System.out.println("URL Formed :: " + url);
       // this.table1Markup += "URL::: <a href='" + url + "'>Data Link</a><br>";
        InputStream inputStream = new URL(url).openStream();

        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for (String key : mainJsonObj.keySet()) {
      //  	System.out.println("Key val :: " + key);
            if (key.equals("Meta Data")) {
      //      	System.out.println("Inside the Meta data ......");
                this.table1Markup = null; // reset table 1 markup before repopulating
                JsonObject jsob = (JsonObject) mainJsonObj.get(key);
      //          System.out.println("JsonObject details :: " + jsob);
                
                this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                this.table1Markup += "<table>";
                this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
       //         System.out.println("Information Table :: " + this.table1Markup + "  :: " + jsob.getString("1. Information") );
                this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                this.table1Markup += "</table>";
            } else {
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + subKey + "</td>"
                            + "<td>" + subJsonObj.getString("1. open") + "</td>"
                            + "<td>" + subJsonObj.getString("2. high") + "</td>"
                            + "<td>" + subJsonObj.getString("3. low") + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>"
                            + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                        System.out.println("Buy stock button URL :: " + path);
                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/customer/CustomerPurchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
                        
                    }
                    this.table2Markup += "</tr>";
                    i++;
                }
                this.table2Markup += "</tbody></table>";
            }
        }
        return;
    }
    
    
    public List<CustomerBean> getUserList()
    {
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
		setUsername(name);
  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
  //  	System.out.println("Username in CustomerGetlsit :: " + username);
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		
    		String role = CustomerDao.getRole(name);
    		
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		
    		String sql = "select role from user where username = '" + name +"'";
    	//	System.out.println("Sql formed for getting role customerBean." + sql);
    		ps= con.prepareStatement(sql); 
    		rs= ps.executeQuery(); 
    		while (rs.next())
    		{
    			
    			CustomerBean usr = new CustomerBean();
//    			usr.setRole(rs.getString("role"));
//    			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//    			usr.setUserID(rs.getInt("user_id"));
    			//usr.setUsername(rs.getString("username"));
    			usr.setUsername(name);
    			usr.setRole(rs.getString("role"));
    	//		System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));
        		if(rs.getString("role").equals("Customer"))
        		{
        	//		System.out.println("Inside customerBean getList customer");
        			String sqlForUserdata = "select * from customer where username ='" + name +"'";
            		ps= con.prepareStatement(sqlForUserdata); 
            		rs= ps.executeQuery(); 
            		while(rs.next())
            		{
            			CustomerBean cust = new CustomerBean();
            			cust.setAddress(rs.getString("address"));
            			cust.setFirstname(rs.getString("firstname"));
            			cust.setLastname(rs.getString("lastname"));
            			cust.setEmail(rs.getString("email"));
            //			System.out.println("Getting Phone Number:: "+ rs.getInt("phonenumber"));
            			cust.setPhonenumber(rs.getString("phonenumber"));
            			cust.setRole(rs.getString("role"));
            			String tempRole = rs.getString("role");
            			list.add(cust);
            		}
        		}
        		else
        		{
        	//		System.out.println("Inside customerBean getList manager");
        			String sqlForUserdata = "select * from manager where username ='" + name +"'";
            		ps= con.prepareStatement(sqlForUserdata); 
            		rs= ps.executeQuery(); 
            		while(rs.next())
            		{
            			CustomerBean cust = new CustomerBean();
            			cust.setAddress(rs.getString("address"));
            			cust.setFirstname(rs.getString("firstname"));
            			cust.setLastname(rs.getString("lastname"));
            			cust.setEmail(rs.getString("email"));
            			cust.setPhonenumber(rs.getString("phonenumber"));
            			cust.setRole(rs.getString("role"));
            			cust.setFee(rs.getDouble("fee"));
            			list.add(cust);
        		}
        		
        		}
    		//list.add(usr);
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

//    public List<CustomerBean> getManagerProfileData(String usernam)
//    {
//    		System.out.println("Inside GetmanagerProfileData.");
//    		List<CustomerBean> list = new ArrayList<CustomerBean>();
//    		PreparedStatement ps = null;
//    		Connection con = null;
//    		ResultSet rs = null;
//    		try
//    		{
//    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
////    			System.out.println("Datasource successful.");
//    			ds.setServerName(System.getenv("ICSI518_SERVER"));
////    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
////    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//    			ds.setUser(System.getenv("ICSI518_USER"));
//    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
////    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//    		
//    			// Get a connection object
//    			con = ds.getConnection();
//    		
//    		String sql = "select * from manager where username='"+usernam +"'";
//    	//	System.out.println("Sql formed for getting role customerBean." + sql);
//    		ps= con.prepareStatement(sql); 
//    		rs= ps.executeQuery(); 
//    		while (rs.next())
//    		{
//
//            			CustomerBean cust = new CustomerBean();
//            			cust.setFirstname(rs.getString("firstname"));
//            			System.out.println("Setting firstname : " + rs.getString("firstname"));
//            			cust.setLastname(rs.getString("lastname"));
//            			cust.setUsername(rs.getString("username"));
//            			cust.setEmail(rs.getString("email"));
//            //			System.out.println("Getting Phone Number:: "+ rs.getInt("phonenumber"));
//            			cust.setPhonenumber(rs.getString("phonenumber"));
//            			System.out.println("Setting phonenumber :" + rs.getString("phonenumber"));
//            			cust.setRole(rs.getString("role"));
//            			list.add(cust);
//            			System.out.println("After setting :: " + firstname + lastname + email + phonenumber + role);
//            			
//                        this.table1Markup = ""; // reset table 1 markup before repopulating
//                     
//
//                        
//                        this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Manager Data</b>:<br>";
//                        this.table1Markup += "<table>";
//                        this.table1Markup += "<tr><td>Firstname</td><td>" + rs.getString("firstname") + "</td></tr>";
//               //         System.out.println("Information Table :: " + this.table1Markup + "  :: " + jsob.getString("1. Information") );
//                        this.table1Markup += "<tr><td>LastName</td><td>" + rs.getString("lastname") + "</td></tr>";
//                        this.table1Markup += "<tr><td>Username</td><td>" + rs.getString("username") + "</td></tr>";
//                        this.table1Markup += "<tr><td>Email</td><td>" + rs.getString("email") + "</td></tr>";
//                        this.table1Markup += "<tr><td>PhoneNumber</td><td>" + rs.getString("phonenumber") + "</td></tr>";
//                        this.table1Markup += "<tr><td>Role</td><td>" + rs.getString("role") + "</td></tr>";
//                        String username = rs.getString("username");
//                        this.table1Markup += "<tr><td>Button</td><td><button type='submit' value='Select Manager' action='#{CustomerBean.insertSelectedManager("' + username + '")}' styleClass='btn btn-success' >Select Manager</button></td></tr>";
//                        this.table1Markup += "</table>";
//                        
//                        System.out.println("Table Markup Formed" + this.table1Markup);
//        		
//        		}
//    	
//
//    		}
//    		catch(Exception e)
//    		{
//    		e.printStackTrace();
//    		}
//    		finally
//    		{
//    		try
//    		{
//    		con.close();
//    		ps.close();
//    		}
//    		catch(Exception e)
//    		{
//    		e.printStackTrace();
//    		}
//    		}
//    		return list;
//    	}
    
    
    
    
    
    
    
    
    
    
  public List<CustomerBean> getManagerProfileData(String usernam)
  {
  		System.out.println("Inside GetmanagerProfileData.");
  		getManagerDatalist = new ArrayList<CustomerBean>();
  		PreparedStatement ps = null;
  		Connection con = null;
  		ResultSet rs = null;
  		try
  		{
  			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//  			System.out.println("Datasource successful.");
  			ds.setServerName(System.getenv("ICSI518_SERVER"));
//  			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
  			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
  	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
  			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//  			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
  			ds.setUser(System.getenv("ICSI518_USER"));
  	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
  			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//  			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
  		
  			// Get a connection object
  			con = ds.getConnection();
  		
  		String sql = "select * from manager where username='"+usernam +"'";
  	//	System.out.println("Sql formed for getting role customerBean." + sql);
  		ps= con.prepareStatement(sql); 
  		rs= ps.executeQuery(); 
  		while (rs.next())
  		{

          			CustomerBean cust = new CustomerBean();
          			cust.setFirstname(rs.getString("firstname"));
          			System.out.println("Setting firstname : " + rs.getString("firstname"));
          			cust.setLastname(rs.getString("lastname"));
          			cust.setUsername(rs.getString("username"));
          			cust.setEmail(rs.getString("email"));
          //			System.out.println("Getting Phone Number:: "+ rs.getInt("phonenumber"));
          			cust.setPhonenumber(rs.getString("phonenumber"));
          			System.out.println("Setting phonenumber :" + rs.getString("phonenumber"));
          			cust.setRole(rs.getString("role"));
          			cust.setFee(rs.getDouble("fee"));
          			getManagerDatalist.add(cust);
          			System.out.println("After setting :: " + firstname + lastname + email + phonenumber + role);
          		
      		
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
  		return getManagerDatalist;
  	}


	public List<CustomerBean> getManagerList()
    {
    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		
    		String sql = "select * from manager";
    	//	System.out.println("Sql formed for getting role customerBean." + sql);
    		ps= con.prepareStatement(sql); 
    		rs= ps.executeQuery(); 
    		while (rs.next())
    		{

            			CustomerBean cust = new CustomerBean();
            			cust.setFirstname(rs.getString("firstname"));
            			cust.setLastname(rs.getString("lastname"));
            			cust.setUsername(rs.getString("username"));
            			list.add(cust);
        		
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
	
    public List<CustomerBean> getStocks()
    {
    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		
    		String sql = "select * from stocks";
    	//	System.out.println("Sql formed for getting role customerBean." + sql);
    		ps= con.prepareStatement(sql); 
    		rs= ps.executeQuery(); 
    		while (rs.next())
    		{

            			CustomerBean cust = new CustomerBean();
            			cust.setSymbol(rs.getString("stock_symbol"));
            			list.add(cust);
        		
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

//    public void selectManager(String uname)
//    {
//    	getManagerProfileData(uname);
//    	System.out.println("After getManagerProfileData.");
//    	String page2 = "manager/ManagerProfilePublic.xhtml";
//		//System.out.println("Inside Customer Dashboard.");
//		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//		try {
//			ec.redirect(ec.getRequestContextPath() + "/" + page2);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
	
	
    
    public List<CustomerBean> getStocksList()
    {
    	System.out.println("Inside getStocks List");
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
		setUsername(name);
		System.out.println("Getting Data for user :: " + name);
  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
  //  	System.out.println("Username in CustomerGetlsit :: " + username);
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		System.out.println("Before Query");
    		String sql = "select * from customerpurchase where user = '" + name +"'";
    	//	System.out.println("Sql formed for getting role customerBean." + sql);
    		System.out.println("After Query");
    		ps= con.prepareStatement(sql); 
    		System.out.println("after ps");
    		rs= ps.executeQuery(); 
    		System.out.println("After rs " + rs);
    		while (rs.next())
    		{
    			
    					System.out.println("Inside rs while");
            			CustomerBean cust = new CustomerBean();
            			cust.setSymbol(rs.getString("stock_symbol"));
            			cust.setQty(rs.getInt("qty"));
            			cust.setPrice(rs.getDouble("price"));
            			cust.setAmt(rs.getDouble("amt"));
            			cust.setStock_id(rs.getInt("uid"));
            			cust.setDate(rs.getDate("date"));
            			cust.setTimestamp(rs.getString("timestamp"));
            			cust.setPurchasedByWhom(rs.getString("purchasedbywhom"));
                        System.out.println("symbol:" + symbol);
                        System.out.println("price:" + price);
                        System.out.println("qty:" + qty);
                        System.out.println("amt:" + amt);
            			list.add(cust);
            		

        		
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


    
    
    
    public List<CustomerBean> getSellHistory()
    {
    	System.out.println("Inside getStocks List");
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
		setUsername(name);
		System.out.println("Getting Data for user :: " + name);
  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
  //  	System.out.println("Username in CustomerGetlsit :: " + username);
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		System.out.println("Before Query");
    		String sql = "select * from customersell where user = '" + name +"'";
    	//	System.out.println("Sql formed for getting role customerBean." + sql);
    		System.out.println("After Query");
    		ps= con.prepareStatement(sql); 
    		System.out.println("after ps");
    		rs= ps.executeQuery(); 
    		System.out.println("After rs " + rs);
    		while (rs.next())
    		{
    			
    					System.out.println("Inside rs while");
            			CustomerBean cust = new CustomerBean();
            			cust.setSymbol(rs.getString("stock_symbol"));
            			cust.setQty(rs.getInt("qty"));
            			cust.setPrice(rs.getDouble("price"));
            			cust.setAmt(rs.getDouble("amt"));
            			cust.setStock_id(rs.getInt("uid"));
            			cust.setDate(rs.getDate("date"));
            			cust.setTimestamp(rs.getString("timestamp"));
                        System.out.println("symbol:" + symbol);
                        System.out.println("price:" + price);
                        System.out.println("qty:" + qty);
                        System.out.println("amt:" + amt);
            			list.add(cust);
            		

        		
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

    
    
	public String updatefirstname()
	{
		try{
			
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    			
        		String sql = "select role from user where username = '" + name +"'";
            	//	System.out.println("Sql formed for getting role customerBean." + sql);
            		ps= con.prepareStatement(sql); 
            		rs= ps.executeQuery(); 
            		while (rs.next())
            		{
            			
            			CustomerBean usr = new CustomerBean();
//            			usr.setRole(rs.getString("role"));
//            			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//            			usr.setUserID(rs.getInt("user_id"));
            			//usr.setUsername(rs.getString("username"));
            			usr.setUsername(name);
            			usr.setRole(rs.getString("role"));
            			System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));


		System.out.println("Before Update of Firstname");
		System.out.println("Username :: " + name);
		System.out.println("Firstname :: " + firstname);
		System.out.println("Lastname :: " + lastname);
		System.out.println("Address :: " + address);
		System.out.println("Email :: " + email);
		System.out.println("PhoneNumber :: " + phonenumber);
		System.out.println("Role :: " + usr.role);
		
		boolean valid =CustomerDao.updatefirstname(firstname,usr.role,name);
		
		if(valid)
		{
			getUserList();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully","Updated Successfully");        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "Login.xhtml?faces-redirect=true";
		}
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
	}

	catch(Exception e){
		e.printStackTrace();
	}

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed","Update Failed");        
//	FacesContext.getCurrentInstance().addMessage(null, message);      
//	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("loginform:temp", message);
	
	context.getExternalContext().getFlash().setKeepMessages(true);
	return "Login.xhtml?faces-redirect=true";
	}
	
	
	public String updatelastname()
	{
		try{
			
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    			
        		String sql = "select role from user where username = '" + name +"'";
            	//	System.out.println("Sql formed for getting role customerBean." + sql);
            		ps= con.prepareStatement(sql); 
            		rs= ps.executeQuery(); 
            		while (rs.next())
            		{
            			
            			CustomerBean usr = new CustomerBean();
//            			usr.setRole(rs.getString("role"));
//            			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//            			usr.setUserID(rs.getInt("user_id"));
            			//usr.setUsername(rs.getString("username"));
            			usr.setUsername(name);
            			usr.setRole(rs.getString("role"));
            			System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));


		System.out.println("Before Update of Firstname");
		System.out.println("Username :: " + name);
		System.out.println("Firstname :: " + firstname);
		System.out.println("Lastname :: " + lastname);
		System.out.println("Address :: " + address);
		System.out.println("Email :: " + email);
		System.out.println("PhoneNumber :: " + phonenumber);
		System.out.println("Role :: " + usr.role);
		
		boolean valid =CustomerDao.updatelastname(lastname,usr.role,name);
		
		if(valid)
		{
			getUserList();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully","Updated Successfully");        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "Login.xhtml?faces-redirect=true";
		}
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
	}

	catch(Exception e){
		e.printStackTrace();
	}

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed","Update Failed");        
//	FacesContext.getCurrentInstance().addMessage(null, message);      
//	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("loginform:temp", message);
	
	context.getExternalContext().getFlash().setKeepMessages(true);
	return "Login.xhtml?faces-redirect=true";
	}
	
	
	
	
	public String updateaddress()
	{
		try{
			
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    			
        		String sql = "select role from user where username = '" + name +"'";
            	//	System.out.println("Sql formed for getting role customerBean." + sql);
            		ps= con.prepareStatement(sql); 
            		rs= ps.executeQuery(); 
            		while (rs.next())
            		{
            			
            			CustomerBean usr = new CustomerBean();
//            			usr.setRole(rs.getString("role"));
//            			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//            			usr.setUserID(rs.getInt("user_id"));
            			//usr.setUsername(rs.getString("username"));
            			usr.setUsername(name);
            			usr.setRole(rs.getString("role"));
            			System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));


		System.out.println("Before Update of Firstname");
		System.out.println("Username :: " + name);
		System.out.println("Firstname :: " + firstname);
		System.out.println("Lastname :: " + lastname);
		System.out.println("Address :: " + address);
		System.out.println("Email :: " + email);
		System.out.println("PhoneNumber :: " + phonenumber);
		System.out.println("Role :: " + usr.role);
		
		boolean valid =CustomerDao.updateaddress(address,usr.role,name);
		
		if(valid)
		{
			getUserList();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully","Updated Successfully");        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "Login.xhtml?faces-redirect=true";
		}
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
	}

	catch(Exception e){
		e.printStackTrace();
	}

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed","Update Failed");        
//	FacesContext.getCurrentInstance().addMessage(null, message);      
//	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("loginform:temp", message);
	
	context.getExternalContext().getFlash().setKeepMessages(true);
	return "Login.xhtml?faces-redirect=true";
	}
	
	
	
	public String updatephonenumber()
	{
		try{
			
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    			
        		String sql = "select role from user where username = '" + name +"'";
            	//	System.out.println("Sql formed for getting role customerBean." + sql);
            		ps= con.prepareStatement(sql); 
            		rs= ps.executeQuery(); 
            		while (rs.next())
            		{
            			
            			CustomerBean usr = new CustomerBean();
//            			usr.setRole(rs.getString("role"));
//            			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//            			usr.setUserID(rs.getInt("user_id"));
            			//usr.setUsername(rs.getString("username"));
            			usr.setUsername(name);
            			usr.setRole(rs.getString("role"));
            			System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));


		System.out.println("Before Update of Firstname");
		System.out.println("Username :: " + name);
		System.out.println("Firstname :: " + firstname);
		System.out.println("Lastname :: " + lastname);
		System.out.println("Address :: " + address);
		System.out.println("Email :: " + email);
		System.out.println("PhoneNumber :: " + phonenumber);
		System.out.println("Role :: " + usr.role);
		
		boolean valid =CustomerDao.updatephonenumber(phonenumber,usr.role,name);
		
		if(valid)
		{
			getUserList();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully","Updated Successfully");        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "Login.xhtml?faces-redirect=true";
		}
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
	}

	catch(Exception e){
		e.printStackTrace();
	}

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed","Update Failed");        
//	FacesContext.getCurrentInstance().addMessage(null, message);      
//	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("loginform:temp", message);
	
	context.getExternalContext().getFlash().setKeepMessages(true);
	return "Login.xhtml?faces-redirect=true";
	}
	
	public String updateemail()
	{
		try{
			
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    			
        		String sql = "select role from user where username = '" + name +"'";
            	//	System.out.println("Sql formed for getting role customerBean." + sql);
            		ps= con.prepareStatement(sql); 
            		rs= ps.executeQuery(); 
            		while (rs.next())
            		{
            			
            			CustomerBean usr = new CustomerBean();
//            			usr.setRole(rs.getString("role"));
//            			usr.setFlagVal(rs.getBoolean("flagbyadmin"));
//            			usr.setUserID(rs.getInt("user_id"));
            			//usr.setUsername(rs.getString("username"));
            			usr.setUsername(name);
            			usr.setRole(rs.getString("role"));
            			System.out.println("Role value :: " + usr.role + " DB :: " + rs.getString("role"));


		System.out.println("Before Update of Firstname");
		System.out.println("Username :: " + name);
		System.out.println("Firstname :: " + firstname);
		System.out.println("Lastname :: " + lastname);
		System.out.println("Address :: " + address);
		System.out.println("Email :: " + email);
		System.out.println("PhoneNumber :: " + phonenumber);
		System.out.println("Role :: " + usr.role);
		
		boolean valid =CustomerDao.updateemail(email,usr.role,name);
		
		if(valid)
		{
			getUserList();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Successfully","Updated Successfully");        
//			FacesContext.getCurrentInstance().addMessage(null, message);      
//			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("loginform:temp", message);
			
			context.getExternalContext().getFlash().setKeepMessages(true);
			return "Login.xhtml?faces-redirect=true";
		}
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
	}

	catch(Exception e){
		e.printStackTrace();
	}

	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Failed","Update Failed");        
//	FacesContext.getCurrentInstance().addMessage(null, message);      
//	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("loginform:temp", message);
	
	context.getExternalContext().getFlash().setKeepMessages(true);
	return "Login.xhtml?faces-redirect=true";
	}
	

	
    public double getBalance()
    {
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	StockAPIDao dao = new StockAPIDao();
    	double totalbalance = dao.getTotalBalance(name);
    	CustomerBean obj = new CustomerBean();
    	
    	System.out.println("Total Balance is : " + totalbalance);
    	
    	obj.setTotalbal(totalbalance);
    	return totalbalance;
    }
    
    
    public void addToWatchlist(String symbol)
    {

    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	System.out.println("Inside Add to Watchlist method.");
    	CustomerDao dao = new CustomerDao();
    	boolean valid = dao.insertintoWatchlist(name,symbol);
    	
    	System.out.println("Boolean Returned value :: " + valid);
    	if(valid)
    	{
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added to Watchlist",""));
    	}
    	else
    	{
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "WatchList add Failed.",""));
    	}
    }
    
    
    public List<CustomerBean> getWatchList()
    {
    	
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
		setUsername(name);
		System.out.println("Getting Data for user :: " + name);
  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
  //  	System.out.println("Username in CustomerGetlsit :: " + username);
    		List<CustomerBean> list = new ArrayList<CustomerBean>();
    		PreparedStatement ps = null;
    		Connection con = null;
    		ResultSet rs = null;
    		try
    		{
    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//    			System.out.println("Datasource successful.");
    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
    			ds.setUser(System.getenv("ICSI518_USER"));
    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
    		
    			// Get a connection object
    			con = ds.getConnection();
    		String sql = "select * from customerwatchlist where username = '" + name +"'";
    		ps= con.prepareStatement(sql); 
    		rs= ps.executeQuery(); 
    		while (rs.next())
    		{
    			
            			CustomerBean cust = new CustomerBean();
            			cust.setSymbol(rs.getString("stock_symbol"));
            			cust.setStockidforwatchlist(rs.getInt("uid"));
            			list.add(cust);
            		

        		
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
    
    
    
    
    public String getCurrentPriceofStock(String sym) throws MalformedURLException, IOException {
    	String currentPrice=null;
        installAllTrustingManager();
        System.out.println("Inside timeseries for Symbol :: " + sym);
        String symbol = sym;
        String interval = this.selectedInterval;
        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + API_KEY;
        System.out.println("URL formed :: " + url);
        InputStream inputStream = new URL(url).openStream();

        // convert the json string back to object
        JsonReader jsonReader = Json.createReader(inputStream);
        JsonObject mainJsonObj = jsonReader.readObject();
        for (String key : mainJsonObj.keySet()) {
            if (key.equals("Meta Data")) {
                this.table1Markup = null; // reset table 1 markup before repopulating
                JsonObject jsob = (JsonObject) mainJsonObj.get(key);
                
                this.table1Markup += "<style>#detail >tbody > tr > td{ text-align:center;}</style><b>Stock Details</b>:<br>";
                this.table1Markup += "<table>";
                this.table1Markup += "<tr><td>Information</td><td>" + jsob.getString("1. Information") + "</td></tr>";
       //         System.out.println("Information Table :: " + this.table1Markup + "  :: " + jsob.getString("1. Information") );
                this.table1Markup += "<tr><td>Symbol</td><td>" + jsob.getString("2. Symbol") + "</td></tr>";
                this.table1Markup += "<tr><td>Last Refreshed</td><td>" + jsob.getString("3. Last Refreshed") + "</td></tr>";
                this.table1Markup += "<tr><td>Interval</td><td>" + jsob.getString("4. Interval") + "</td></tr>";
                this.table1Markup += "<tr><td>Output Size</td><td>" + jsob.getString("5. Output Size") + "</td></tr>";
                this.table1Markup += "<tr><td>Time Zone</td><td>" + jsob.getString("6. Time Zone") + "</td></tr>";
                this.table1Markup += "</table>";
            } else {
                this.table2Markup = null; // reset table 2 markup before repopulating
                JsonObject dataJsonObj = mainJsonObj.getJsonObject(key);
                this.table2Markup += "<table class='table table-hover'>";
                this.table2Markup += "<thead><tr><th>Timestamp</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th></tr></thead>";
                this.table2Markup += "<tbody>";
                int i = 0;
                for (String subKey : dataJsonObj.keySet()) {
                    JsonObject subJsonObj = dataJsonObj.getJsonObject(subKey);
                    this.table2Markup
                            += "<tr>"
                            + "<td>" + subKey + "</td>"
                            + "<td>" + subJsonObj.getString("1. open") + "</td>"
                            + "<td>" + subJsonObj.getString("2. high") + "</td>"
                            + "<td>" + subJsonObj.getString("3. low") + "</td>"
                            + "<td>" + subJsonObj.getString("4. close") + "</td>"
                            + "<td>" + subJsonObj.getString("5. volume") + "</td>";
                    		currentPrice = subJsonObj.getString("4. close");
                    		
                    		System.out.println("Got the current Price :: " + currentPrice);
                    if (i == 0) {
                        String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                        System.out.println("Buy stock button URL :: " + path);
                       // this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/customer/CustomerPurchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
                        
                    }
                    this.table2Markup += "</tr>";
                    i++;
                    break;
                }
                this.table2Markup += "</tbody></table>";
            }
        }
        System.out.println("Returning Current Price :: " + currentPrice);
        return currentPrice;
    }
    
    public void removeFromWatchlist(int id)
    {
    	CustomerDao dao = new CustomerDao();
    	
    	boolean valid = dao.removeFromWatchlist(id);
    	if(valid)
    	{
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully removed from Watchlist",""));
    	}
    	else
    	{
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "WatchList remove Failed.",""));
    	}
    }
    
    
    public void insertSelectedManager(String managerUsername) {
		// TODO Auto-generated method stub
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	
    	System.out.println("Inside Select Manager Insert Method." + managerUsername);
    	CustomerDao dao = new CustomerDao();
    	
    	boolean flag = dao.checkSelected(name,managerUsername);
    	
    	if(flag)
    	{
    		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You already Selected Manager for Buy & Sell.",""));	
    	}	
    	else
    	{
        	boolean valid = dao.insertSelectedManager(name, managerUsername);
        	
        	if(valid)
        	{
        		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added  selected Manager",""));
        	}
        	else
        	{
        		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Manager Selection Failed.",""));
        	}
    	}

	}
    
    public void requestForBuy(String manager, double Amount)
    {
    	System.out.println("Inside Request For buy by user to Manager");
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	
		try{
			// Setup the DataSource object
			
			System.out.println("Before Inserting request for buy " + name + " " + manager + " " + symbol + " " + qty);
			
			boolean flag = CustomerDao.checkentryforcustomermanagerbuyrequest(name,manager);
			
			if(flag)
			{
				double dbAmt = CustomerDao.getBuyRequestAmount(manager,name);
				double dbRemainingAmt = CustomerDao.getBuyRequestRemainingAmount(manager, name);
				
				double updatedAmt = dbAmt + Amount;
				double updatedRemainingAmt = dbRemainingAmt + Amount;
				System.out.println("Updated Amount :: " + updatedAmt);
				System.out.println("Updated Remaining Amount :: " + updatedRemainingAmt);
				boolean valid= CustomerDao.updatecustomermanagerbuyrequest(manager,name,updatedAmt,updatedRemainingAmt);
				if(valid)
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry already there so updated Amount Successfully.",""));
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Amount failed for entry.",""));
				}
			}
			else
			{
				boolean valid =CustomerDao.customermanagerbuyrequest(manager,Amount,name);
				
				
				if(valid)
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Added Successfully.",""));
				}

			}
			
		}

		catch(Exception e){
			e.printStackTrace();
		}
    }
    
    
    public void requestForSell(String manager, double Amount)
    {
    	System.out.println("Inside Request For buy by user to Manager");
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	HttpSession session = request.getSession();
    	String user = request.getRemoteUser();
    	//Enumeration<String> names = session.getAttributeNames();
    	String name = (String)session.getAttribute("user");
    	
		try{
			// Setup the DataSource object
			
			System.out.println("Before Inserting request for buy " + name + " " + manager + " " + symbol + " " + qty);
			
			boolean flag = CustomerDao.checkentryforcustomermanagersellrequest(name,manager);
			
			if(flag)
			{
				double dbAmt = CustomerDao.getSellRequestAmount(manager,name);
				double dbRemainingAmt = CustomerDao.getSellRequestRemainingAmount(manager, name);
				
				double updatedAmt = dbAmt + Amount;
				double updatedRemainingAmt = dbRemainingAmt + Amount;
				System.out.println("Updated Amount :: " + updatedAmt);
				System.out.println("Updated Remaining Amount :: " + updatedRemainingAmt);
				boolean valid= CustomerDao.updatecustomermanagersellrequest(manager,name,updatedAmt,updatedRemainingAmt);
				if(valid)
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Entry already there so updated Amount Successfully.",""));
				}
				else
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Amount failed for entry.",""));
				}
			}
			else
			{
				boolean valid =CustomerDao.customermanagersellrequest(manager,Amount,name);
				
				
				if(valid)
				{
					FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Added Successfully.",""));
				}
			
		}}

		catch(Exception e){
			e.printStackTrace();
		}
    }
    
    public List<CustomerBean> getSelectedManagers()
    {
        	
        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        	HttpSession session = request.getSession();
        	String user = request.getRemoteUser();
        	//Enumeration<String> names = session.getAttributeNames();
        	String name = (String)session.getAttribute("user");
    		setUsername(name);
    		System.out.println("Getting Data for user :: " + name);
      //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
      //  	System.out.println("Username in CustomerGetlsit :: " + username);
        		List<CustomerBean> list = new ArrayList<CustomerBean>();
        		PreparedStatement ps = null;
        		Connection con = null;
        		ResultSet rs = null;
        		try
        		{
        			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//        			System.out.println("Datasource successful.");
        			ds.setServerName(System.getenv("ICSI518_SERVER"));
//        			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
        			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
        	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
        			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//        			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
        			ds.setUser(System.getenv("ICSI518_USER"));
        	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
        			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//        			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
        		
        			// Get a connection object
        			con = ds.getConnection();
        		String sql = "select * from customerselectedmanager where customername = '" + name +"'";
        		ps= con.prepareStatement(sql); 
        		rs= ps.executeQuery(); 
        		while (rs.next())
        		{
        			
                			CustomerBean cust = new CustomerBean();
                			cust.setManager(rs.getString("managername"));
                			cust.setId(rs.getInt("uid"));
                			list.add(cust);
                		

            		
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
