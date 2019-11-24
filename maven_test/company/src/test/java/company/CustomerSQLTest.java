package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.CustomerController;
import company.Controller.PersonController;
import company.Entity.SQLCustomer;


/**
 * Unit test for simple App.
 */
public class CustomerSQLTest 
{

    @AfterClass
    public static void truncate(){
        CustomerController.getInstance().truncateTable();
    }

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

    /**
     * What if I try to get a customer that doesn't exist
     */
    @Test
    public void testGetNonExist(){
        CustomerController cc = CustomerController.getInstance();
        SQLCustomer c = cc.getCustomer(UUID.randomUUID());
        assertNull(c);
    }

    /**
     * Can I delete?
     */
    @Test
    public void testDelete(){
        CustomerController cc = CustomerController.getInstance();
        SQLCustomer c = cc.createCustomer("joey", "james");
        cc.deleteCustomer(c);
        c = cc.getCustomer(c.getId());
        assertNull(c);
    }

    /**
     * Can I change something?
     */
    @Test
    public void testUpdate(){
        CustomerController cc = CustomerController.getInstance();
        SQLCustomer c = cc.createCustomer("joey", "james");
        c.setFirstName("new first name");
        SQLCustomer cDB = cc.getCustomer(c.getId());
        boolean same = c.getFirstName().equals(cDB.getFirstName());
        assertTrue(same);
    }

}
