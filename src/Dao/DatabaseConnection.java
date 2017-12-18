// I referred Singlton Database Concept here https://community.oracle.com/docs/DOC-918906
package Dao;
import java.sql.Connection;
import java.sql.DriverManager;

public final class DatabaseConnection {

    private static String hostName;
    private static String portNumber;
    private static String databaseName;
    private static String userName;
    private static String password;
    static com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
    private DatabaseConnection()
    {
    	hostName = "localhost";
    	portNumber = "3306";
    	databaseName = "userdb_userdetails";
    	userName = "root";
    	password = "root";
    }
    public static Connection getConnection() {
        try {
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
			
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = ds.getConnection();
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }

    
    private static class DbSingletonManagerHolder {
        private final static DatabaseConnection instance = new DatabaseConnection();
    }
    
    
    public static DatabaseConnection getInstance() {
        try {
            return DbSingletonManagerHolder.instance;
        } catch (ExceptionInInitializerError ex) {

        }
        return null;
    }
    
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }
}