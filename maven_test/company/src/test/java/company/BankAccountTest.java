package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.BankAccount;
import company.Entity.Abstract.ABankAccount;
import company.Entity.Interface.ISaveable;

public class BankAccountTest{

    @After
    public void cleanUp(){
        ISaveable.clearFile(BankAccount.class.getName());
    }

//    @Test
//    public void createAccountTest(){
//        BankAccount ba = new BankAccount();
//        UUID id =  ba.setAmount(12456);
//        BankAccount b2 = ABankAccount.load(id);
//        boolean isSame = ba.getAmount() == b2.getAmount();
//        boolean sameId = ba.getObjectId().equals(b2.getObjectId());
//        assertTrue("Bank amounts do not match. { " + ba.getAmount() + " != " + b2.getAmount() + " }", isSame);
//        assertTrue("Bank account ids do not match. { " + ba.getObjectId() + " != " + b2.getObjectId() + " }", sameId);
//    }
}