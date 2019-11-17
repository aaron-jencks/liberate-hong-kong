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
        this.accountIds = new ArrayList<>();
        this.save();
    }

    public ACustomer(APerson person){
        super(person.getFirstName(), person.getLastName());
        this.accountIds = new ArrayList<>();
        this.save();
        person.delete();
    }

    public ACustomer(){
        super();
        this.accountIds = new ArrayList<>();
    }

    public static Customer load(UUID id){
        Object o = ISaveable.load(Customer.class, id);
        return (Customer)o;
    }

    @Override
    public UUID addAccount(UUID accountId){
        this.accountIds.add(accountId);
        return this.save();
    }

    @Override
    public UUID removeAccount(UUID accountId) {
        accountIds.remove(accountId);
        return this.save();
    }

    @Override
    public ArrayList<UUID> getAccountIds()
    {
        return accountIds;
    }

    @Override
    public UUID setAccountIds(ArrayList<UUID> accountIds)
    {
        this.accountIds = accountIds;
        return this.save();
    }
}
