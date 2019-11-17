package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.Teller;

public interface ITeller extends IEmployee{
    
    /**
     * Load a teller with the given id
     * @param id
     * @return
     */
    public static Teller load(UUID id){
        Object o = ISaveable.load(Teller.class, id);
        return (Teller)o;
    }

    /**
     * Load a teller from the json obj
     * @param obj
     * @return
     */
    public static ITeller load(JSONObject obj){
        Object o = ISaveable.load(Teller.class, obj);
        return (ITeller)o;
    }
}
