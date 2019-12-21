package testData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import XMLUtils.XMLUtil;
import dbConnection.DBManager;
import model.AddReminder;
import model.BTA;

public class AddReminderTestDataCreation {


	public String createAddReminderPOSTBody(Map<String, String> reminderData) {
		BTA obj = new BTA();
		AddReminder addrem =  new AddReminder();
		obj.setXsinoNamespaceSchemaLocation("BTADocAddReminder.xsd");
		addrem.setReminderType("Reminder");
		addrem.setMinsBeforeStart("8");
		addrem.setChannelExtID(reminderData.get("externalID"));
		addrem.setSchTrailID(reminderData.get("scheduletrailID"));
		obj.setAddReminder(addrem);
		XMLUtil xmlUtil= new  XMLUtil();
		String requestBody1 = xmlUtil.convertToXml(obj, obj.getClass());
		return requestBody1;

	}

	public Map<String, String> getReminderDataFromDB() throws SQLException, ClassNotFoundException {
		Statement stmt = null;
		ResultSet resultset2 = null;
		Map<String,String> mapData = null;
		try{
			stmt = DBManager.getDBConnection("","").createStatement();
			resultset2=stmt.executeQuery("SELECT STB.MACADDRESS, DTV.EXTERNALID, SFS.SCHEDULETRAILID FROM subscribers s,customer_na@TO_UUSD cna,SETTOPBOXES STB,subscriberpackages sp, packageitems pi,dtvchannels dtv,schedulefilespec sfs WHERE S.ID = CNA.SUBSCRIBER_ID AND S.ID = STB.ASSIGNEDTOSUBSCRIBERID AND SP.SUBSCRIBERID = S.ID AND SP.PACKAGEID = PI.PACKAGEID AND PI.ITEMID = DTV.ID AND DTV.CHANNELREFERENCENO = SFS.CHANNELREFERENCENUMBER AND S.STATUS = 'Active' AND S.STATUSCODE = 'A' AND STB.STATUS='Assigned'  AND STB.RECORDSTATUSCODE='A' AND rownum < 100");
			mapData=new HashMap<String,String>();
			resultset2.next();
			mapData.put("macaddress", resultset2.getString(1));
			mapData.put("externalID", resultset2.getString(2));
			mapData.put("scheduletrailID", resultset2.getString(3));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		finally{
			stmt.close();
			resultset2.close();
			DBManager.closeConnection();
		}
		return mapData;
	}

}
