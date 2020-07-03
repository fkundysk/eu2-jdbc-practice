package jdbctests;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.*;

public class dbutils_practice {


    @Test
    public void test1(){

        //create connection
        DBUtils.createConnection();

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap("select * from departments");

//        Map<String, List<Map<String, Object>>> queryData2 = new HashMap<>();
//        queryData2.put("33",queryData);

//        System.out.println("queryData2 = " + queryData2);

        for (Map<String, Object> row : queryData) {
            System.out.println(row);
        }
//        System.out.println(queryData.get(1).get("DEPARTMENT_NAME"));
//        //close connection
        DBUtils.destroy();
    }

    @Test
    public void test2(){

        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select * from employees where employee_id =100");
        System.out.println(rowMap.toString());
        DBUtils.destroy();

    }
    @Test
    public void testHash(){
        Map <String, String> tt11= new HashMap<>();
        tt11.put("bb", "rr");
        tt11.put("aa", "bb");
        tt11.put("cc", "hh");

        for (Map.Entry each : tt11.entrySet()){

            System.out.println("Key : " + each.getKey() + " ---> " + "Value : " +  each.getValue());
        }

    }

}