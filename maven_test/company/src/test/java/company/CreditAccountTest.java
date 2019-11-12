package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.CreditAccount;
import company.Entity.Abstract.ACreditAccount;
import company.Entity.Interface.ISaveable;

public class CreditAccountTest{

    @After
    public void cleanUp(){
        ISaveable.clearFile(CreditAccount.class.getName());
    }

    @Test
    public void createAccountTest(){
        CreditAccount ca = new CreditAccount();
        UUID id =  ca.setAmount(12456);
        CreditAccount c2 = ACreditAccount.load(id);
        boolean isSame = ca.getAmount() == c2.getAmount();
        boolean sameId = ca.getObjectId().equals(c2.getObjectId());
        assertTrue("Credit amounts do not match. { " + ca.getAmount() + " != " + c2.getAmount() + " }", isSame);
        assertTrue("Credit account ids do not match. { " + ca.getObjectId() + " != " + c2.getObjectId() + " }", sameId);
    }
}