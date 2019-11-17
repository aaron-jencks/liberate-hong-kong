package company.Entity.Interface;

import java.util.UUID;

import org.json.JSONObject;

import company.Entity.Person;

public interface IPerson extends ISaveable {

    /**
     * Load a person from the id
     * @param id
     * @return
     */
    public static Person load(UUID id){
        Object o = ISaveable.load(Person.class, id);
        return (Person)o;
    }

    /**
     * Load the person from json obj
     * @param obj
     * @return
     */
    public static IPerson load(JSONObject obj){
        Object o = ISaveable.load(Person.class, obj);
        return (IPerson)o;
    }

    public String getFirstName();
    public UUID setFirstName(String firstName);
    public String getLastName();
    public UUID setLastName(String lastName);
}
