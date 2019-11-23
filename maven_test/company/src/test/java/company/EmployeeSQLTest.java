package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import company.Controller.EmployeeController;
import company.Entity.SQLEmployee;

/**
 * Unit test for simple App.
 */
public class EmployeeSQLTest 
{

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

    @Test
    public void canDelete(){
        EmployeeController ec = EmployeeController.getInstance();
        ec.truncateTable();
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson");
        ec.deleteEmployee(e.getId());
        SQLEmployee n = ec.getEmployee(e.getId());
        assertNull(n);
    }

}
