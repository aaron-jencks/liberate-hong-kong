package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Interface.IPerson;

public abstract class APerson extends ASaveable implements IPerson
{
    private String firstName;
    private String lastName;

    public APerson(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.save();
    }

    public APerson(){
        super();
        this.firstName = null;
        this.lastName = null;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public UUID setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this.save();
    }

    public String getLastName()
    {
        return lastName;
    }

    public UUID setLastName(String lastName)
    {
        this.lastName = lastName;
        return this.save();
    }
}
