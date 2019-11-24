package company.Controller.Abstract;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Controller.AccountController;
import company.Controller.Interface.IAccountController;
import company.Entity.Account;
import company.Entity.Enum.AccountType;

public abstract class AAccountController extends ASQLController implements IAccountController{
    
    public Account getAccount(String id){
        return getAccount(UUID.fromString(id));
    }

    @Override
    public void accrueInterest() {
        ArrayList<Account> list = getAll();
        for (Account account : list) {
            //TODO fix
            // account.accrueInterest();
        }
    }

    /**
     * Find the person by the id
     */
    public Account getAccount(UUID id){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME +
                    " WHERE ID = " + sqlPrepare(id);
        Account a = null;
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
    public Account createAccount(BigDecimal amount, AccountType type){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + 
                    implode(new String[]{
                        ACCT_AMT_CONST,
                        ACCT_TYPE,
                    }) + 
                     " ) VALUES ( " + 
                     implode(new String[]{
                        sqlPrepare(id),
                        sqlPrepare(amount),
                        sqlPrepare(type.toString())
                     }) + " )";
        executeUpdate(sql);
        return getAccount(id);
    }

    /**
     * Internal function to instantiate an account object from a ResultSet
     */
    private Account createAccount(ResultSet personResult) {
        BigDecimal amt = null;
        UUID id = null;
        AccountType type = null;
        try {
            type = AccountType.valueOf(personResult.getString(ACCT_TYPE).toUpperCase());
        } catch (SQLException e) {
            debugError(e);
        }
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
        return new Account(id, amt, type);
    }
    

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<Account> getAll(){
        ArrayList<Account> allAccount = new ArrayList<>();
        String sqlQuery = "SELECT * " + TABLE_NAME;
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
    protected static void createTable(){
        String[] params = {
            ACCT_AMT_CONST + " DECIMAL(13,4) ",
            ACCT_TYPE + " VARCHAR(255) ",
        };
        create(TABLE_NAME, params);
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

    /**
     * Delete an account
     * @param account
     */
    public BigDecimal deleteAccount(Account account){
        if(account == null){
            return BigDecimal.ZERO;
        }
        return deleteAccount(account.getId());
    }
    
    /**
     * Delete an account
     * @param id
     */
    public BigDecimal deleteAccount(UUID id){
        BigDecimal d = AccountController.getInstance().getAccount(id).getAmount();
        delete(TABLE_NAME, id);
        return d;
    }

    /**
     * Deposit amount into account
     * @param account
     * @param amount
     * @return Account
     */
    public Account deposit(Account account, BigDecimal amount) throws InvalidParameterException{
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
     * @return Account
     */
    public Account withdrawl(Account account, BigDecimal amount) throws InvalidParameterException{
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidParameterException("Withdrawl amount can not be <= 0");
        }
        account.setAmount(account.getAmount().subtract(amount));
        return getAccount(account.getId());
    }

    /**
     * Make an update to an account
     */
    public void updateAccount(Account account){
        String[] params = {
            ACCT_AMT_CONST + " = " + sqlPrepare(account.getAmount())
        };
        update(TABLE_NAME, account.getId(), params);
    }
}