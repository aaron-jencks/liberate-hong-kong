package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.CustomerController;
import company.Controller.PersonController;
import company.Entity.Customer;
import company.Entity.Person;


/**
 * Unit test for simple App.
 */
public class CustomerTest
{

    private static CustomerController cc;
    private static Person p;

    @AfterClass
    public static void truncate(){
        CustomerController.getInstance().truncateTable();
    }

    @Before
    public void setup(){
        cc = CustomerController.getInstance();
        p = PersonController.getInstance().createPerson("first", "something");
    }

    @Test
    public void getAllTest(){
        truncate();
        for(int i = 0; i < 5; i++){
            cc.createCustomer("f" + i, "l" + i);
        }
        ArrayList<Customer> allCustomers = cc.getAll();
        for (Customer customer : allCustomers) {
            System.out.println("Customer : " + customer.getFirstName());
        }
        boolean sameSize = allCustomers.size() == 5;
        assertTrue(sameSize);
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
        Customer c = cc.getCustomer(UUID.randomUUID());
        assertNull(c);
    }

    /**
     * Can I delete?
     */
    @Test
    public void testDelete(){
        Customer c = cc.createCustomer("joey", "james");
        cc.deleteCustomer(c);
        c = cc.getCustomer(c.getId());
        assertNull(c);
    }

    /**
     * Can I change something?
     */
    @Test
    public void testUpdate(){
        Customer c = cc.createCustomer("joey", "james");
        c.setFirstName("new first name");
        Customer cDB = cc.getCustomer(c.getId());
        boolean same = c.getFirstName().equals(cDB.getFirstName());
        assertTrue(same);
    }

    @Test
    public void testCreateFromPerson(){
        Customer c = cc.createCustomer(p);
        assertSame(c.getId(), p.getId());
        assertSame(c.getFirstName(), p.getFirstName());
    }

    @Test
    public void testUpdateName(){
        Customer c = cc.createCustomer(p);
        c.setFirstName("nothing");
        p = PersonController.getInstance().getPerson(c.getId());
        boolean same = c.getFirstName().equals(p.getFirstName());
        assertTrue(same);
    }

}
