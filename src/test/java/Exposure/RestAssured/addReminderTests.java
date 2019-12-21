package Exposure.RestAssured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import org.testng.annotations.Test;

import XMLUtils.XMLUtil;
import base.Testbase;
import model.AddReminder;
import model.BTA;

public class addReminderTests extends Testbase {
	static String requestBody1 =null;
	Testbase tbase;

	@Test
	public void addReminderInsertion_BEP14470001() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException
	{
		
		
		BTA obj = new BTA();
		AddReminder addrem =  new AddReminder();
		tbase=new Testbase();
		String tmServerIp=Testbase.getUpdatedProptiesFile().getProperty("TM_IP");
		String tmPort=Testbase.getUpdatedProptiesFile().getProperty("TM_PORT");
		String macAddress=tbase.getDataForReminderPostBTACall().get(3);
		String externalId=tbase.getDataForReminderPostBTACall().get(1);
		String scheduleTrailId=tbase.getDataForReminderPostBTACall().get(2);
		System.out.println("MAC==>"+macAddress);
		System.out.println("EXTERNAL ID==>"+externalId);
		System.out.println("SCHEDULE TRAIL ID==>"+scheduleTrailId);
		System.out.println(tmServerIp);
		obj.setXsinoNamespaceSchemaLocation("BTADocAddReminder.xsd");
		addrem.setReminderType("Reminder");
		addrem.setMinsBeforeStart("8");
		addrem.setChannelExtID(externalId);
		addrem.setSchTrailID(scheduleTrailId);
		obj.setAddReminder(addrem);

		XMLUtil xmlUtil= new  XMLUtil();
		requestBody1 = xmlUtil.convertToXml(obj, obj.getClass());
		System.out.println(""+requestBody1);
		given().
		header("Content-Type","application/xml").
		contentType(ContentType.XML).
		body(requestBody1).
		when().
		post("http://"+tmServerIp+":"+tmPort+"/broker/bta/addReminder?MAC="+macAddress+"&InterfaceVersion=5.2.0").
		then().
		statusCode(200).
		//body("BTAResponse.Status",equalTo("Success")).
		//body(matchesXsdInClasspath("/src/resources/BTAResponseSuccess.xsd")).
		log().all();
	}
}
