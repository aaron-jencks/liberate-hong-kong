package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.sql.SQLPermission;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.EmployeeController;
import company.Controller.PersonController;
import company.Entity.SQLEmployee;
import company.Entity.SQLPerson;
import company.Entity.Enum.Position;

/**
 * Unit test for simple App.
 */
public class EmployeeSQLTest 
{

    public static SQLPerson p;
    public static EmployeeController ec;

    @AfterClass
    public static void truncate(){
        EmployeeController.getInstance().truncateTable();
    }

    @Before
    public void setup(){
        p = PersonController.getInstance().createPerson("firsty", "lasty");
        ec = EmployeeController.getInstance();
    }

    /**
     * Can I create an employee?
     */
    @Test
    public void testCreateEmployee(){
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson", p);
        assertTrue(e.getQuestion().equals("who are you?"));
    }

    /**
     * What if I try to get a nonexistent one
     */
    @Test
    public void testGetNonExist(){
        SQLEmployee e = ec.getEmployee(UUID.randomUUID());
        assertNull(e);
    }

    /**
     * Can I change something?
     */
    @Test
    public void testUpdate(){
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson", p);
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
        ec.truncateTable();
        SQLEmployee e = ec.createEmployee("who are you?", "me", "tom", "thompson", p);
        ec.deleteEmployee(e);
        e = ec.getEmployee(e.getId());
        assertNull(e);
    }

}
