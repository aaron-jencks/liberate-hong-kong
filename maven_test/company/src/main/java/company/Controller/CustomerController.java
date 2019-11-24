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
    private static String TABLE_NAME = "CUSTOMER";

    public static String CUST_FIRSTNAME_CONST = "first";
    public static String CUST_LASTNAME_CONST = "last";
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
        String sqlQuery = "SELECT * FROM " + TABLE_NAME +
                    " WHERE ID = " + sqlPrepare(id);
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
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + 
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
        String[] params = {
            CUST_FIRSTNAME_CONST + " = " + sqlPrepare(c.getFirstName()),
            CUST_LASTNAME_CONST + " = " + sqlPrepare(c.getLastName()),
            CUST_ACCOUNTS_CONST + " = " + sqlPrepare(c.getAccountsString()),
        };
        update(TABLE_NAME, c.getId(), params);
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
            id = UUID.fromString(customerResult.getString("ID"));
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
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
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
        String[] params = {
            CUST_FIRSTNAME_CONST + " VARCHAR(255)",
            CUST_LASTNAME_CONST + " VARCHAR(255)",
            CUST_ACCOUNTS_CONST + " VARCHAR(255)",
        };
        create(TABLE_NAME, params);
    }

    /**
     * Delete a customer
     * @param customer
     */
    public void deleteCustomer(SQLCustomer customer){
        if(customer == null){
            return;
        }
        delete(TABLE_NAME, customer.getId().toString());
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        truncate(TABLE_NAME);
    }

    /**
     * Drop the table
     */
    public static void dropTable(){
        drop(TABLE_NAME);
    }
}
