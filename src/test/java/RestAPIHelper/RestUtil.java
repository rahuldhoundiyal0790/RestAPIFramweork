package RestAPIHelper;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtil {

	public static Response sendGetAPI(){
		return null;
	}
	public static Response sendPostAPI(String postBody,String contentType,String... reminderData ){
	Response response = 	
		given().
		header("Content-Type",contentType).
		contentType(ContentType.XML).
		body(postBody).
	when().
		post("/broker/bta/addReminder?MAC="+reminderData+"&InterfaceVersion=5.2.0");
	return response;
	}
}
