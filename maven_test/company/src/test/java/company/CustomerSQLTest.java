package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import company.Controller.CustomerController;
import company.Controller.PersonController;
import company.Entity.SQLCustomer;
import company.Entity.SQLPerson;


/**
 * Unit test for simple App.
 */
public class CustomerSQLTest 
{

    private static CustomerController cc;
    private static SQLPerson p;

    @AfterClass
    public static void truncate(){
        CustomerController.getInstance().truncateTable();
    }

    @Before
    public void setup(){
        cc = CustomerController.getInstance();
        p = PersonController.getInstance().createPerson("first", "something");
    }

    /**
     * Can I create a customer?
     */
    @Test
    public void testCreateCustomer(){
        cc.createCustomer("joey", "james");
    }

    /**
     * What if I try to get a customer that doesn't exist
     */
    @Test
    public void testGetNonExist(){
        SQLCustomer c = cc.getCustomer(UUID.randomUUID());
        assertNull(c);
    }

    /**
     * Can I delete?
     */
    @Test
    public void testDelete(){
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
        SQLCustomer c = cc.createCustomer("joey", "james");
        c.setFirstName("new first name");
        SQLCustomer cDB = cc.getCustomer(c.getId());
        boolean same = c.getFirstName().equals(cDB.getFirstName());
        assertTrue(same);
    }

    @Test
    public void testCreateFromPerson(){
        SQLCustomer c = cc.createCustomer(p);
        assertSame(c.getId(), p.getId());
        assertSame(c.getFirstName(), p.getFirstName());
    }

    @Test
    public void testUpdateName(){
        SQLCustomer c = cc.createCustomer(p);
        c.setFirstName("nothing");
        p = PersonController.getInstance().getPerson(c.getId());
        boolean same = c.getFirstName().equals(p.getFirstName());
        assertTrue(same);
    }

}
