package company;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * Sanity check
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    

    // @Test
    // public void bankAccountTest(){
    //     BankAccount ba = new BankAccount();
    //     ba.setAccountNumber("test").setAmount(123l);
    //     UUID id = ba.save();
    //     BankAccount b2 = ABankAccount.load(id);
    //     boolean isSame = ba.getAccountNumber().equals(b2.getAccountNumber());
    //     assertTrue("Bank: account numbers do not match", isSame);
    // }

    // @Test
    // public void vaultTestPerson(){
    //     Vault v = new Vault();
    //     Person p = new Person("first", "last");
    //     UUID id = v.createTeller(p);
    //     IEmployee t = v.getEmployee(id);
    //     boolean isSame = t.getFirstName().equals(p.getFirstName());
    //     assertTrue(isSame);
    // }
}
