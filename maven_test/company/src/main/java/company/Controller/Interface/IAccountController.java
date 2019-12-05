package company.Controller.Interface;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Account;
import company.Entity.Enum.AccountType;
import company.exceptions.BankLockedException;

public interface IAccountController{

    static String TABLE_NAME = "ACCOUNT";

    static String ACCT_AMT_CONST = "amount";
    static String ACCT_TYPE = "type";
    static String ACCT_INTEREST = "interest";

    static BigDecimal DEFAULT_INTEREST = new BigDecimal(0.05);

    /**
     * Create a new account
     * @param amount
     * @param type
     * @return
     */
    public Account createAccount(BigDecimal amount, AccountType type) throws BankLockedException;
    
    /**
     * Get account
     * @param id
     * @return
     */
    public Account getAccount(String id) throws BankLockedException;

    /**
     * Get account
     * @param id
     * @return
     */
    public Account getAccount(UUID id) throws BankLockedException;

    /**
     * Get all accounts
     * @return
     */
    public ArrayList<Account> getAll() throws BankLockedException;

    /**
     * Delete account
     * @param account
     * @return the amount left on the account
     */
    public BigDecimal deleteAccount(Account account) throws BankLockedException;

    /**
     * Delete account
     * @param id
     * @return the amount left on the account
     */
    public BigDecimal deleteAccount(UUID id) throws BankLockedException;

    /**
     * Deposit amount into account
     * Will not allow negative
     * @param account
     * @param amount
     * @return
     * @throws InvalidParameterException
     */
    public Account deposit(Account account, BigDecimal amount) throws InvalidParameterException, BankLockedException;

    /**
     * Withdraw amount from account
     * Will not allow negative
     * @param account
     * @param amount
     * @return
     * @throws InvalidParameterException
     */
    public Account withdrawl(Account account, BigDecimal amount) throws InvalidParameterException, BankLockedException;

    /**
     * Accrue interest on all accounts
     */
    public void accrueInterest() throws BankLockedException;
}
