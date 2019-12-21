package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import dbConnection.DataBaseConnection;
import java.util.Random;

public class Testbase 
{
	public ResultSet resultset,resultset1,resultset2;
	public Statement statement,statement1,statement2;
	public String tvid;
	public static int i=0;
	public int subscriberNA=0;
	Random rand = new Random();
	DataBaseConnection db;
	protected static String configFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "java" + File.separator
			+ "config" + File.separator + "configuration.properties";

	@BeforeSuite(enabled=true)
	public void initialiseDB() throws ClassNotFoundException, SQLException
	{
		db=new DataBaseConnection();
		System.out.println("Database Connections Established");
	}
	public static Properties getUpdatedProptiesFile() {
		Properties property = new Properties();
		FileInputStream FIS;
		try {
			FIS = new FileInputStream(configFilePath);
			property.load(FIS);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return property;
	}
	@AfterSuite(enabled=true)
	public void closeConnection() throws SQLException, ClassNotFoundException
	{
		db=new DataBaseConnection();
		System.out.println("After Suite");
		if(db.conn!=null && db.conn1!=null && db.conn2!=null)
		{
			db.conn.close();
			db.conn1.close();
			db.conn2.close();
			statement.close();
			statement2.close();
			resultset.close();
			resultset2.close();
		}
		if(db.conn==null && db.conn1==null && db.conn2==null)
		{
			System.out.println("Database Connections,Statements and Resultset are closed");
		}
	}
	public String getMacFromSettopBoxTable() throws SQLException, ClassNotFoundException
	{
		db=new DataBaseConnection();
		statement2=db.conn2.createStatement();
		resultset2=statement2.executeQuery("select MACADDRESS from SETTOPBOXES");
		ArrayList<String> arraylist=new ArrayList<String>();
		while(resultset2.next())  
		{
			arraylist.add(resultset2.getString(1));
		}
		String Arraytvid=arraylist.get(rand.nextInt(100) + 1);
		//System.out.println(Arraytvid);
		return Arraytvid;
	}
	public ArrayList<String> getDataForReminderPostBTACall() throws SQLException, ClassNotFoundException
	{
		initialiseDB();
		statement2=db.conn2.createStatement();
		resultset2=statement2.executeQuery("SELECT STB.MACADDRESS, DTV.EXTERNALID, SFS.SCHEDULETRAILID FROM subscribers s,customer_na@TO_UUSD cna,SETTOPBOXES STB,subscriberpackages sp, packageitems pi,dtvchannels dtv,schedulefilespec sfs WHERE S.ID = CNA.SUBSCRIBER_ID AND S.ID = STB.ASSIGNEDTOSUBSCRIBERID AND SP.SUBSCRIBERID = S.ID AND SP.PACKAGEID = PI.PACKAGEID AND PI.ITEMID = DTV.ID AND DTV.CHANNELREFERENCENO = SFS.CHANNELREFERENCENUMBER AND S.STATUS = 'Active' AND S.STATUSCODE = 'A' AND STB.STATUS='Assigned'  AND STB.RECORDSTATUSCODE='A' AND rownum < 100");
		ArrayList<String> arraylist=new ArrayList<String>();
		while(resultset2.next())  
		{
			arraylist.add(resultset2.getString(1));
			arraylist.add(resultset2.getString(2));
			arraylist.add(resultset2.getString(3));
		}
		/*String macAddress=arraylist.get(1);
		String externalId=arraylist.get(2);
		String scheduleTrailId=arraylist.get(3);
		System.out.println(macAddress);
		System.out.println(externalId);
		System.out.println(scheduleTrailId);*/
		return arraylist;

	}

	public List<String> getDataFromComicdatabase() throws SQLException, ClassNotFoundException
	{
		db=new DataBaseConnection();
		ArrayList<String> arraylist=new ArrayList<String>();
		try{
			System.out.println("=========>"+tvid);
			statement=db.conn.createStatement();
			resultset=statement.executeQuery("select * from CONSENTS where TVID="+tvid);
			System.out.println("=========>"+tvid);
			while(resultset.next())  
			{
				arraylist.add(resultset.getString(3));
				arraylist.add(resultset.getString(4));
				arraylist.add(resultset.getString(5));
				arraylist.add(resultset.getString(7));
				arraylist.add(resultset.getString(8));
				System.out.println("---->"+arraylist.get(0));
			} 
			int size = arraylist.size(); 
			System.out.println("size of ArrayList after clearing elements: " + size);


		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return arraylist;		
	}

	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		Testbase ts=new Testbase();
		ts.getDataForReminderPostBTACall();

	}

}
