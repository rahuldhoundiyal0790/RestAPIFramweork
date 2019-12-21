package Exposure.RestAssured;

import java.sql.SQLException;
import java.util.Map;

import org.testng.annotations.Test;

import RestAPIHelper.RestUtil;
import base.Testbase1;
import io.restassured.RestAssured;
import testData.AddReminderTestDataCreation;

public class AddReminderTestCase extends Testbase1{

	@Test
	public void addReminderTestCase() throws SQLException, ClassNotFoundException
	{
		AddReminderTestDataCreation addReminderData = new AddReminderTestDataCreation();
		RestAssured.baseURI = "http://10.67.177.45:8085";//+tmServerIp+":"+tmPort;
		Map<String,String> reminderData = addReminderData.getReminderDataFromDB();
		String postBody = addReminderData.createAddReminderPOSTBody(reminderData);
		RestUtil.sendPostAPI(postBody, "application/xml", reminderData.get("macaddress")).then().statusCode(200);
	}
	
}
