package company.Entity.Abstract;

import company.Entity.Customer;
import company.Entity.Interface.ICustomer;
import company.Entity.Interface.ISaveable;

import java.util.ArrayList;
import java.util.UUID;

public abstract class ACustomer extends APerson implements ICustomer
{
    protected ArrayList<UUID> accountIds;

    public ACustomer(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public ACustomer(){
        super();
        this.accountIds = null;
    }

    public static Customer load(UUID id){
        Object o = ISaveable.load(Customer.class, id);
        return (Customer)o;
    }

    @Override
    public ACustomer addAccount(UUID accountId){
        this.accountIds.add(accountId);
        this.save();
        return this;
    }

    @Override
    public ICustomer removeAccount(UUID accountId) {
        accountIds.remove(accountId);
        this.save();
        return this;
    }

    @Override
    public ArrayList<UUID> getAccountIds()
    {
        return accountIds;
    }

    @Override
    public ACustomer setAccountIds(ArrayList<UUID> accountIds)
    {
        this.accountIds = accountIds;
        this.save();
        return this;
    }
}
