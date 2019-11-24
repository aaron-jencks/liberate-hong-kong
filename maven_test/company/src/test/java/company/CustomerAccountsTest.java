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
import company.Controller.CustomerController;
import company.Controller.PersonController;
import company.Entity.SQLAccount;
import company.Entity.SQLCustomer;
import company.Entity.Enum.AccountType;
import jdk.jfr.Timestamp;


/**
 * Unit test for simple App.
 */
public class CustomerAccountsTest 
{

    private static CustomerController cc;
    private static AccountController ac;
    private static SQLCustomer customer;

    @AfterClass
    public static void truncate(){
        ac.truncateTable();
        cc.truncateTable();
        
    }

    @Before
    public void setup(){
        ac = AccountController.getInstance();
        cc = CustomerController.getInstance();
        customer = cc.createCustomer("first", "last");
    }

    /**
     * Can I create an account from a customer
     */
    @Test
    public void testCreateAccount(){
        cc.addAccount(customer, AccountType.CHECKING);
    }

}