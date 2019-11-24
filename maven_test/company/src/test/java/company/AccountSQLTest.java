package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.AccountController;
import company.Controller.PersonController;
import company.Entity.SQLAccount;
import jdk.jfr.Timestamp;


/**
 * Unit test for simple App.
 */
public class AccountSQLTest 
{

    @AfterClass
    public static void truncate(){
        AccountController.getInstance().truncateTable();
    }

    /**
     * Can I create the table?
     */
    @Test
    public void testTableCreate(){
        AccountController ac = AccountController.getInstance();
    }

    /**
     * Can I create an account?
     */
    @Test
    public void testCreateAccount(){
        AccountController ac = AccountController.getInstance();
        ac.createAccount(new BigDecimal(123456));
    }

    /**
     * What if I try to get a non-existing account
     */
    @Test
    public void testGetAccountError(){
        AccountController ac = AccountController.getInstance();
        SQLAccount a = ac.getAccount(UUID.randomUUID());
        assertNull(a);
    }

    /**
     * Can I delete one?
     */
    @Test
    public void testDelete(){
        AccountController ac = AccountController.getInstance();
        SQLAccount a = ac.createAccount(new BigDecimal(123456));
        ac.deleteAccount(a);
        a = ac.getAccount(a.getId());
        assertNull(a);
    }

    /**
     * Can I deposit?
     */
    @Test
    public void testAccountDeposit(){
        AccountController ac = AccountController.getInstance();
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal addedAmount = new BigDecimal(1566);
        SQLAccount account = ac.createAccount(startAmount);
        account = ac.deposit(account, addedAmount);
        BigDecimal sum = startAmount.add(addedAmount);
        //can't forget that it should be rounded
        sum = sum.setScale(2, RoundingMode.HALF_EVEN);
        boolean same = sum.compareTo(account.getAmount()) == 0;
        assertTrue(same);
    }

    /**
     * Can I withdraw?
     */
    @Test
    public void testAccountWithdrawl(){
        AccountController ac = AccountController.getInstance();
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal subAmount = new BigDecimal(1566);
        SQLAccount account = ac.createAccount(startAmount);
        account = ac.withdrawl(account, subAmount);
        BigDecimal sum = startAmount.subtract(subAmount);
        sum = sum.setScale(2, RoundingMode.HALF_EVEN);
        boolean same = sum.compareTo(account.getAmount()) == 0;
        assertTrue(same);
    }

    /**
     * Can I withdraw a negative amount?
     */
    @Test(expected = InvalidParameterException.class)
    public void withdrawErrorHandling(){
        AccountController ac = AccountController.getInstance();
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal subAmount = new BigDecimal(-1566);
        SQLAccount account = ac.createAccount(startAmount);
        account = ac.withdrawl(account, subAmount);
    }

    /**
     * Can I deposit a negative amount?
     */
    @Test(expected = InvalidParameterException.class)
    public void depositErrorHandling(){
        AccountController ac = AccountController.getInstance();
        BigDecimal startAmount = new BigDecimal(123.456);
        BigDecimal addedAmount = new BigDecimal(-1566);
        SQLAccount account = ac.createAccount(startAmount);
        account = ac.deposit(account, addedAmount);
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