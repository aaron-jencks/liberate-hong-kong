package company.Entity.Interface;

import java.util.ArrayList;
import java.util.UUID;

import org.json.JSONObject;

import company.Entity.Customer;

public interface ICustomer extends IPerson {
    public UUID addAccount(UUID accountId);
    public UUID removeAccount(UUID accountId);
    public UUID setAccountIds(ArrayList<UUID> accountIds);
    public ArrayList<UUID> getAccountIds();

    /**
     * Load customer from id
     * @param id
     * @return
     */
    public static ICustomer load(UUID id){
        Object o = ISaveable.load(ICustomer.class, id);
        return (ICustomer)o;
    }

    
    /**
     * Load the customer from json obj
     * @param obj
     * @return
     */
    public static ICustomer load(JSONObject obj){
        Object o = ISaveable.load(Customer.class, obj);
        return (ICustomer)o;
    }
}
