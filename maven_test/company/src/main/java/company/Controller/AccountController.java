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
    private static String TABLE_NAME = "ACCOUNT";

    public static String ACCT_AMT_CONST = "amount";
    

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
        String sqlQuery = "SELECT * FROM " + TABLE_NAME +
                    " WHERE ID = " + sqlPrepare(id);
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
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + 
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
            id = UUID.fromString(personResult.getString("ID"));
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
    public void updateAccount(SQLAccount account){
        String[] params = {
            ACCT_AMT_CONST + " = " + sqlPrepare(account.getAmount())
        };
        update(TABLE_NAME, account.getId(), params);
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLAccount> getAll(){
        ArrayList<SQLAccount> allAccount = new ArrayList<>();
        String sqlQuery = "SELECT * " + TABLE_NAME;
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
        String[] params = {
            ACCT_AMT_CONST + " DECIMAL(13,4) ",
        };
        create(TABLE_NAME, params);
    }

    /**
     * Delete an account
     * @param id
     */
    public void deleteAccount(UUID id){
        delete(TABLE_NAME, id);
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
        truncate(TABLE_NAME);
    }

    /**
     * Drop the table
     */
    public void dropTable(){
        drop(TABLE_NAME);
    }
}
