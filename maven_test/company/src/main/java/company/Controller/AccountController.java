package company.Controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLAccount;

public class AccountController extends SQLController{

    private static AccountController controllerInstance = null;

    public static String ACCT_ID_CONST = "ID";
    public static String ACCT_AMT_CONST = "amount";
    public static String ACCT_TABLE_NAME = "ACCOUNT";

    /**
     * Make this controller a singleton
     * @return
     */
    public static AccountController getInstance(){
        if (controllerInstance == null) {
            createTable();
            controllerInstance = new AccountController();
        }
        return controllerInstance;
    }

    /**
     * Find the person by the id
     */
    public SQLAccount getAccount(UUID id){
        String sqlQuery = "SELECT * FROM " + ACCT_TABLE_NAME +
                    " WHERE " + ACCT_ID_CONST + " = " + sqlPrepare(id);
        SQLAccount a = null;
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
                a = createAccount(resultSet);
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
        return a;
    }

    /**
     * Create a person with the supplied names
     * returns the newly created person
     */
    public SQLAccount createAccount(BigDecimal amount){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + ACCT_TABLE_NAME +
                    " ( " + 
                    ACCT_ID_CONST + ", " + 
                    ACCT_AMT_CONST +
                     " ) VALUES ( " + 
                    sqlPrepare(id) + " , " +
                    sqlPrepare(amount) + 
                    " )";
        executeUpdate(sql);
        return getAccount(id);
    }

    /**
     * Internal function to instantiate an account object from a ResultSet
     */
    private SQLAccount createAccount(ResultSet personResult) {
        BigDecimal amt = null;
        UUID id = null;
        try {
            amt = personResult.getBigDecimal(ACCT_AMT_CONST);
            amt = amt.setScale(2, RoundingMode.HALF_EVEN);
        } catch (SQLException e) {
            System.err.println("Failed to retrieve account.amount (AccountController.createAccount)");
            debugError(e);
        }
        try {
            id = UUID.fromString(personResult.getString(ACCT_ID_CONST));
        } catch (SQLException e) {
            System.err.println("Failed to retrieve account.id (AccountController.createAccount)");
            debugError(e);
        }
        return new SQLAccount(id, amt);
    }

    /**
     * Deposit amount into account
     * @param account
     * @param amount
     * @return SQLAccount
     */
    public SQLAccount deposit(SQLAccount account, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidParameterException("Deposit amount can not be <= 0");
        }
        account.setAmount(amount.add(account.getAmount()));
        return getAccount(account.getId());
    }

    /**
     * Withdraw amount from account
     * @param account
     * @param amount
     * @return SQLAccount
     */
    public SQLAccount withdrawl(SQLAccount account, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidParameterException("Withdrawl amount can not be <= 0");
        }
        account.setAmount(account.getAmount().subtract(amount));
        return getAccount(account.getId());
    }

    /**
     * Make an update to an account
     */
    public SQLAccount updateAccount(SQLAccount account){
        String sql = "UPDATE " + ACCT_TABLE_NAME + 
            " SET " + 
            ACCT_AMT_CONST + " = " + sqlPrepare(account.getAmount()) + 
            " WHERE " + 
            ACCT_ID_CONST + " = " + sqlPrepare(account.getId());
        executeUpdate(sql);
        return getAccount(account.getId());
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLAccount> getAll(){
        ArrayList<SQLAccount> allAccount = new ArrayList<>();
        String sqlQuery = "SELECT * " + ACCT_TABLE_NAME;
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
                allAccount.add(createAccount(resultSet));
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
        return allAccount;
    }

    /**
     * Create the table in sql
     * Will only create if it doesn't exist
     */
    private static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + ACCT_TABLE_NAME + 
                    "(" + ACCT_ID_CONST + " VARCHAR(255) not NULL, " + 
                    ACCT_AMT_CONST + " DECIMAL(13,4), " + 
                    " PRIMARY KEY ( " + ACCT_ID_CONST + " ))";
        executeUpdate(sql);
    }

    /**
     * Delete an account
     * @param id
     */
    public void deleteAccount(UUID id){
        String sql = "DELETE FROM " + ACCT_TABLE_NAME + 
                    " WHERE " + ACCT_ID_CONST + 
                    " = " + sqlPrepare(id);
        executeUpdate(sql);
    }

    /**
     * Delete an account
     * @param account
     */
    public void deleteAccount(SQLAccount account){
        if(account == null){
            return;
        }
        deleteAccount(account.getId());
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        String sql = "TRUNCATE TABLE " + ACCT_TABLE_NAME;
        executeUpdate(sql);
    }

    /**
     * Drop the table
     */
    public void dropTable(){
        String sql = "DROP TABLE " + ACCT_TABLE_NAME;
        executeUpdate(sql);
    }
}
