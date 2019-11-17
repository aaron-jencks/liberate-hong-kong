package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.HRManager;
import company.Entity.Interface.IPerson;

public interface IHRManager extends IManager{
    public long Hire(IPerson p);

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static HRManager load(UUID id){
        Object o = ISaveable.load(HRManager.class, id);
        return (HRManager)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static IHRManager load(JSONObject obj){
        Object o = ISaveable.load(HRManager.class, obj);
        return (IHRManager)o;
    }
}
