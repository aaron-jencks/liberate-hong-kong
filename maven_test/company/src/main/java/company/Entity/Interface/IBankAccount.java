package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.BankAccount;

public interface IBankAccount extends IAccount{

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static BankAccount load(UUID id){
        Object o = ISaveable.load(BankAccount.class, id);
        return (BankAccount)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static IBankAccount load(JSONObject obj){
        Object o = ISaveable.load(BankAccount.class, obj);
        return (IBankAccount)o;
    }
}
