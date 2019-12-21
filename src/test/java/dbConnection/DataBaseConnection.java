package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import base.Testbase;

public class DataBaseConnection extends Testbase {

	public  Statement stmt = null;
	public  Connection conn = null;
	public  Connection conn1 = null;
	public  Connection conn2 = null;
	static ResultSet rs = null;
	String db_IP_UUSD = getDBIPUUSD();
	String db_userName_uusd = getDBUserNameforUUSD();
	String db_Password_uusd = getDBPasswordForUUSD();
	String db_IP_TM = getDBIPTM();
	String db_userName_TM = getDBUserNameforTM();
	String db_Password_TM = getDBPasswordForTM();
	String db_IP = getDBIP();
	String db_userName_comic = getDBUserNameforComic();
	String db_Password_comic = getDBPasswordForComic();
	public DataBaseConnection() throws SQLException, ClassNotFoundException 
	{
		ComicConnection();
		UUSDConnection();
		TMConnection();
	}
	public Connection ComicConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@" + db_IP + ":"+ getDBSchema(), db_userName_comic, db_Password_comic);
		conn.setAutoCommit(true);
		System.out.println("Commic Connection Created"+db_IP +getDBSchema()+""+db_userName_comic+""+db_Password_comic);
		return conn;
	}
	public Connection UUSDConnection() throws SQLException, ClassNotFoundException
		{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn1 = DriverManager.getConnection("jdbc:oracle:thin:@" + db_IP_UUSD + getDBSchemaUUSD(), db_userName_uusd, db_Password_uusd);
		conn1.setAutoCommit(true);
		System.out.println("UUSD Connection Created"+db_IP_UUSD +""+db_userName_uusd+""+db_Password_uusd);
		return conn1;

		}
	public Connection TMConnection() throws SQLException, ClassNotFoundException
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	conn2 = DriverManager.getConnection("jdbc:oracle:thin:@" + db_IP_TM + getDBSchemaTM(), db_userName_TM, db_Password_TM);
	conn2.setAutoCommit(true);
	System.out.println("TM Connection Created"+db_IP_TM +""+db_userName_TM+""+db_Password_TM);
	return conn2;

	}
	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet rset = stmt.executeQuery(query);
		return rset;
	}

	public int deleteQuery(String query) throws SQLException {
		int affectedRow = stmt.executeUpdate(query);
		return affectedRow;
	}


	private String getDBIP() {	
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_IP");
			//param =System.getProperty("db_IP");
		}
		return param;
	}
	private String getDBIPUUSD() {
		
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_IP_UUSD");
			//param =System.getProperty("DB_IP_UUSD");
		} 
		return param;
	}
	
	private String getDBSchema(){
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("Db_Schema");
		}
		return param;
	}
		private String getDBSchemaUUSD(){
	    String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("Db_Schema_UUSD");
		}
		return param;
	}
	
	private String getDBUserNameforComic(){
	
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_UserName");
		}
		return param;
	}
	private String getDBPasswordForComic(){
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_Password");
		}
		return param;
	}
     private String getDBUserNameforUUSD(){
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_UserName_UUSD");
		}
		return param;
	}	

	private String getDBPasswordForUUSD(){		
		String param = null;
		if (param == null || param.contentEquals("")) {
			param = Testbase.getUpdatedProptiesFile().getProperty("DB_Password_UUSD");
		}
		return param;
	}
	 private String getDBUserNameforTM(){
			String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("DB_UserName_TM");
			}
			return param;
		}	

		private String getDBPasswordForTM(){		
			String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("DB_Password_TM");
			}
			return param;
		}
		private String getDBSchemaTM(){
		    String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("Db_Schema_TM");
			}
			return param;
		}
		private String getDBIPTM() {
			
			String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("DB_IP_TM");
			} 
			return param;
		}
		private String getTMIP(){
		    String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("TM_IP");
			}
			return param;
		}
		private String getTMPort() {
			
			String param = null;
			if (param == null || param.contentEquals("")) {
				param = Testbase.getUpdatedProptiesFile().getProperty("TM_PORT");
			} 
			return param;
		}
}

