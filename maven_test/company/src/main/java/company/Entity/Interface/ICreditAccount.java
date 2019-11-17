package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.CreditAccount;

public interface ICreditAccount extends IAccount{

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static CreditAccount load(UUID id){
        Object o = ISaveable.load(CreditAccount.class, id);
        return (CreditAccount)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static ICreditAccount load(JSONObject obj){
        Object o = ISaveable.load(ICreditAccount.class, obj);
        return (ICreditAccount)o;
    }
}
