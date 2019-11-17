package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.Owner;

public interface IOwner extends IHRManager{

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static Owner load(UUID id){
        Object o = ISaveable.load(Owner.class, id);
        return (Owner)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static IPerson load(JSONObject obj){
        Object o = ISaveable.load(Owner.class, obj);
        return (IOwner)o;
    }
}
