package apitests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpartanTestWithJsonPath {

    @BeforeClass
    public void beforeClass(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");;

    }
      /*
          Given accept type is json
          And path param spartan id is 11
          When user sends a get request to /spartans/{id}
         Then status code is 200
         And content type is Json
         And   "id": 11,
               "name": "Nona",
              "gender": "Female",
              "phone": 7959094216
    */

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 11)
                .when().get("/api/spartans/{id}");

        //verify status code
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        // verify (id and name with path()
        int id = response.path("id");
        String name = response.path("name");

        assertEquals(id, 11);
        assertEquals(name, "Nona");

        //=====================================
        //same thing with the JasonPath
        //assign response to json
        JsonPath json = response.jsonPath();

        int idJson = json.getInt("id");
        String nameJson = json.getString("name");
        String genderJson = json.getString("gender");
        long phoneJson = json.getLong("phone");

        //print the values
        System.out.println("idJson = " + idJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("gender = " + genderJson);
        System.out.println("phone = " + phoneJson);

        //assert values
        assertEquals(idJson,11);
        assertEquals(nameJson,"Nona");
        assertEquals(genderJson,"Female");
        assertEquals(phoneJson,7959094216l);

    }



}
