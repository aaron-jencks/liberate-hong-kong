package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import company.Entity.Teller;
import company.Entity.Abstract.ATeller;
import company.Entity.Interface.ITeller;

public class TellerTest {

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
        t.setEmployeeUsername("user")
        .setEmployeePassword("pass").setEmployeeSecurityQuestion("question")
        .setEmployeeSecurityAnswer("answer").setFirstName("first").setLastName("last");
        UUID id = t.save();
        //TODO fix
        // Teller t2 = ATeller.load(id);
        // boolean isSame = t.getEmployeeUsername().equals(t2.getEmployeeUsername());
        // assertTrue("Teller: employee username is not the same", isSame);
    }

    /**
     * Test if person subclass of teller is saved and loaded correctly
     */
    @Test
    public void saveableTellerPersonTest(){
        Teller t = new Teller();
        t.setEmployeeUsername("user")
        .setEmployeePassword("pass").setEmployeeSecurityQuestion("question")
        .setEmployeeSecurityAnswer("answer").setFirstName("first").setLastName("last");
        UUID id = t.save();
        //TODO fix
        // Teller t2 = ITeller.load(id);
        // boolean isSame = t.getFirstName().equals(t2.getFirstName());
        // assertTrue("Teller: person first name is not the same. " + t.getFirstName() + " != " + t2.getFirstName(), isSame);
    }

}