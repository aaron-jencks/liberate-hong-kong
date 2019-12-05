package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.AccountController;
import company.Entity.Account;
import company.Entity.BankLock;
import company.Entity.Enum.AccountType;
import company.exceptions.BankLockedException;


/**
 * Unit test for simple App.
 */
public class AccountTest
{

    private static AccountController ac;

    @AfterClass
    public static void truncate(){
        AccountController.getInstance().truncateTable();
    }

    @Before
    public void setup(){
        ac = AccountController.getInstance();
        BankLock.getInstance().unlockBank();
    }

    @Test
    public void getAllTest(){
        try {
            truncate();
            for(int i = 0; i < 5; i++){
                ac.createAccount(new BigDecimal(i), AccountType.SAVINGS);
            }
            ArrayList<Account> allAccounts = ac.getAll();
            for (Account account : allAccounts) {
                System.out.println("Account : " + account.getAmount());
            }
            boolean sameSize = allAccounts.size() == 5;
            assertTrue(sameSize);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I create an account?
     */
    @Test
    public void testCreateAccount(){
        try {
        ac.createAccount(new BigDecimal(123456), AccountType.SAVINGS);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * What if I try to get a non-existing account
     */
    @Test
    public void testGetAccountError(){
        try {
        Account a = ac.getAccount(UUID.randomUUID());
        assertNull(a);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I delete one?
     */
    @Test
    public void testDelete(){
        try{
            Account a = ac.createAccount(new BigDecimal(123456), AccountType.CHECKING);
            ac.deleteAccount(a);
            a = ac.getAccount(a.getId());
            assertNull(a);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I deposit?
     */
    @Test
    public void testAccountDeposit(){
        try{
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal addedAmount = new BigDecimal(1566);
        Account account = ac.createAccount(startAmount, AccountType.CHECKING);
        account = ac.deposit(account, addedAmount);
        BigDecimal sum = startAmount.add(addedAmount);
        //can't forget that it should be rounded
        sum = sum.setScale(2, RoundingMode.HALF_EVEN);
        boolean same = sum.compareTo(account.getAmount()) == 0;
        assertTrue(same);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I withdraw?
     */
    @Test
    public void testAccountWithdrawl(){
        try{
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal subAmount = new BigDecimal(1566);
        Account account = ac.createAccount(startAmount, AccountType.CHECKING);
        account = ac.withdrawl(account, subAmount);
        BigDecimal sum = startAmount.subtract(subAmount);
        sum = sum.setScale(2, RoundingMode.HALF_EVEN);
        boolean same = sum.compareTo(account.getAmount()) == 0;
        assertTrue(same);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I withdraw a negative amount?
     */
    @Test(expected = InvalidParameterException.class)
    public void withdrawErrorHandling(){
        try{
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal subAmount = new BigDecimal(-1566);
        Account account = ac.createAccount(startAmount, AccountType.CHECKING);
        account = ac.withdrawl(account, subAmount);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Can I deposit a negative amount?
     */
    @Test(expected = InvalidParameterException.class)
    public void depositErrorHandling(){
        try{
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal addedAmount = new BigDecimal(-1566);
        Account account = ac.createAccount(startAmount, AccountType.CHECKING);
        account = ac.deposit(account, addedAmount);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

    /**
     * Make sure BigDecimals behave the way I expect
     */
    @Test
    public void bigDecimalTest(){
        BigDecimal x = new BigDecimal(123.456);
        BigDecimal y = new BigDecimal(1566);
        BigDecimal t = x;
        x = x.subtract(y);
        boolean same = x.compareTo(t) == 0;
        assertTrue(!same);
    }

}