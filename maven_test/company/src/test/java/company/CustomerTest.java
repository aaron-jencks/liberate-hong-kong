package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.BankAccount;
import company.Entity.Customer;
import company.Entity.Abstract.ACustomer;
import company.Entity.Interface.ISaveable;

public class CustomerTest{

    @After
    public void cleanUp(){
        ISaveable.clearFile(Customer.class.getName());
    }


    @Test
    public void CustomerPersonTest(){
        Customer c = new Customer("first", "last");
        UUID id = c.setFirstName("notFirst");
        Customer c2 = ACustomer.load(id);
        boolean isSame = c.getFirstName().equals(c2.getFirstName());
        boolean sameId = c.getObjectId().equals(c2.getObjectId());
        assertTrue("Loaded customer names do not match { " + c.getFirstName() + " != " + c2.getFirstName() +  " }", isSame);
        assertTrue("Loaded customers do not have same objIds { " + c.getObjectId() + " != " + c2.getObjectId() + " }", sameId);
    }

    @Test
    public void addAccountsTest(){
        BankAccount ba = new BankAccount();
        UUID acctId = ba.setAmount(123465);
        Customer c = new Customer("first", "last");
        UUID cId = c.addAccount(acctId);
        Customer c2 = ACustomer.load(cId);
        boolean hasAcct = c2.getAccountIds().contains(acctId);
        assertTrue("Customer does not have the accountid saved.", hasAcct);
    }

}