package company;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import company.Entity.BankAccount;
import company.Entity.Person;
import company.Entity.Teller;
import company.Entity.Vault;
import company.Entity.Abstract.ABankAccount;
import company.Entity.Abstract.APerson;
import company.Entity.Abstract.ASaveable;
import company.Entity.Abstract.ATeller;
import company.Entity.Abstract.AVault;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void tellerTestEmployee(){
        Teller t = new Teller();
        t.setEmployeeID(123l).setEmployeeUsername("user")
        .setEmployeePassword("pass").setEmployeeSecurityQuestion("question")
        .setEmployeeSecurityAnswer("answer").setFirstName("first").setLastName("last");
        UUID id = t.save();
        Teller t2 = ATeller.load(id);
        boolean isSame = t.getEmployeeUsername().equals(t2.getEmployeeUsername());
        assertTrue("Teller: employee username is not the same", isSame);
    }

    @Test
    public void tellerTestPerson(){
        Teller t = new Teller();
        t.setEmployeeID(123l).setEmployeeUsername("user")
        .setEmployeePassword("pass").setEmployeeSecurityQuestion("question")
        .setEmployeeSecurityAnswer("answer").setFirstName("first").setLastName("last");
        UUID id = t.save();
        Teller t2 = ATeller.load(id);
        boolean isSame = t.getFirstName().equals(t2.getFirstName());
        assertTrue("Teller: person first name is not the same. " + t.getFirstName() + " != " + t2.getFirstName(), isSame);
    }

    @Test
    public void bankAccountTest(){
        BankAccount ba = new BankAccount();
        ba.setAccountNumber("test").setAmount(123l);
        UUID id = ba.save();
        BankAccount b2 = ABankAccount.load(id);
        boolean isSame = ba.getAccountNumber().equals(b2.getAccountNumber());
        assertTrue("Bank: account numbers do not match", isSame);
    }

    
    public void vaultTest(){
        Vault v = new Vault();
        Person p0 = new Person("first", "last");
        long pId = v.createTeller(p0);
        UUID id = v.save();
        Vault v0 = AVault.load(id);
        boolean isSame = v0.getEmployees().get(pId).getFirstName().equals(p0.getFirstName());
        assertTrue(isSame);
    }
}
