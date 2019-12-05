package company;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Employee;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.EmployeeController;
import company.Controller.PersonController;
import company.Entity.Person;
import company.Entity.Enum.Position;

/**
 * Unit test for simple App.
 */
public class EmployeeTest
{

    public static Person p;
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
        Employee e = ec.createEmployee(Position.TELLER, "who are you?", "me", "tom", "thompson", p);
        assertTrue(e.getQuestion().equals("who are you?"));
    }

    @Test
    public void getAllTest(){
        truncate();
        for(int i = 0; i < 5; i++){
            ec.createEmployee(Position.TELLER, "f" + i, "l" + i);
        }
        ArrayList<Employee> allEmps = ec.getAll();
        for (Employee employee : allEmps) {
            System.out.println("Emp : " + employee.getFirstName());
        }
        boolean sameSize = allEmps.size() == 5;
        assertTrue(sameSize);
    }

    /**
     * What if I try to get a nonexistent one
     */
    @Test
    public void testGetNonExist(){
        Employee e = ec.getEmployee(UUID.randomUUID());
        assertNull(e);
    }

    /**
     * Can I change something?
     */
    @Test
    public void testUpdate(){
        Employee e = ec.createEmployee(Position.TELLER, "who are you?", "me", "tom", "thompson", p);
        e.setQuestion("what are you?");
        Employee eDB = ec.getEmployee(e.getId());
        boolean same = e.getQuestion().equals(eDB.getQuestion());
        assertTrue(same);
    }

    /**
     * Can I delete one?
     */
    @Test
    public void canDelete(){
        ec.truncateTable();
        Employee e = ec.createEmployee(Position.TELLER, "who are you?", "me", "tom", "thompson", p);
        ec.deleteEmployee(e);
        e = ec.getEmployee(e.getId());
        assertNull(e);
    }

    @Test
    public void createFromPerson(){
        Employee e = ec.createEmployee(Position.TELLER, "a", "b", "c", "d", p);
        assertSame(e.getId(), p.getId());
        assertSame(e.getFirstName(), p.getFirstName());
    }

    /**
     * Can I change the name and not break everything?
     */
    @Test
    public void testUpdateName(){
        Employee e = ec.createEmployee(Position.MANAGER,"a", "b", "c", "d", p);
        e.setFirstName("not");
        p = PersonController.getInstance().getPerson(e.getId());
        //should affect the other after load
        boolean same = e.getFirstName().equals(p.getFirstName());
        assertTrue(same);
    }

}
