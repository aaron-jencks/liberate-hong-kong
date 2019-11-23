package company;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

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
    // @Test
    public void testAccountDeposit(){
        AccountController ac = AccountController.getInstance();
        BigDecimal x = new BigDecimal(123.456);
        BigDecimal y = new BigDecimal(1566);
        SQLAccount b = ac.createAccount(x);
        b = ac.deposit(b, y);
        BigDecimal sum = x.add(y);
        boolean same = sum.compareTo(b.getAmount()) == 0;
        assertTrue(same);
    }

    /**
     * Can I withdraw?
     */
    // @Test
    public void testAccountWithdrawl(){
        AccountController ac = AccountController.getInstance();
        BigDecimal x = new BigDecimal(123.456);
        BigDecimal y = new BigDecimal(1566);
        SQLAccount a = ac.createAccount(y);
        a = ac.withdrawl(a, x);
        BigDecimal sum = y.subtract(x);
        boolean same = sum.compareTo(a.getAmount()) == 0;
        assertTrue(same);
    }

}