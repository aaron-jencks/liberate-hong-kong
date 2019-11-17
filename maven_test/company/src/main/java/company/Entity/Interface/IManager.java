package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.Manager;
import company.Entity.Interface.IEmployee;

public interface IManager extends IEmployee{
    public void Fire(IEmployee e);

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static IManager load(UUID id){
        Object o = ISaveable.load(Manager.class, id);
        return (Manager)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static IManager load(JSONObject obj){
        Object o = ISaveable.load(Manager.class, obj);
        return (IManager)o;
    }
}
