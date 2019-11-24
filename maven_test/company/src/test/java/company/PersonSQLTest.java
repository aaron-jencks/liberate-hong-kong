package company;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.PersonController;
import company.Entity.SQLPerson;

/**
 * Unit test for simple App.
 */
public class PersonSQLTest {
    public static PersonController pc;

    @AfterClass
    public static void truncate() {
        PersonController.getInstance().truncateTable();
    }

    @Before
    public void setup() {
        pc = PersonController.getInstance();
    }

    /**
     * Can I create a person?
     */
    @Test
    public void testCreatePerson() {
        SQLPerson p = pc.createPerson("billy", "bob");
        assertTrue(p.getFirstName().equals("billy"));
    }

    /**
     * What if I try to get an employee that doesn't exist?
     */
    @Test
    public void testGetNonExist() {
        SQLPerson p = pc.getPerson(UUID.randomUUID());
        assertNull(p);
    }

    /**
     * Can I change it?
     */
    @Test
    public void testUpdate() {
        SQLPerson p = pc.createPerson("tom", "joe");
        p.setFirstName("not billy");
        assertNotEquals(p.getFirstName(), "tom");
    }

    /**
     * Can I delete it?
     */
    @Test
    public void testDelete(){
        SQLPerson p = pc.createPerson("a", "b");
        pc.deletePerson(p);
        
    }

}
