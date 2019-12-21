package Exposure.RestAssured;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;
//import com.jayway.restassured.internal.matcher.xml.XmlXsdMatcher 
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import model.BTA;
import model.Consents;

import org.junit.BeforeClass;
import org.testng.annotations.Test;

import base.Testbase;
import XMLUtils.XMLUtil;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class setConsentTest{
	//@Test
	static String requestBody1 =null;
	Testbase tbase;
	
	@Test
	public void testForSerializationOrPojoUsingXML() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException
	{
		BTA obj = new BTA();
    	Consents ob1 =  new Consents();
    	tbase=new Testbase();
    	String comicServerIp=Testbase.getUpdatedProptiesFile().getProperty("comic_ip");
    	String comicPort=Testbase.getUpdatedProptiesFile().getProperty("comic_port");
    	String macAddress=tbase.getMacFromSettopBoxTable();
    	
    	obj.setXsinoNamespaceSchemaLocation("BTADocSetConsent.xsd");
    	System.out.println(macAddress);
    	System.out.println(comicServerIp);
    	ob1.setConsentType("TVTA");
    	ob1.setConsentValue("OPTIN");
    	ob1.setLastUpdatedBy("MOHAN");
    	ob1.setConsentMessage("REST ASSURED");
    	obj.setConentDetails(ob1);
    	
        XMLUtil xmlUtil= new  XMLUtil();
        requestBody1 = xmlUtil.convertToXml(obj, obj.getClass());
		System.out.println(""+requestBody1);
		given().
			header("Content-Type","application/xml").
			contentType(ContentType.XML).
			body(requestBody1).
		when().
			post("http://"+comicServerIp+":"+comicPort+"/broker/bta/setConsent?MAC="+macAddress+"&InterfaceVersion=5.2.0").
		then().
			statusCode(200).
			body("BTAResponse.Status",equalTo("Success")).
			//body(matchesXsd("/src/resources/BTAResponseSuccess.xsd")).
			log().all();
	}
}
