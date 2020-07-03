package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class simpleGetRequest {

    String hrurl = "http://100.26.244.91:1000/ords/hr/regions";
    @Test
    public void test1(){

        Response response = RestAssured.get(hrurl);
        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

//      System.out.println(response.body().asString());
        //print the json body
        response.prettyPrint();

    }
    /*
    Given accept type is json
    When user sends get request to regions endpoint
    Then response status code must be 200
    and body is json format
    * */

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl);
        // Verify response status code is 200
        Assert.assertEquals(response.statusCode(),200, "Verify the status code");

        // verify content type is application/json

//        String contentType = response.contentType();
//        System.out.println("contentType = " + contentType);

        System.out.println("res = " + response.contentType());
        Assert.assertEquals(response.contentType(),"application/xml","Verify response contentType is application/json");


    }

    @Test
    public void tets3(){

        RestAssured.given().accept(ContentType.JSON)
                .when().get(hrurl).then()
                .assertThat().statusCode(200).and()
                .contentType("application/json");


    }
    /*
       Given accept type is json
       When user sends get request to regions/2
       Then response status code must be 200
       and body is json format
       and response body contains Americas
       * */

    @Test
    public void test4(){
        Response response = given().accept(ContentType.JSON)
                .when().get(hrurl+"/2");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify content type
        Assert.assertEquals(response.contentType(),"application/json");

        //verify body includes Americas
        Assert.assertTrue(response.body().asString().contains("Americas"), "Verify the body includes Americas");


    }

}
