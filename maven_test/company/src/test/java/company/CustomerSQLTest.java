package company;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import company.Controller.CustomerController;
import company.Controller.PersonController;


/**
 * Unit test for simple App.
 */
public class CustomerSQLTest 
{

    /**
     * Can I create the table?
     */
    @Test
    public void testTableCreate(){
        CustomerController cc = CustomerController.getInstance();
    }

    /**
     * Can I create a customer?
     */
    @Test
    public void testCreateCustomer(){
        CustomerController cc = CustomerController.getInstance();
        cc.createCustomer("joey", "james");
    }

}
