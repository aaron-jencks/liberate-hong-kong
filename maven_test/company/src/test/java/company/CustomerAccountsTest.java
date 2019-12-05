package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.AccountController;
import company.Controller.CustomerController;
import company.Entity.BankLock;
import company.Entity.Customer;
import company.Entity.Enum.AccountType;
import company.exceptions.BankLockedException;


/**
 * Unit test for simple App.
 */
public class CustomerAccountsTest 
{

    private static CustomerController cc;
    private static AccountController ac;
    private static Customer customer;

    @AfterClass
    public static void truncate(){
        ac.truncateTable();
        cc.truncateTable();
        
    }

    @Before
    public void setup(){
        BankLock.getInstance().unlockBank();
        ac = AccountController.getInstance();
        cc = CustomerController.getInstance();
        customer = cc.createCustomer("first", "last");
    }

    /**
     * Can I create an account from a customer
     */
    @Test
    public void testCreateAccount(){
        try{
        cc.addAccount(customer, AccountType.CHECKING);
        }
        catch(BankLockedException e)
        {
            // unreachable
        }
    }

}