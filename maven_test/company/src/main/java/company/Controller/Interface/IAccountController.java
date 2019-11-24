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

    public Account createAccount(BigDecimal amount, AccountType type);
    
    public Account getAccount(String id);
    public Account getAccount(UUID id);

    public ArrayList<Account> getAll();

    public BigDecimal deleteAccount(Account account);
    public BigDecimal deleteAccount(UUID id);

    public Account deposit(Account account, BigDecimal amount) throws InvalidParameterException;
    public Account withdrawl(Account account, BigDecimal amount) throws InvalidParameterException;

    public void updateAccount(Account account);

    


    /**
     * Accrues interest on all credit accounts in the vault
     */
    // public void accrueInterest();
}
