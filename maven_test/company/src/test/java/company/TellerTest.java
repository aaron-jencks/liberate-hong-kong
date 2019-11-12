package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.Teller;
import company.Entity.Interface.ISaveable;
import company.Entity.Interface.ITeller;

public class TellerTest {

    @After
    public void cleanUp(){
        ISaveable.clearFile(Teller.class.getName());
    }

    /**
     * Rigorous Test :-)
     * Sanity check
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Test if the employee subclass of a teller is saved and loaded correctly
     */
    @Test
    public void savableTellerEmployeeTest(){
        Teller t = new Teller();
        t.setEmployeeUsername("user");
        t.setEmployeePassword("pass");
        t.setEmployeeSecurityQuestion("question");
        t.setEmployeeSecurityAnswer("answer");
        t.setFirstName("first");
        t.setLastName("last");
        UUID id = t.save();
        Teller t2 = ITeller.load(id);
        boolean isSame = t.getEmployeeUsername().equals(t2.getEmployeeUsername());
        assertTrue("Teller: employee username is not the same. { " + t.getEmployeeUsername() + " != " + t2.getEmployeeUsername() + " } ", isSame);
    }

    /**
     * Test if person subclass of teller is saved and loaded correctly
     */
    // @Test
    public void saveableTellerPersonTest(){
        Teller t = new Teller();
        t.setEmployeeUsername("user");
        t.setEmployeePassword("pass");
        t.setEmployeeSecurityQuestion("question");
        t.setEmployeeSecurityAnswer("answer");
        t.setFirstName("first");
        t.setLastName("last");
        UUID id = t.save();
        Teller t2 = ITeller.load(id);
        boolean isSame = t.getFirstName().equals(t2.getFirstName());
        assertTrue("Teller: person first name is not the same. { " + t.getFirstName() + " != " + t2.getFirstName() + " }", isSame);
    }

}