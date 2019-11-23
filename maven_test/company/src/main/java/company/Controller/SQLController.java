package company.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SQLController {

    public static boolean debug = false;

    /**
     * Execute SQL, can be used for INSERT, UPDATE, or DELETE. Will return the
     * number of affected rows
     * 
     * @param sqlQuery
     * @return
     */
    public static int executeUpdate(String sqlQuery) {
        if(SQLController.debug){
            System.out.println("executeUpdate : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        int result = 0;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            result = statement.executeUpdate(sqlQuery);
        } catch (SQLException se) {
            debugError(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static DataSource getDataSource() {
        MysqlDataSource source = new MysqlDataSource();
        source.setServerName("localhost");
        source.setPort(3306);
        source.setDatabaseName("java_test");
        source.setUser("java");
        source.setPassword("java-sucks");
        return source;
    }

    public static String sqlPrepare(String input) {
        return "'" + input + "'";
    }

    public static String sqlPrepare(BigDecimal input) {
        BigDecimal rounded = input.setScale(2, RoundingMode.HALF_EVEN);
        return sqlPrepare(rounded.toString());
    }

    public static String sqlPrepare(UUID input) {
        return sqlPrepare(input.toString());
    }

    public static void debugError(SQLException e) {
        System.err.println("\n SQL Error: {State: " + e.getSQLState() + " ErrorCode: " + e.getErrorCode()
                + " Messages: " + e.getMessage() + " } \n");
    }
}
