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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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

import Dao.CustomerDao;
import Dao.ManagerDao;
import Dao.StockAPIDao;


@ManagedBean(name = "ManagerBean")
@SessionScoped

public class ManagerBean {
	
    private static final long serialVersionUID = 1L;
    static final String API_KEY = "GJN8GUXV0BINMDWI";
    
	private String table1Markup;
    private String table2Markup;	
    private String selectedSymbol;
    private List<SelectItem> availableSymbols;
    private String selectedInterval;
    private List<SelectItem> availableIntervals;
    private String customername;
    private String managername;
    private String symbol;
    private int qty;
	private int id;
	private double price;
	private double amt;
	private Date date;
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
    private String purchaseSymbol;
    private double purchasePrice;
    private String currentPrice;
    private double totalbal;
    private String timestamp;
    private String purchasedByWhom;
    private double remainingAmt;
    
    private String firstname;
    private String lastname;
    private String address;
    private int phonenumber;
    private String email;
    private double fee;
    
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



	public int getPhonenumber() {
		return phonenumber;
	}



	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public double getFee() {
		return fee;
	}



	public void setFee(double fee) {
		this.fee = fee;
	}



	public double getRemainingAmt() {
		return remainingAmt;
	}



	public void setRemainingAmt(double remainingAmt) {
		this.remainingAmt = remainingAmt;
	}



	public String getPurchasedByWhom() {
		return purchasedByWhom;
	}



	public void setPurchasedByWhom(String purchasedByWhom) {
		this.purchasedByWhom = purchasedByWhom;
	}



