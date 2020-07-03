package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {

    String dbUrl ="jdbc:oracle:thin:@100.26.244.91:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void metadata2() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name, last_name, salary, job_id\n" +
                "from employees\n" +
                "where ROWNUM<6\n");

        //get the database related data inside the dbMetaData object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        //get the resultset object megadata
        ResultSetMetaData rsMegaData = resultSet.getMetaData();

        //how many columns we have?
        System.out.println("Column Count = " + rsMegaData.getColumnCount());

        //columns name
        System.out.println("4th column name = " + rsMegaData.getColumnName(4));

        //print all  the column names dynamically

        for (int i =1; i<=rsMegaData.getColumnCount();i++) {
            System.out.println(rsMegaData.getColumnName(i));
        }

        // list for keeping all rows as a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //------------------
        Map<String,Object> row1 = new HashMap<>();
        //move to first row
        resultSet.next();
        row1.put(rsMegaData.getColumnName(1), resultSet.getString(1));
        row1.put(rsMegaData.getColumnName(2), resultSet.getString(2));
        row1.put(rsMegaData.getColumnName(3), resultSet.getString(3));
        row1.put(rsMegaData.getColumnName(4), resultSet.getString(4));

        System.out.println(row1.toString());

        System.out.println(row1.get("FIRST_NAME"));

        Map<String,Object> row2 = new HashMap<>();

        //move to second row
        resultSet.next();
        row2.put(rsMegaData.getColumnName(1), resultSet.getString(1));
        row2.put(rsMegaData.getColumnName(2), resultSet.getString(2));
        row2.put(rsMegaData.getColumnName(3), resultSet.getString(3));
        row2.put(rsMegaData.getColumnName(4), resultSet.getString(4));



        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData.toString());

        System.out.println(queryData.get(0).get("salary"));
        System.out.println(queryData.get(1).get("job_id"));

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metadata3() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get the resultset object megadata
        ResultSetMetaData rsMegaData = resultSet.getMetaData();

        // main list
        List<Map<String, Object>> queryData = new ArrayList<>();

        //numbers of column
        int colCount = rsMegaData.getColumnCount();

        //loop through each row
        while (resultSet.next()){

            Map<String,Object> row = new HashMap<>();

            //some code to get values dynamically
            for (int i=1; i<=colCount; i++){
                row.put(rsMegaData.getColumnName(i),resultSet.getObject(i));
            }
            //put map to main list
            queryData.add(row);
        }
        //print the result
        for (Map<String,Object> row: queryData) {
            System.out.println(row.toString());

        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
