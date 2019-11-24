package company.Controller.Interface;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Account;
import company.Entity.Enum.AccountType;

public interface IAccountController{

    static String TABLE_NAME = "ACCOUNT";

    static String ACCT_AMT_CONST = "amount";
    static String ACCT_TYPE = "type";

    /**
     * Create a new account
     * @param amount
     * @param type
     * @return
     */
    public Account createAccount(BigDecimal amount, AccountType type);
    
    /**
     * Get account
     * @param id
     * @return
     */
    public Account getAccount(String id);

    /**
     * Get account
     * @param id
     * @return
     */
    public Account getAccount(UUID id);

    /**
     * Get all accounts
     * @return
     */
    public ArrayList<Account> getAll();

    /**
     * Delete account
     * @param account
     * @return the amount left on the account
     */
    public BigDecimal deleteAccount(Account account);

    /**
     * Delete account
     * @param id
     * @return the amount left on the account
     */
    public BigDecimal deleteAccount(UUID id);

    /**
     * Deposit amount into account
     * Will not allow negative
     * @param account
     * @param amount
     * @return
     * @throws InvalidParameterException
     */
    public Account deposit(Account account, BigDecimal amount) throws InvalidParameterException;

    /**
     * Withdraw amount from account
     * Will not allow negative
     * @param account
     * @param amount
     * @return
     * @throws InvalidParameterException
     */
    public Account withdrawl(Account account, BigDecimal amount) throws InvalidParameterException;

    /**
     * Accrue interest on all accounts
     */
    public void accrueInterest();
}
