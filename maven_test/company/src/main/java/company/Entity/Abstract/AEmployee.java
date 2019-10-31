package main.java.company.Entity.Abstract;

import main.java.company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{

    protected long employeeID;
    protected long employeePassword;

    public long getEmployeeID()
    {
        return employeeID;
    }

    public AEmployee setEmployeeID(long employeeID)
    {
        this.employeeID = employeeID;
        return this;
    }

    public long getEmployeePassword()
    {
        return employeePassword;
    }

    public AEmployee setEmployeePassword(long employeePassword)
    {
        this.employeePassword = employeePassword;
        return this;
    }
}
