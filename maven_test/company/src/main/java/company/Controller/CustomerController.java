package company.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLCustomer;

public class CustomerController extends SQLController {

    private static CustomerController controllerInstance = null;

    public static String CUST_ID_CONST = "ID";
    public static String CUST_FIRSTNAME_CONST = "first";
    public static String CUST_LASTNAME_CONST = "last";
    public static String CUST_TABLE_NAME = "CUSTOMER";
    public static String CUST_ACCOUNTS_CONST = "accounts";

    /**
     * Make this controller a singleton
     * @return
     */
    public static CustomerController getInstance(){
        if (controllerInstance == null) {
            createTable();
            controllerInstance = new CustomerController();
        }
        return controllerInstance;
    }

    /**
     * Find the person by the id
     */
    public SQLCustomer getCustomer(UUID id){
        String sqlQuery = "SELECT * FROM " + CUST_TABLE_NAME +
                    " WHERE " + CUST_ID_CONST + " = " + sqlPrepare(id);
        SQLCustomer c = null;
        if(SQLController.debug){
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                c = createCustomer(resultSet);
            }
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
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return c;
    }

    /**
     * Create a customer with the supplied names
     * returns the newly created person
     */
    public SQLCustomer createCustomer(String firstName, String lastName){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + CUST_TABLE_NAME +
                    " ( " + 
                    CUST_ID_CONST + ", " + 
                    CUST_FIRSTNAME_CONST + ", " + 
                    CUST_LASTNAME_CONST + " , " + 
                    CUST_ACCOUNTS_CONST + 
                    " ) VALUES ( " + 
                    sqlPrepare(id) + " , " +
                    sqlPrepare(firstName) + " , " +
                    sqlPrepare(lastName) + " , " +
                    " NULL " + 
                    " )";
        executeUpdate(sql);
        return getCustomer(id);
    }

    /**
     * Update all customer attributes
     */
    public void updateCustomer(SQLCustomer c){
        String sql = "UPDATE " + CUST_TABLE_NAME + 
                    " SET " + 
                    CUST_FIRSTNAME_CONST + " = " + sqlPrepare(c.getFirstName()) +  " , " + 
                    CUST_LASTNAME_CONST + " = " + sqlPrepare(c.getLastName()) +  " , " + 
                    CUST_ACCOUNTS_CONST + " = " + sqlPrepare(c.getAccountsString()) + 
                    " WHERE " +
                    CUST_ID_CONST + " = " + sqlPrepare(c.getId());
        executeUpdate(sql);
    }

    /**
     * Create a customer from a result set
     */
    private SQLCustomer createCustomer(ResultSet customerResult){
        SQLCustomer c = new SQLCustomer();
        String first = null;
        String last = null;
        UUID id = null;
        ArrayList<UUID> ids= new ArrayList<>();
        String accounts = null;
        try {
            first = customerResult.getString(CUST_FIRSTNAME_CONST);
        } catch (SQLException e) {
            debugError(e);
        }
        try {
            last = customerResult.getString(CUST_LASTNAME_CONST);
        } catch (SQLException e) {
            debugError(e);
        }
        try {
            id = UUID.fromString(customerResult.getString(CUST_ID_CONST));
        } catch (SQLException e) {
            debugError(e);
        }
        try {
            accounts = customerResult.getString(CUST_ACCOUNTS_CONST);
            if(accounts != null && !accounts.isBlank()){
                String[] s = accounts.split(",");
            for (String string : s) {
                ids.add(UUID.fromString(string));
            }
            c.setAccounts(ids);
            }
        } catch (SQLException e) {
            debugError(e);
        }
        return new SQLCustomer(id, first, last, ids);
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLCustomer> getAll(){
        ArrayList<SQLCustomer> allPerson = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + CUST_TABLE_NAME;
        if(SQLController.debug){
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                allPerson.add(createCustomer(resultSet));
            }
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
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allPerson;
    }

    /**
     * Create the table in sql
     * Will only create if it doesn't exist
     */
    private static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + CUST_TABLE_NAME + 
                    "(" + CUST_ID_CONST + " VARCHAR(255) not NULL, " + 
                    CUST_FIRSTNAME_CONST + " VARCHAR(255), " + 
                    CUST_LASTNAME_CONST + " VARCHAR(255), " + 
                    CUST_ACCOUNTS_CONST + " VARCHAR(255), " + 
                    " PRIMARY KEY ( " + CUST_ID_CONST + " ))";
        executeUpdate(sql);
    }

    /**
     * Delete a customer
     * @param id
     */
    public void deleteCustomer(UUID id){
        String sql = "DELETE FROM " + CUST_TABLE_NAME + 
                    " WHERE " + CUST_ID_CONST +
                    " = " + sqlPrepare(id);
        executeUpdate(sql);
    }

    /**
     * Delete a customer
     * @param customer
     */
    public void deleteCustomer(SQLCustomer customer){
        if(customer == null){
            return;
        }
        deleteCustomer(customer.getId());
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        String sql = "TRUNCATE TABLE " + CUST_TABLE_NAME;
        executeUpdate(sql);
    }

    /**
     * Drop the table
     */
    public static void dropTable(){
        String sql = "DROP TABLE " + CUST_TABLE_NAME;
        executeUpdate(sql);
    }
}
