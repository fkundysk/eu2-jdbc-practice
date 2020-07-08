package apitests;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
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
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }


    @Test
    public void SpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //we will convert Json Response to Java Collection
        Map<String,Object> spartanMap = response.body().as(Map.class);

        System.out.println(spartanMap);
        String name = (String) spartanMap.get("name");
        assertEquals(name,"Meta");

    }

    @Test
    public void allSpartanWithList(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        List<Map<String,Object>> allSpartanList = response.body().as(List.class);

        System.out.println(allSpartanList);

        //print first spartan first name
        System.out.println(allSpartanList.get(0).get("name"));

    }
    @Test
    public void regionJsonMap(){

        Response response = when().get("http://100.26.244.91:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        Map<String, Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("count"));

        System.out.println(regionMap.get("hasMore"));

        System.out.println(regionMap.get("items"));

//        List<Map<String, Object>> itemList = (List<Map<String, Object>>) regionMap.get("items");
//
//        System.out.println(itemList.get(0).get("region_name"));
    }


}
