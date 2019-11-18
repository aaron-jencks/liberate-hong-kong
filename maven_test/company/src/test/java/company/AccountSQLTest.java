package company;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import company.Controller.AccountController;
import company.Controller.PersonController;


/**
 * Unit test for simple App.
 */
public class AccountSQLTest 
{
    /**
     * Rigorous Test :-)
     * Sanity check
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testTableCreate(){
        AccountController ac = AccountController.getInstance();
    }

    @Test
    public void testCreateAccount(){
        AccountController ac = AccountController.getInstance();
        ac.createAccount(new BigDecimal(123456));
    }

}