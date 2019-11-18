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
        CustomerController pc = CustomerController.getInstance();
    }

    @Test
    public void testCreateCustomer(){
        CustomerController pc = CustomerController.getInstance();
        pc.createCustomer("joey", "james");
    }

}
