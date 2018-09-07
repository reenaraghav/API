import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class LocationsTest {

	@BeforeMethod()
	public void setUp(){
		RestAssured.baseURI="http://cmapi.bananaappscenter.com/";
		RestAssured.basePath="api/Location/LocationDetails";
		
	}
	public static Response doGetRequest( ) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get().
                        then().log().everything().contentType(ContentType.JSON).extract().response();
    }
	@Test
	public void getLocationDetails(){
		/*RestAssured.get().
		then().log().everything().
		assertThat().
		statusCode(200).and().
		assertThat().
		body("Msg.Message",equalTo("Success Location Details"));
		Map<String,String> msg=RestAssured.get().jsonPath().get("Msg");
		//msg.remove("StatusCode");
		System.out.println(msg);
		for (Map.Entry<String, String> entry : msg.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}*/
		
		Response response=doGetRequest();
		//List<String> jsonResponse = response.jsonPath().getList("$");
        //System.out.println(jsonResponse.size());
        
        Map<String, Object> msg = response.jsonPath().getMap("Msg");
        System.out.println(msg.get("Message"));
		Assert.assertEquals(msg.get("Message"), "Success Location Details");
		
		for(Map.Entry<String, Object> entry:msg.entrySet()){
			String key=entry.getKey();
			System.out.println(key);
			Object value=entry.getValue();
			System.out.println(value);
		}
		
	}
	
}
