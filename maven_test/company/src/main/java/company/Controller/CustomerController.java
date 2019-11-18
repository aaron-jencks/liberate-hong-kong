package company.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLCustomer;
import company.Entity.SQLPerson;

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
        String sql = "SELECT * FROM " + CUST_TABLE_NAME +
                    " WHERE " + CUST_ID_CONST + " = " + sqlPrepare(id.toString());
        return createCustomer(execute(sql));
    }

    /**
     * Create a person with the supplied names
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
                    sqlPrepare(id.toString()) + " , " +
                    sqlPrepare(firstName) + " , " +
                    sqlPrepare(lastName) + " , " +
                    " NULL " + 
                    " )";
        executeUpdate(sql);
        return getCustomer(id);
    }

    private SQLCustomer createCustomer(ResultSet customerResult){
        try {
            SQLCustomer p = new SQLCustomer();
            p.setFirstName(customerResult.getString(CUST_FIRSTNAME_CONST));
            p.setLastName(customerResult.getString(CUST_LASTNAME_CONST));
            p.setId(UUID.fromString(customerResult.getString(CUST_ID_CONST)));
            return p;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLCustomer> getAll(){
        ArrayList<SQLCustomer> allPerson = new ArrayList<>();
        String sql = "SELECT * " + CUST_TABLE_NAME;
        ResultSet list = execute(sql);
        try {
            while (list.next()){
                SQLCustomer p = createCustomer(list);
                allPerson.add(p);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
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
     * Drop the table
     */
    public void dropTable(){
        String sql = "DROP TABLE " + CUST_TABLE_NAME;
        executeUpdate(sql);
    }
}
