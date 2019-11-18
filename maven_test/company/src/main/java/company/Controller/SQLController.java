package company.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLController {

    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    /**
     * Execute SQL, can only be used for SELECT queries. Will return a result set of the items selected
     */
    public static ResultSet execute(String sqlQuery) {
        System.out.println("Query = " + sqlQuery);
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
                return statement.executeQuery();
            }
            else{
                System.out.println("Connection returned null");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    /**
     * Execute SQL, can be used for INSERT, UPDATE, or DELETE. Will return the number of affected rows
     * @param sqlQuery
     * @return
     */
    public static int executeUpdate(String sqlQuery){
        System.out.println("Update query = " + sqlQuery);
        Connection conn = getConnection();
        if(conn != null){
            try {
                PreparedStatement statement = conn.prepareStatement(sqlQuery);
                return statement.executeUpdate();
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }else{
            System.out.println("Connection failed");
        }
        return 0;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_test", "java", "java-sucks");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sqlPrepare(String input){
        return "'" + input + "'";
    }
}
