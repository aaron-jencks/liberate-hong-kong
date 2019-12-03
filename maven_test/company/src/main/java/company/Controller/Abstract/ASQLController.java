package company.Controller.Abstract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public abstract class ASQLController{
    
    public static boolean debug = true;

    /**
     * Create a table with the params
     * @param table
     * @param params
     */
    protected static void create(String table, String[] params){
        String sql = "CREATE TABLE IF NOT EXISTS " + table + " (ID VARCHAR(255) not NULL, ";
        sql += implode(params) + " , ";
        sql += " PRIMARY KEY ( ID ))";
        executeUpdate(sql);
    }

    /**
     * UPDATE {table} SET {equals} WHERE ID = {id.toString()}
     * @param table
     * @param id
     * @param params
     */
    protected static void update(String table, UUID id, String[] params){
        update(table, id.toString(), params);
    }

    /**
     * UPDATE {table} SET {equals} WHERE ID = {id}
     * @param table
     * @param id
     * @param params
     */
    protected static void update(String table, String id, String[] params){
        String sql = "UPDATE " + table + " SET ";
        sql += implode(params);
        sql += " WHERE ID = " + sqlPrepare(id);
        executeUpdate(sql);
    }

    /**
     * Implode the array with default delim " , "
     * @param list
     * @return
     */
    protected static String implode(String[] list){
        return implode(list, " , ");
    }

    /**
     * Implode the array with the given delim
     * @param list
     * @param delim
     * @return
     */
    private static String implode(String[] list, String delim){
        String s = "";
        for (int i = 0; i < list.length; i++) {
            s += list[i];
            if(i != list.length - 1){
                s += " , ";
            }
        }
        return s;
    }


    /**
     * DELETE FROM {table} WHERE ID = {id.toString()}
     * @param table
     * @param id
     */
    protected static void delete(String table, UUID id){
        delete(table, id.toString());
    }

    /**
     * DELETE FROM {table} WHERE ID = {id}
     * @param table
     * @param id
     */
    protected static void delete(String table, String id){
        executeUpdate("DELETE FROM " + table + " WHERE ID = " + sqlPrepare(id));
    }

    /**
     * TRUNCATE TABLE {table}
     * @param table
     */
    protected static void truncate(String table){
        executeUpdate("TRUNCATE TABLE  " + table);
    }

    /**
     * DROP TABLE {table}
     * @param table
     */
    protected static void drop(String table){
        executeUpdate("DROP TABLE " + table);
    }

    /**
     * Execute SQL, can be used for INSERT, UPDATE, or DELETE. Will return the
     * number of affected rows
     * 
     * @param sqlQuery
     * @return
     */
    public static int executeUpdate(String sqlQuery) {
        if(ASQLController.debug){
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

    /**
     * Connect to the data source
     * //TODO extract these consts to a non-git file
     * @return
     */
    public static DataSource getDataSource() {
        MysqlDataSource source = new MysqlDataSource();
        source.setServerName("localhost");
        source.setPort(3306);
        source.setDatabaseName("java_test");
        source.setUser("java");
        source.setPassword("java-sucks");
        return source;
    }

    /**
     * Sanitize input
     * @param input
     * @return
     */
    public static String sqlPrepare(String input) {
        if(input == null){
            return "NULL";
        }
        return "'" + input + "'";
    }

    /**
     * Sanitize input
     * @param input
     * @return
     */
    public static String sqlPrepare(BigDecimal input) {
        BigDecimal rounded = input.setScale(2, RoundingMode.HALF_EVEN);
        return sqlPrepare(rounded.toString());
    }

    /**
     * Sanitize input
     * @param input
     * @return
     */
    public static String sqlPrepare(UUID input) {
        return sqlPrepare(input.toString());
    }

    /**
     * Advanced print out from SQL exception
     */
    public static void debugError(SQLException e) {
        System.err.println("\n SQL Error: {State: " + e.getSQLState() + " ErrorCode: " + e.getErrorCode()
                + " Messages: " + e.getMessage() + " } \n");
                e.printStackTrace();
    }
}