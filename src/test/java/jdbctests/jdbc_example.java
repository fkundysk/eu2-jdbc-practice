package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jdbc_example {

    String dbUrl ="jdbc:oracle:thin:@100.26.244.91:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement();

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from regions");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));

        }
        //===================================
        resultSet = statement.executeQuery("select * from departments");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));

        }

        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void CountRowAndNavigate() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        // how to find how many rows we have for the query
        //go  to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println("rowCount = " + rowCount);
        //we need to move before first row to get full list since we are at the last row right now
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }



        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metadata() throws SQLException {

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //get the database related data inside the dbMetaData object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("User: " + dbMetaData.getUserName());
        System.out.println("Database Product Name = " + dbMetaData.getDatabaseProductName());
        System.out.println("Database Product Version = " +dbMetaData.getDatabaseProductVersion());
        System.out.println("Driver  = " + dbMetaData.getDriverName());
        System.out.println("Driver Version = " + dbMetaData.getDriverVersion());

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



        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void metadata2() throws SQLException {
        //create the connection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
        System.out.println("rsMetadata.getColumnCount() = " + rsMetadata.getColumnCount());
        //list for keeping all rows as a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        //-----------------------
        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRESS");

        System.out.println(row1.toString());

        System.out.println(row1.get("first_name"));

        Map<String,Object> row2 = new HashMap<>();

        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData.get(0).get("salary"));
        System.out.println(queryData);


        //closing all connections
        resultSet.close();
        statement.close();
        connection.close();

    }


}
