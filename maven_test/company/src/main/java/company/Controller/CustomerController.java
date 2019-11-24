package company.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLCustomer;
import company.Entity.SQLPerson;

public class CustomerController extends SQLController {

    private static CustomerController controllerInstance = null;
    private static String TABLE_NAME = "CUSTOMER";

    protected static String CUST_FIRSTNAME_CONST = "first";
    protected static String CUST_LASTNAME_CONST = "last";
    protected static String CUST_ACCOUNTS_CONST = "accounts";

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
     * Create a new customer from their name
     * @param first
     * @param last
     * @return
     */
    public SQLCustomer createCustomer(String first, String last){
        SQLPerson p = PersonController.getInstance().createPerson(first, last);
        return createCustomer(p);
    }

    /**
     * Create a customer with the supplied names
     * returns the newly created customer
     */
    public SQLCustomer createCustomer(SQLPerson p){
        UUID id = p.getId();
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + 
                    CUST_ACCOUNTS_CONST + 
                    " ) VALUES ( " + 
                    sqlPrepare(id) + " , " +
                    " NULL " + 
                    " )";
        executeUpdate(sql);
        return new SQLCustomer(id, p.getFirstName(), p.getLastName());
    }

    /**
     * Update all customer attributes
     */
    public void updateCustomer(SQLCustomer c){
        String[] params = {
            CUST_ACCOUNTS_CONST + " = " + sqlPrepare(c.getAccountsString()),
        };
        update(TABLE_NAME, c.getId(), params);
        PersonController.getInstance().updatePerson(c);
    }

    /**
     * Create a customer from a result set
     */
    private SQLCustomer createCustomer(ResultSet customerResult){
        String first = null, last = null;
        UUID id = null;
        ArrayList<UUID> accounts= new ArrayList<>();
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
            String accountString = customerResult.getString(CUST_ACCOUNTS_CONST);
            if(accountString != null && !accountString.isBlank()){
                String[] s = accountString.split(",");
            for (String string : s) {
                accounts.add(UUID.fromString(string));
            }
            }
        } catch (SQLException e) {
            debugError(e);
        }
        SQLPerson person = PersonController.getInstance().getPerson(id);
        return new SQLCustomer(id, accounts, person);
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
