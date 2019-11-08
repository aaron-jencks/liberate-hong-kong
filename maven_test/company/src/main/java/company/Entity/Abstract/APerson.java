package company.Entity.Abstract;

import company.Entity.Interface.IPerson;

public abstract class APerson extends ASaveable implements IPerson
{

    protected String firstName;
    protected String lastName;

    public APerson(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
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

    public IPerson setFirstName(String firstName)
    {
        this.firstName = firstName;
        this.save();
        return this;
    }

    public String getLastName()
    {
        return lastName;
    }

    public IPerson setLastName(String lastName)
    {
        this.lastName = lastName;
        this.save();
        return this;
    }
}
