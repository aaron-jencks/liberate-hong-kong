package company.Entity.Abstract;

import company.Entity.BankAccount;
import company.Entity.Interface.ICustomer;

import java.util.ArrayList;
import java.util.UUID;

public abstract class ACustomer extends APerson implements ICustomer
{
    protected ArrayList<String> accountIds;

    public ACustomer(String firstName, String lastName) {
        super(firstName, lastName);
    }
    
    public String createAccount(){
        BankAccount ba = new BankAccount();
        ba.setAmount(0)
        .setAccountNumber(UUID.randomUUID().toString());
        ba.save();
        return ba.getAccountNumber();
    }

    public ArrayList<String> getAccountIds()
    {
        return accountIds;
    }

    public ACustomer setAccountIds(ArrayList<String> accountIds)
    {
        this.accountIds = accountIds;
        return this;
    }
}
