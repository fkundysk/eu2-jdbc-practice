package apitests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanGetRequest {

    String spartanUrl = "http://100.26.244.91:8000";



    @Test
    public void test1(){

    Response response = given().accept(ContentType.JSON)
                        .and().auth().basic("admin", "admin")
                        .when().get(spartanUrl+ "/api/spartans");

        System.out.println(response.statusCode());
        response.prettyPrint();


    }

    /*
    When users send a get request to / api / spartans/3
    then status code should be 200
    and content type should be application/jason; charset=UFT-8
    and jason body should contain Fidole
     */

    @Test
    public void test2 (){

        Response response = given().auth().basic("admin", "admin")
                            .when().get(spartanUrl+"/api/spartans/3");
        // verify status code
            assertEquals(response.statusCode(),200);

        //verify contentType
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //verify body contains Fidole
        assertTrue(response.body().asString().contains("Fidole"));

    }
    /*
    Given no header provided
    When Users sends GET request to api/hello
    Then response status could should be 200
    And content type header should be "text/plain;charset=UTF-8"
    And header should contain date
    And content-lenght should be 17
    And body should be "Hello from Sparta"
    */

    @Test
    public void test3 (){

        Response response = when().get(spartanUrl + "/api/hello");

        //Verify status code 200
        assertEquals(response.statusCode(),200);

        // Verify content type header
        assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //Verify we have headers contain which is named Date
        assertTrue(response.headers().hasHeaderWithName("Date"));


        // Verify content-lenght is 17
        assertEquals(response.getHeader("Content-Length"), "17");

        // Verify body should be "Hello from Sparta"
        //       System.out.println("response.getBody().asString() = " + response.getBody().asString());

        assertTrue(response.body().asString().contains("Hello from Sparta"));
        assertEquals(response.getBody().asString(), "Hello from Sparta");

    }


}
