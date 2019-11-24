package company.Controller.Abstract;

import company.Controller.Interface.ICustomerController;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Controller.AccountController;
import company.Controller.PersonController;
import company.Controller.Abstract.ACustomerController;
import company.Controller.Abstract.ASQLController;
import company.Entity.Account;
import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Enum.AccountType;

public abstract class ACustomerController extends ASQLController implements ICustomerController{
    
    /**
     * Find the person by the id
     */
    public Customer getCustomer(UUID id){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME +
                    " WHERE ID = " + sqlPrepare(id);
        Customer c = null;
        if(ASQLController.debug){
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
     * Create a new account for the customer of type
     * @param customer
     * @param type
     * @return
     */
    public Account addAccount(Customer customer, AccountType type){
        Account a = AccountController.getInstance().createAccount(BigDecimal.ZERO, type);
        customer.addAccount(a);
        return a;
    }

    /**
     * Create a new customer from their name
     * @param first
     * @param last
     * @return
     */
    public Customer createCustomer(String first, String last){
        Person p = PersonController.getInstance().createPerson(first, last);
        return createCustomer(p);
    }

    /**
     * Create a customer with the supplied names
     * returns the newly created customer
     */
    public Customer createCustomer(Person p){
        UUID id = p.getId();
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + 
                    CUST_ACCOUNTS_CONST + 
                    " ) VALUES ( " + 
                    sqlPrepare(id) + " , " +
                    " NULL " + 
                    " )";
        executeUpdate(sql);
        return new Customer(id, p.getFirstName(), p.getLastName());
    }

    /**
     * Update all customer attributes
     */
    public void updateCustomer(Customer c){
        String[] params = {
            CUST_ACCOUNTS_CONST + " = " + sqlPrepare(c.getAccountsString()),
        };
        update(TABLE_NAME, c.getId(), params);
        PersonController.getInstance().updatePerson(c);
    }

    /**
     * Create a customer from a result set
     */
    private Customer createCustomer(ResultSet customerResult){
        UUID id = null;
        ArrayList<UUID> accounts= new ArrayList<>();
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
        Person person = PersonController.getInstance().getPerson(id);
        return new Customer(id, accounts, person);
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<Customer> getAll(){
        ArrayList<Customer> allPerson = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        if(ASQLController.debug){
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
    protected static void createTable(){
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
    public void deleteCustomer(Customer customer){
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