package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.EmployeeController;
import company.Entity.SQLEmployee;

/**
 * Unit test for simple App.
 */
public class EmployeeSQLTest 
{

    @AfterClass
    public static void truncate(){
        EmployeeController.getInstance().truncateTable();
    }

    /**
     * Can I create the table?
     */
    @Test
    public void testTableCreate(){
        EmployeeController ec = EmployeeController.getInstance();
    }

    /**
     * Can I create an employee?
     */
    @Test
    public void testCreateEmployee(){
        EmployeeController ec = EmployeeController.getInstance();
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson");
        assertTrue(e.getQuestion().equals("who are you?"));
    }

    /**
     * What if I try to get a nonexistent one
     */
    @Test
    public void testGetNonExist(){
        EmployeeController ec = EmployeeController.getInstance();
        SQLEmployee e = ec.getEmployee(UUID.randomUUID());
        assertNull(e);
    }

    /**
     * Can I change something?
     */
    @Test
    public void testUpdate(){
        EmployeeController ec = EmployeeController.getInstance();
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson");
        e.setQuestion("what are you?");
        SQLEmployee eDB = ec.getEmployee(e.getId());
        boolean same = e.getQuestion().equals(eDB.getQuestion());
        assertTrue(same);
    }

    /**
     * Can I delete one?
     */
    @Test
    public void canDelete(){
        EmployeeController ec = EmployeeController.getInstance();
        ec.truncateTable();
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson");
        ec.deleteEmployee(e);
        e = ec.getEmployee(e.getId());
        assertNull(e);
    }

}
