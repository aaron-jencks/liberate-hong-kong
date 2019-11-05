package company.Entity.Abstract;

import company.Entity.BankAccount;
import company.Entity.Customer;
import company.Entity.Interface.ICustomer;

import java.util.ArrayList;
import java.util.UUID;

public abstract class ACustomer extends APerson implements ICustomer
{
    protected ArrayList<String> accountIds;

    public ACustomer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ACustomer(){
        super();
        this.accountIds = null;
    }

    public static Customer load(UUID id){
        Object o = ASaveable.load(Customer.class, id);
        return (Customer)o;
    }

    public void AddAccount(String accountId){
        this.accountIds.add(accountId);
        this.save();
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
