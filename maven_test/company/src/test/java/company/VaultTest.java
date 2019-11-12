package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.Customer;
import company.Entity.Person;
import company.Entity.Vault;
import company.Entity.Abstract.ACustomer;
import company.Entity.Interface.ISaveable;

public class VaultTest{

    @After
    public void cleanUp(){
        ISaveable.clearFile(Vault.class.getName());
    }

    @Test
    public void InstanceTest(){
        Vault v = Vault.getInstance();
    }

    @Test
    public void VaultCustomerTest(){
        Vault v = Vault.getInstance();
        Person p = new Person("first", "last");
        UUID cid = v.createCustomer(p);
        Customer c = ACustomer.load(cid);
        boolean isSame = p.getFirstName().equals(c.getFirstName());
        boolean sameId = p.getObjectId().equals(c.getObjectId());
        assertTrue("Customer and person do not match", isSame);
        assertTrue("Two different instances", sameId);
    }
}