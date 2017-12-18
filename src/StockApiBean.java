

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
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

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

import org.omg.CORBA.Current;

import Dao.DatabaseConnection;

//import com.login.DataConnect;

import Dao.StockAPIDao;
@ManagedBean
@SessionScoped
public class StockApiBean {

    private static final long serialVersionUID = 1L;
    static final String API_KEY = "GJN8GUXV0BINMDWI";
    private static final DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
    private String symbol;
    private double price;
    private int qty;
    private double amt;
    private int stock_id;
	private String table1Markup;
    private String table2Markup;
    private Date date;
	private String selectedSymbol;
    private List<SelectItem> availableSymbols;
    private String purchaseSymbol;
    private String currentPrice;
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

    @PostConstruct
    public void init() {

    }

    private String selectedInterval;
    private List<SelectItem> availableIntervals;

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

    

    public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
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

    

    public String updateDbRecord(int stockid, int qty) throws MalformedURLException, IOException{
        try {
        	StockAPIDao dao = new StockAPIDao();
        	String sym = dao.getSymbol(stockid);
        	
        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        	HttpSession session = request.getSession();
        	String user = request.getRemoteUser();
        	//Enumeration<String> names = session.getAttributeNames();
        	String name = (String)session.getAttribute("user");
        	
        	double totalbalance = dao.getTotalBalance(name);
        	
        	System.out.println("UpdateRecord symbol value :: " + sym);
        	String CurrentPrice = timeseries(sym);
        	System.out.println("Current Price inside updateDBRecord ::" + CurrentPrice);
        	String newPrice = CurrentPrice.replaceAll("^\"|\"$", "");
        	System.out.println("Current price after removing quote :: " + newPrice) ;
        	float CurrentPriceNow= Float.parseFloat(newPrice);
        	System.out.println("After Integer Current Price inside updateDBRecord ::" + CurrentPriceNow);
            System.out.println("Got from sell Page symbol:" + symbol);
            
            System.out.println("Got from sell Page qty:" + qty);
            System.out.println("Got from sell Page amt:" + amt);
        	System.out.println("Current Price before updating :: " + CurrentPrice);
        	
        	
        	List<StockAPIDao> rsforCurrent = dao.getCurrentDetails(stockid);
        	StockAPIDao sad = rsforCurrent.get(0);
        	String dbSymbol = sad.getSymbol(stockid);
        	
        	int dbQty = sad.getStockqty();
        	double dbPrice = sad.getStockprice();
        	double dbAmount = sad.getStockamt();
        	
        	System.out.println("Got from DB symbol:" + dbSymbol);
        	System.out.println("Got from DB price:" + dbPrice);
            System.out.println("Got from DB qty:" + dbQty);
            System.out.println("Got from DB amt:" + dbAmount);
            int updatedQty = dbQty - qty ;
            if(dbQty >= qty && updatedQty>= 0)
            {
		            
		            System.out.println("Updated Qty :: " + updatedQty);
		            double updatedAmt = CurrentPriceNow * qty ;
		            System.out.println("Updated Amount :: " + updatedAmt);
		            double balance = totalbalance + updatedAmt ;
		            dao.updateBalance(balance, name);
		            System.out.println("Updated Total Balance :: " + totalbalance);
		            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
		            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");
		        	Connection con = null;
//					com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
//		//			System.out.println("Datasource successful.");
//					ds.setServerName(System.getenv("ICSI518_SERVER"));
//		//			System.out.println("Datasource successful. A" + System.getenv("ICSI518_SERVER"));
//					ds.setPortNumber(Integer.valueOf(System.getenv("ICSI518_PORT")));
//			//		System.out.println("Datasource successful. B" + Integer.valueOf(System.getenv("ICSI518_PORT")));
//					ds.setDatabaseName(System.getenv("ICSI518_DB"));
//		//			System.out.println("Datasource successful. C" + System.getenv("ICSI518_DB"));
//					ds.setUser(System.getenv("ICSI518_USER"));
//			//		System.out.println("Datasource successful. D" + System.getenv("ICSI518_USER"));
//					ds.setPassword(System.getenv("ICSI518_PASSWORD"));
//		//			System.out.println("Datasource successful. E" + System.getenv("ICSI518_PASSWORD"));
//				
//					// Get a connection object
//					con = ds.getConnection();
		        	con = DatabaseConnection.getConnection();
		            //Connection conn = DataConnect.getConnection();
		        //    Statement statement = con.createStatement();
		            
		            //get userid
		//            System.out.println("Getting userid");
		//            Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
		//                    .getExternalContext()
		//                    .getSessionMap().get("userid"));
		//            System.out.println("Getting user");
		//            String user = (String) FacesContext.getCurrentInstance()
		//                    .getExternalContext()
		//                    .getSessionMap().get("user");

	            	long time = System.currentTimeMillis();
	            	System.out.println("Current Time :: " + time);
	            	date = new Date(time);
					
//	            	
//	            	Time t = new java.sql.Time(503000);
//	            	timeformat.setTimeZone(TimeZone.getTimeZone("GMT"));
//	            	String currentTime = timeformat.format(t);

	            	
	            	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	                 System.out.println(timestamp);
	                 String currentTime = timeformat.format(timestamp);
	                 
	                dao.sellCommandTable(sym,qty,name);
					dao.insertIntoCustomerSell(name,sym,qty,CurrentPriceNow,updatedAmt,date,currentTime);
		            System.out.println("Username tobe entered : " + name);
		            System.out.println("symbol:" + symbol);
		            System.out.println("price:" + price);
		            System.out.println("qty:" + qty);
		            System.out.println("amt:" + amt);
		          //  statement.executeUpdate("INSERT INTO customerpurchase ('user', `stock_symbol`, `qty`, `price`, `amt`) "
		  //                  + "VALUES ('" + name + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
		            String sql = "update customerpurchase set qty= '"+ updatedQty + "' where uid=" + stockid + "";
		            //System.out.println("Before Updating stock sql:: " + sql);
		            PreparedStatement st = con.prepareStatement(sql);
		            
		            boolean rs = st.execute();
		            
		            st.close();
		            con.close();
		            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Sold Stock",""));
            }
            else
            {
            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You don't have enough stocks to sell.",""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "purchase";
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
    
    public String createDbRecord(String symbol, double price, int qty, double amt) {
        try {
        	
        	
        	System.out.println("Inside Create DB record");
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");
        	Connection con = null;
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
            //Connection conn = DataConnect.getConnection();
        //    Statement statement = con.createStatement();
            
            //get userid
//            System.out.println("Getting userid");
//            Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .getSessionMap().get("userid"));
//            System.out.println("Getting user");
//            String user = (String) FacesContext.getCurrentInstance()
//                    .getExternalContext()
//                    .getSessionMap().get("user");
        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        	HttpSession session = request.getSession();
        	String user = request.getRemoteUser();
        	//Enumeration<String> names = session.getAttributeNames();
        	String name = (String)session.getAttribute("user");
        	commonTable(symbol, qty,name);
            System.out.println("Username tobe entered : " + name);
            System.out.println("symbol:" + symbol);
            System.out.println("price:" + price);
            System.out.println("qty:" + qty);
            System.out.println("amt:" + amt);
            
            double totalbalance = StockAPIDao.getTotalBalance(name);
            System.out.println("Total Balance Remained :: " + totalbalance);
            
            if(totalbalance > amt)
            {
            	double updatedAmt = totalbalance - amt;
            
            	StockAPIDao.updateBalance(updatedAmt, name);
            	long time = System.currentTimeMillis();
            	System.out.println("Current Time :: " + time);
            	date = new Date(time);
//            	
//            	Time t = new java.sql.Time(503000);
//            	timeformat.setTimeZone(TimeZone.getTimeZone("GMT"));
//            	String currentTime = timeformat.format(t);
//            	
//                java.util.Date today = new java.util.Date();
//                System.out.println(new java.sql.Timestamp(today.getTime()));
            	
            	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                 System.out.println(timestamp);
                 String currentTime = timeformat.format(timestamp);
          //  statement.executeUpdate("INSERT INTO customerpurchase ('user', `stock_symbol`, `qty`, `price`, `amt`) "
  //                  + "VALUES ('" + name + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
            String sql = "insert into customerpurchase(user,stock_symbol,qty,price,amt,date,timestamp,purchasedbywhom)	VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,symbol);
            st.setInt(3, qty);
            st.setDouble(4, price);
            st.setDouble(5, amt);
            st.setDate(6, date);
            st.setString(7, currentTime);
            st.setString(8, "purchased by you");
            boolean rs = st.execute();
            
            st.close();
            con.close();
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully purchased stock",""));
            }
            else
            {
            	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You do not have enough balance to purchase stocks.",""));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "purchase";
    }
    
    public boolean createDbRecord(String name, String symbol, double price, int qty, double amount, String managerName) {
        try {
        	System.out.println("Inside Create DB record");
        	
        	
            //System.out.println("symbol: " + this.symbol + ", price: " + this.price + "\n");
            //System.out.println("qty: " + this.qty + ", amt: " + this.amt + "\n");
        	Connection con = null;
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

            System.out.println("Username tobe entered : " + name);
            System.out.println("symbol:" + symbol);
            System.out.println("price:" + price);
            System.out.println("qty:" + qty);
            System.out.println("amt:" + amount);
            
            double totalbalance = StockAPIDao.getTotalBalance(name);
            System.out.println("Total Balance Remained :: " + totalbalance);
            System.out.println("Amount :: " + amount);
            if(totalbalance > amount)
            {
            	double updatedAmt = totalbalance - amount;
            
            	StockAPIDao.updateBalance(updatedAmt, name);
            	long time = System.currentTimeMillis();
            	System.out.println("Current Time :: " + time);
            	date = new Date(time);
//            	
//            	Time t = new java.sql.Time(503000);
//            	timeformat.setTimeZone(TimeZone.getTimeZone("GMT"));
//            	String currentTime = timeformat.format(t);
//            	
//                java.util.Date today = new java.util.Date();
//                System.out.pr'intln(new java.sql.Timestamp(today.getTime()));
            	
            	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                 System.out.println(timestamp);
                 String currentTime = timeformat.format(timestamp);
          //  statement.executeUpdate("INSERT INTO customerpurchase ('user', `stock_symbol`, `qty`, `price`, `amt`) "
  //                  + "VALUES ('" + name + "','" + symbol + "','" + qty + "','" + price + "','" + amt +"')");
            String sql = "insert into customerpurchase(user,stock_symbol,qty,price,amt,date,timestamp,purchasedbywhom)	VALUES(?,?,?,?,?,?,?,?)";
            System.out.println("Customer Purchase SQL :: " + sql);
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,symbol);
            st.setInt(3, qty);
            st.setDouble(4, price);
            st.setDouble(5, amount);
            st.setDate(6, date);
            st.setString(7, currentTime);
            st.setString(8, managerName);
            System.out.println("Customer Purchase SQL :: " + sql);
            boolean rs = st.execute();
            
            st.close();
            con.close();
         //   FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer Successfully purchased stock",""));
            if(rs)
            {
            	return true;
            }
            }
            else
            {
        //    	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Customer You do not have enough balance to purchase stocks.",""));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return false;
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


    public void purchaseStock() {
        System.out.println("Calling function purchaseStock()");
        System.out.println("stockSymbol: " + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockSymbol"));
        System.out.println("stockPrice" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("stockPrice"));
        return;
    }
    
    
    
    
    
    
	public static void commonTable(String sym, int quan, String username)
	{
		try
		{
			System.out.println("In Dao of getting common Table" + sym + quan);
				Connection con = null;
				ResultSet rs=null;
				con= DatabaseConnection.getConnection();
				System.out.println("Connection Successful");
				// Get a prepared SQL statement
				String sql;
//				String sqlLogin;
		
					sql = "select * from customerstocks where customername= '" + username +"' && stock_symbol='" + sym +"'";
					//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//					System.out.println("Before Inserting into user");
//					sqlLogin = "insert into user(username,password) VALUES(?,?)";
			
				PreparedStatement st = con.prepareStatement(sql);
//				PreparedStatement st1 = con.prepareStatement(sqlLogin);
				System.out.println("PreparedStatement Successful" + st);
				// Execute the statement
				 rs = st.executeQuery();
////				 rs.next();
//				 String stock = rs.getString("stock_symbol");
				 if(rs.next())
				 {
					 StockAPIDao dao = new StockAPIDao();
					 int qu = dao.getDBQuantity(sym,username);
					 System.out.println("Original Quantity from DB." + qu);
					 int newqty = qu + quan;
					 sql = "update customerstocks set quantity=" + newqty + " where customername='" +username +"' && stock_symbol='"+ sym +"'";
					 PreparedStatement st1 = con.prepareStatement(sql);
					 System.out.println("commonTable insert :: " + sql);
					 boolean rs1 = st1.execute();

					 
				 }
				 else
				 {
					 sql = "insert into customerstocks(stock_symbol,customername,quantity) VALUES(?,?,?)";
					 PreparedStatement st1 = con.prepareStatement(sql);
					 System.out.println("commonTable insert :: " + sql);
					 st1.setString(1, sym);
					 st1.setString(2, username);
					 st1.setInt(3, quan);
					 boolean rs1 = st1.execute();
				 }
				 System.out.println("Resultset Value is :: " + rs);
				 st.close();
				 DatabaseConnection.close(con);
				 
				 
		}
		catch(SQLException  e){
			// TODO Auto-generated catch block
			System.out.println("Catch of Dao");
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void commonTable(String sym, int quan)
	{
		try
		{
			System.out.println("In Dao of getting common Table" + sym + quan);
				Connection con = null;
				ResultSet rs=null;
	        	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	        	HttpSession session = request.getSession();
	        	String user = request.getRemoteUser();
	        	//Enumeration<String> names = session.getAttributeNames();
	        	String username = (String)session.getAttribute("user");
				con= DatabaseConnection.getConnection();
				System.out.println("Connection Successful");
				// Get a prepared SQL statement
				String sql;
//				String sqlLogin;
		
					sql = "select * from customerstocks where customername= '" + username +"' && stock_symbol='" + sym +"'";
					//sql = "insert into customer(firstname,lastname,address,email,phonenumber)	VALUES(?,?,?,?,?)";
//					System.out.println("Before Inserting into user");
//					sqlLogin = "insert into user(username,password) VALUES(?,?)";
			
				PreparedStatement st = con.prepareStatement(sql);
//				PreparedStatement st1 = con.prepareStatement(sqlLogin);
				System.out.println("PreparedStatement Successful" + st);
				// Execute the statement
				 rs = st.executeQuery();
////				 rs.next();
//				 String stock = rs.getString("stock_symbol");
				 if(rs.next())
				 {
					 StockAPIDao dao = new StockAPIDao();
					 int qu = dao.getDBQuantity(sym,username);
					 System.out.println("Original Quantity from DB." + qu);
					 int newqty = qu + quan;
					 sql = "update customerstocks set quantity=" + newqty + " where customername='" +username +"' && stock_symbol='"+ sym +"'";
					 PreparedStatement st1 = con.prepareStatement(sql);
					 System.out.println("commonTable insert :: " + sql);
					 boolean rs1 = st1.execute();

					 
				 }
				 else
				 {
					 sql = "insert into customerstocks(stock_symbol,customername,quantity) VALUES(?,?,?)";
					 PreparedStatement st1 = con.prepareStatement(sql);
					 System.out.println("commonTable insert :: " + sql);
					 st1.setString(1, sym);
					 st1.setString(2, username);
					 st1.setInt(3, quan);
					 boolean rs1 = st1.execute();
				 }
				 System.out.println("Resultset Value is :: " + rs);
				 st.close();
				 DatabaseConnection.close(con);
				 
				 
		}
		catch(SQLException  e){
			// TODO Auto-generated catch block
			System.out.println("Catch of Dao");
			e.printStackTrace();
		}
	}
	
	
	
}