package company.Entity.Abstract;

import java.util.UUID;

public abstract class APerson{
    protected UUID id;
    protected String firstName;
    protected String lastName;

    @Override
    public String toString()
    {
        return "Name: " + firstName + " " + lastName + " Id: " + id;
    }
}