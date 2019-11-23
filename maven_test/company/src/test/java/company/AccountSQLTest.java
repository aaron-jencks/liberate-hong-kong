package company;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;

import org.junit.After;
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