	public String getTimestamp() {
		return timestamp;
	}



	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}



	public double getTotalbal() {
		return totalbal;
	}



	public void setTotalbal(double totalbal) {
		this.totalbal = totalbal;
	}



	public String getPurchaseSymbol() {
		return purchaseSymbol;
	}



	public void setPurchaseSymbol(String purchaseSymbol) {
		this.purchaseSymbol = purchaseSymbol;
	}



	public double getPurchasePrice() {
		return purchasePrice;
	}



	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getAmt() {
		return amt;
	}



	public void setAmt(double amt) {
		this.amt = amt;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCustomername() {
		return customername;
	}



	public void setCustomername(String customername) {
		this.customername = customername;
	}



	public String getManagername() {
		return managername;
	}



	public void setManagername(String managername) {
		this.managername = managername;
	}



	public String getSymbol() {
		return symbol;
	}



	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}



	public int getQty() {
		return qty;
	}



	public void setQty(int i) {
		this.qty = i;
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

    
    

	   public void timeseries() throws MalformedURLException, IOException {

	        installAllTrustingManager();

	        //System.out.println("selectedItem: " + this.selectedSymbol);
	        //System.out.println("selectedInterval: " + this.selectedInterval);
	        String symbol = this.selectedSymbol;
	        String interval = this.selectedInterval;
	        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=" + interval + "&apikey=" + API_KEY;
	        System.out.println("URL Formed :: " + url);

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
	                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/manager/ManagerPurchase.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Buy Stock</a></td>";
	                        this.table2Markup += "<td><a class='btn btn-success' href='" + path + "/manager/ManagerSell.xhtml?symbol=" + symbol + "&price=" + subJsonObj.getString("4. close") + "'>Sell Stock</a></td>";	                        
	                    }
	                    this.table2Markup += "</tr>";
	                    i++;
	                }
	                this.table2Markup += "</tbody></table>";
	            }
	        }
	        return;
	    }
	   
	   
	   public List<ManagerBean> getBuyRequests()
	    {
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String managerName = (String)session.getAttribute("user");
	    		List<ManagerBean> list = new ArrayList<ManagerBean>();
	    		PreparedStatement ps = null;
	    		Connection con = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//	    			System.out.println("Datasource successful.");
	    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//	    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
	    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
	    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//	    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
	    			ds.setUser(System.getenv("ICSI518_USER"));
	    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
	    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//	    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
	    		
	    			// Get a connection object
	    			con = ds.getConnection();
	    		
	    		String sql = "select * from customermanagerbuyrequest where managername='"+ managerName + "'";
	    	//	System.out.println("Sql formed for getting role customerBean." + sql);
	    		ps= con.prepareStatement(sql); 
	    		rs= ps.executeQuery(); 
	    		while (rs.next())
	    		{

	            			ManagerBean cust = new ManagerBean();
	            			cust.setCustomername(rs.getString("customername"));
	            			cust.setManagername(rs.getString("managername"));
	            			cust.setAmt(rs.getDouble("amount"));
	            			cust.setId(rs.getInt("uid"));
	            			cust.setRemainingAmt(rs.getDouble("remainingAmount"));
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
	   
	   
	   public String createDbRecord(String symbol, double price, int qty, double amount,String custName) {
	        try {
	        	System.out.println("Customer Name :: " + custName);
	        	System.out.println("Symbol :: " + symbol );
	        	System.out.println("Price :: " + price);
	        	System.out.println("Amount :: " + amount);
	        	System.out.println("Inside Create DB record");
	            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
	            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");
	        	Connection con = null;
				com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//				System.out.println("Datasource successful.");
				ds.setServerName(System.getenv("ICSI518_SERVER"));
//				System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
				ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
		//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
				ds.setDatabaseName(System.getenv("ICSI518_DB"));
//				System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
				ds.setUser(System.getenv("ICSI518_USER"));
		//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
				ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//				System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
			
				// Get a connection object
				con = ds.getConnection();
		    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		    	HttpSession session = request.getSession();
		    	String user = request.getRemoteUser();
		    	//Enumeration<String> names = session.getAttributeNames();
		    	String managerName = (String)session.getAttribute("user");
	            
	            double totalbalance = StockAPIDao.getTotalBalance(custName);
	            System.out.println("Total Balance Remained :: " + totalbalance);
	            System.out.println("Amount in ManagerBEan " + amount);
	            ManagerDao daob = new ManagerDao();
	            double managerfee = daob.getManagerFee(managerName);
	            System.out.println("Manager Fee :: " + managerfee);
	            double amountAfterManagerfee = amount + managerfee;
	            double totalamt = totalbalance - amountAfterManagerfee;
	            System.out.println("totalbalance ::"+ totalbalance);
	            System.out.println("Amount after adding manager fee ::" + amountAfterManagerfee);
	            System.out.println("Total amount :: " + totalamt);
 	            double remainingBalance= ManagerDao.getRequestBalanceBuy(custName,managerName);
	            System.out.println("Got Remaninig Bal: " + remainingBalance);
	            
	            if(totalbalance > amountAfterManagerfee && remainingBalance > amountAfterManagerfee)
	            {
	            	remainingBalance = remainingBalance - amountAfterManagerfee;
	           // 	double updatedAmt = totalbalance - amount;
		            double managerTotalBalance =  ManagerDao.getTotalBalance(managerName);
		            double managerbal = managerfee + managerTotalBalance;
//	            	StockAPIDao.updateBalance(totalamt, custName);
	            	System.out.println("Remaining Balance after deducting managerfee : " + remainingBalance);
	            	ManagerDao.updateRemainingBalanceBuy(remainingBalance,custName,managerName);
	            	StockApiBean stockapibean = new StockApiBean();
	            	stockapibean.commonTable(symbol, qty,custName);
	            	ManagerDao.updateBalance(managerbal, managerName);
	            	long time = System.currentTimeMillis();
	            	System.out.println("Current Time :: " + time);
	            	date = new Date(time);
//	            	
//	            	Time t = new java.sql.Time(503000);
//	            	timeformat.setTimeZone(TimeZone.getTimeZone("GMT"));
//	            	String currentTime = timeformat.format(t);
//	            	
//	                java.util.Date today = new java.util.Date();
//	                System.out.println(new java.sql.Timestamp(today.getTime()));
	            	
	            	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	                 System.out.println(timestamp);
	                 String currentTime = timeformat.format(timestamp);
	          //  statement.executeUpdate("INSERT INTO customerpurchase ('user', `stock_symbol`, `qty`, `price`, `amt`) "
	  //                  + "VALUES ('" + name + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
	            String sql = "insert into managerpurchase(customername,stock_symbol,qty,price,amt,date,timestamp,purchasedbywhom,managername)	VALUES(?,?,?,?,?,?,?,?,?)";
	            StockApiBean obj = new StockApiBean();
	            obj.createDbRecord(custName, symbol, price, qty, amountAfterManagerfee, managerName);
	            PreparedStatement st = con.prepareStatement(sql);
	            st.setString(1,custName);
	            st.setString(2,symbol);
	            st.setInt(3, qty);
	            st.setDouble(4, price);
	            st.setDouble(5, amount);
	            st.setDate(6, date);
	            st.setString(7, currentTime);
	            st.setString(8, "purchased by you");
	            st.setString(9, managerName);
	            
	            boolean rs = st.execute();
	            
	            st.close();
	            con.close();
	            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock by Manager",""));
	            }
	            else
	            {
	            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer didn't requested enough money.",""));
	            }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return "ManagerPurchase";
	    }
	   
	   
	   
	    public void dashboardRedirect()
	    {
	    	String page2 = "manager/ManagerDashboard.xhtml";
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
	    	String page2 = "manager/ManagerSell.xhtml";
			//System.out.println("Inside Customer Dashboard.");
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
	    	String page2 = "manager/ManagerProfile.xhtml";
			//System.out.println("Inside Customer Dashboard.");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath() + "/" + page2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	    
	    
	    public void redirectBalanceandHistory()
	    {
	    	String page2 = "manager/ManagerBalanceandHistory.xhtml";
			//System.out.println("Inside Customer Dashboard.");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath() + "/" + page2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    public List<ManagerBean> getSellRequests()
	    {
	    	System.out.println("Inside getStocks List");
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String managerName = (String)session.getAttribute("user");
			System.out.println("Getting Data for user :: " + managerName);
	  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
	  //  	System.out.println("Username in CustomerGetlsit :: " + username);
	    		List<ManagerBean> list = new ArrayList<ManagerBean>();
	    		PreparedStatement ps = null;
	    		Connection con = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//	    			System.out.println("Datasource successful.");
	    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//	    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
	    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
	    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//	    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
	    			ds.setUser(System.getenv("ICSI518_USER"));
	    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
	    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//	    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
	    		
	    			// Get a connection object
	    			con = ds.getConnection();
	    		System.out.println("Before Query");
	    		String sql = "select * from customermanagersellrequest where managername = '" + managerName +"'";
	    	//	System.out.println("Sql formed for getting role customerBean." + sql);
	    		System.out.println("After Query");
	    		ps= con.prepareStatement(sql); 
	    		System.out.println("after ps");
	    		rs= ps.executeQuery(); 
	    		System.out.println("After rs " + rs);
	    		while (rs.next())
	    		{
	    			
	    					System.out.println("Inside rs while");
	            			ManagerBean cust = new ManagerBean();
	            			cust.setCustomername(rs.getString("customername"));
	            			cust.setManagername(rs.getString("managername"));
	            			cust.setAmt(rs.getDouble("amount"));
	            			cust.setRemainingAmt(rs.getDouble("remainingAmount"));
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
	    
	    
	    public String updateDbRecord(String sym, double pri, int qty, double amount,String custName) throws MalformedURLException, IOException, SQLException{
	    		
	    	System.out.println("All Details :: " + sym + " " + pri + " " + qty + " " + amount + " " + custName);
	    	
	    	StockApiBean stockapibean = new StockApiBean();
	    	StockAPIDao stockapidao = new StockAPIDao();
	    	double customerTotalBalance = stockapidao.getTotalBalance(custName);
	    	String currentPrice = stockapibean.timeseries(sym);
	    	float currentPriceNow = Float.parseFloat(currentPrice);
	    	
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String managerName = (String)session.getAttribute("user");
	    	
            ManagerDao daob = new ManagerDao();
            double managerfee = daob.getManagerFee(managerName);
            System.out.println("Manager Fee :: " + managerfee);
            double amountAfterManagerfee = amount + managerfee;
            double updatedAmt = currentPriceNow * qty;
            double amountwithManagerFee = updatedAmt + managerfee;
            System.out.println("Current Price :: " + currentPriceNow);
            System.out.println("Updated Amount :: " + updatedAmt);
            double updatedBalanceCustomer = customerTotalBalance + updatedAmt - managerfee;
            System.out.println("totalbalance ::"+ customerTotalBalance);
            System.out.println("Amount after adding manager fee ::" + amountAfterManagerfee);
            System.out.println("Total amount :: " + updatedBalanceCustomer);
	        
            double remainingBalance= ManagerDao.getRequestBalanceSell(custName,managerName);
            System.out.println("Got Remaninig Bal: " + remainingBalance);

            if(remainingBalance > amountwithManagerFee)
            {
            		remainingBalance = remainingBalance - amountwithManagerFee;
		            boolean rs ;
		            System.out.println("Remaining Balance after deducting managerfee : " + remainingBalance);
	            	ManagerDao.updateRemainingBalanceBuy(remainingBalance,custName,managerName);
		        	long time = System.currentTimeMillis();
		        	System.out.println("Current Time :: " + time);
		        	date = new Date(time);
		        	
		        	
		            double managerTotalBalance =  ManagerDao.getTotalBalance(managerName);
		            double managerbal = managerfee + managerTotalBalance;
	            	ManagerDao.updateBalance(managerbal, managerName);
	            	
		        	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		             System.out.println(timestamp);
		             String currentTime = timeformat.format(timestamp);
		             
		            boolean flag = StockAPIDao.sellCommandTable(sym,qty,custName);
		            if(flag==true)
		            {
			            boolean valid = daob.insertIntoManagerSell(custName, sym, qty, currentPriceNow, amountAfterManagerfee, date, currentTime, managerName);
			            
			            if(valid)
			            {
			            	StockAPIDao.updateBalance(updatedBalanceCustomer, custName);
			            	
			            }
			            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Sold Stock by Manager",""));
		            }
		            else
		            {
		            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You do not have enough Quantity.",""));
		            }
		            
            }
            else
            {
            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You don't have enough balance to sell.",""));
            }
	        return "ManagerPurchase";
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
	        
	    public String timeseries(String sym) throws MalformedURLException, IOException {

	        installAllTrustingManager();
	        System.out.println("Inside timeseries for Symbol :: " + sym);
	        //System.out.println("selectedItem: " + this.selectedSymbol);
	        //System.out.println("selectedInterval: " + this.selectedInterval);
	        String symbol = sym;
	        String interval = this.selectedInterval;
	        String url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + API_KEY;
	        System.out.println("URL formed :: " + url);
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
	    
	    
	    public void addToWatchlist(String symbol)
	    {

	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	System.out.println("Inside Add to Watchlist method.");
	    	ManagerDao dao = new ManagerDao();
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
	    
	    
	    
	    public List<CustomerBean> getStocks()
	    {
	    	
	    		List<CustomerBean> list = new ArrayList<CustomerBean>();
	    		PreparedStatement ps = null;
	    		Connection con = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//	    			System.out.println("Datasource successful.");
	    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//	    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
	    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
	    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//	    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
	    			ds.setUser(System.getenv("ICSI518_USER"));
	    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
	    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//	    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
	    		
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
	    
	    
	    public void redirectToWatchList()
	    {
	    	String page2 = "manager/ManagerWatchlist.xhtml";
			//System.out.println("Inside Customer Dashboard.");
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			try {
				ec.redirect(ec.getRequestContextPath() + "/" + page2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    
	    
	    public List<ManagerBean> getWatchList()
	    {
	    	
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
			System.out.println("Getting Data for user :: " + name);
	  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
	  //  	System.out.println("Username in CustomerGetlsit :: " + username);
	    		List<ManagerBean> list = new ArrayList<ManagerBean>();
	    		PreparedStatement ps = null;
	    		Connection con = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//	    			System.out.println("Datasource successful.");
	    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//	    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
	    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
	    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//	    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
	    			ds.setUser(System.getenv("ICSI518_USER"));
	    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
	    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//	    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
	    		
	    			// Get a connection object
	    			con = ds.getConnection();
	    		String sql = "select * from managerwatchlist where username = '" + name +"'";
	    		ps= con.prepareStatement(sql); 
	    		rs= ps.executeQuery(); 
	    		while (rs.next())
	    		{
	    			
	    			ManagerBean cust = new ManagerBean();
	            			cust.setSymbol(rs.getString("stock_symbol"));
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
	    
	    
	    
	    public void removeFromWatchlist(int id)
	    {
	    	ManagerDao dao = new ManagerDao();
	    	
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
	    
	    
	    
	    public double getBalance()
	    {
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
	    	ManagerDao dao = new ManagerDao();
	    	double totalbalance = dao.getTotalBalance(name);
	    	ManagerBean obj = new ManagerBean();
	    	
	    	System.out.println("Total Balance is : " + totalbalance);
	    	
	    	obj.setTotalbal(totalbalance);
	    	return totalbalance;
	    }
	    
	    
	    public List<ManagerBean> getPurchaseHistory()
	    {
	    	
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String name = (String)session.getAttribute("user");
			System.out.println("Getting Data for user :: " + name);
	  //  	System.out.println("Getting data of User :: " + name + " on Profile Page.");
	  //  	System.out.println("Username in CustomerGetlsit :: " + username);
	    		List<ManagerBean> list = new ArrayList<ManagerBean>();
	    		PreparedStatement ps = null;
	    		Connection con = null;
	    		ResultSet rs = null;
	    		try
	    		{
	    			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//	    			System.out.println("Datasource successful.");
	    			ds.setServerName(System.getenv("ICSI518_SERVER"));
//	    			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
	    			ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
	    	//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
	    			ds.setDatabaseName(System.getenv("ICSI518_DB"));
//	    			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
	    			ds.setUser(System.getenv("ICSI518_USER"));
	    	//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
	    			ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//	    			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
	    		
	    			// Get a connection object
	    			con = ds.getConnection();
	    		String sql = "select * from managerpurchase where managername = '" + name +"'";
	    		System.out.println("Query for manager History." + sql);
	    		ps= con.prepareStatement(sql); 
	    		rs= ps.executeQuery(); 
	    		while (rs.next())
	    		{
	    			
	            			ManagerBean cust = new ManagerBean();
	            			cust.setSymbol(rs.getString("stock_symbol"));
	            			cust.setCustomername(rs.getString("customername"));
	            			cust.setAmt(rs.getDouble("amt"));
	            			cust.setPrice(rs.getDouble("price"));
	            			cust.setQty(rs.getInt("qty"));
	            			cust.setDate(rs.getDate("date"));
	            			cust.setTimestamp(rs.getString("timestamp"));
	            			cust.setId(rs.getInt("uid"));
	            			cust.setPurchasedByWhom(rs.getString("purchasedbywhom"));
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
	    
	    
	    public void updateManagerProfile(String value, String field)
	    {
	    	
	    	System.out.println("Update Field " + field + " with the value : "+ value);
	    	ManagerDao dao = new ManagerDao();
	    	
	    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    	HttpSession session = request.getSession();
	    	String user = request.getRemoteUser();
	    	//Enumeration<String> names = session.getAttributeNames();
	    	String managerName = (String)session.getAttribute("user");
	    	
	    	dao.updateManagerProfiles(value,field,managerName);
	    	
	    	
	    	
	    }
	    
	    public void updatefirstname()
	    {
	    	
	    	System.out.println("hi");

	    	
	    	
	    }
}
