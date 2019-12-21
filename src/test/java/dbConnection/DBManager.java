package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import base.Testbase1;

public class DBManager extends Testbase1 {

	public static Connection con;
	
	public static Connection getDBConnection(String dbUserName,String dbPassword) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@" + DBIP + ":"+ DBSCHEMA, dbUserName, dbPassword);
		con.setAutoCommit(true);
		System.out.println("Commic Connection Created"+DBIP +DBSCHEMA+""+dbUserName+""+dbPassword);
		return con;
	}

	public static void closeConnection() throws SQLException {
		try{
			con.close();
		}
		finally{
			con.close();
		}
		
	}

}
