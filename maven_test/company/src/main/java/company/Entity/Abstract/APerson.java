package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Person;
import company.Entity.Interface.IPerson;

public abstract class APerson extends ASaveable implements IPerson
{

    protected String firstName;
    protected String lastName;

    public APerson(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public APerson(){
        this.firstName = null;
        this.lastName = null;
    }

    // public static Person load(UUID id){
    //     Object o = ASaveable.load(Person.class, id);
    //     return (Person)o;
    // }

    public String getFirstName()
    {
        return firstName;
    }

    public APerson setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public String getLastName()
    {
        return lastName;
    }

    public APerson setLastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }
}
