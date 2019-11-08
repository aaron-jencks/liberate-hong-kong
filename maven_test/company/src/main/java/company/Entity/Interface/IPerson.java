package company.Entity.Interface;

import java.util.UUID;

import company.Entity.Person;

public interface IPerson extends ISaveable {

    public static Person load(UUID id){
        Object o = ISaveable.load(Person.class, id);
        return (Person)o;
    }

    public String getFirstName();
    public IPerson setFirstName(String firstName);
    public String getLastName();
    public IPerson setLastName(String lastName);
}
