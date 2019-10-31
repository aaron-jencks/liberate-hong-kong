package main.java.company.Entity.Abstract;

import main.java.company.Entity.Interface.IPerson;

public abstract class APerson extends ASaveable implements IPerson
{

    protected String firstName;
    protected String lastName;
    protected String email;

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

    public String getEmail()
    {
        return email;
    }

    public APerson setEmail(String email)
    {
        this.email = email;
        return this;
    }
}
