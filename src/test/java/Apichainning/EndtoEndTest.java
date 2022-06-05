package Apichainning;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndtoEndTest {
	
	
	Response response;

	String BaseURI = "http://54.196.130.49:8088/employees";
	
	@Test
	public void test1() {
		
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("lakhi", "singh","1000", "abc@yahoo.com");
		Assert.assertEquals(response.getStatusCode(), 201);
		
		JsonPath japth = response.jsonPath();

		int EmpId = japth.get("id");
		System.out.println("Id :-" + EmpId);
		
		response = PutMethod(EmpId, "rajwinder", "kaur","1000", "raj@yahoo.com");
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = DeleteMethod(EmpId);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getBody().asString(), "");
		
		
		response = Getsingle(EmpId);
		Assert.assertEquals(response.getStatusCode(), 400);
	

	

}

	public Response GetMethodAll() {

		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();

		Response response = request.get();

		return response;

	}
	
	public Response PostMethod(String Name, String lname, String Salary, String emailid) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();

		jobj.put("firstName", Name);
		jobj.put("lastName", lname);
		jobj.put("salary", Salary);
		jobj.put("email", emailid);
		

		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.post();

		return response;

	}
	
	public Response PutMethod(int EmpId, String Name, String lname, String Salary, String emailid) {

		RestAssured.baseURI = BaseURI;

		JSONObject jobj = new JSONObject();
		jobj.put("firstName", Name);
		jobj.put("lastName", lname);
		jobj.put("salary", Salary);
		jobj.put("email", emailid);

		
		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(jobj.toString())
				.put("/" + EmpId);

		return response;

	}

	
	public Response DeleteMethod(int EmpId) {
		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();

		Response response = request.delete("/" + EmpId);

		return response;

	}	
	
	public Response Getsingle(int EmpId)

	{
		RestAssured.baseURI = BaseURI;

		RequestSpecification request = RestAssured.given();

		Response response = request.get("/" + EmpId);

		return response;

	}
	
}
	
	
	
	
	
