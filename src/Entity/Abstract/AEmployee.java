package Entity.Abstract;

import Entity.Abstract.APerson;
import Entity.Interface.IEmployee;
import Entity.Interface.ISaveable;

public abstract class AEmployee extends APerson implements IEmployee, ISaveable {

    protected long employeeID;
    protected long employeePassword;

    public long getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(long employeeID)
    {
        this.employeeID = employeeID;
    }

    public long getEmployeePassword()
    {
        return employeePassword;
    }

    public void setEmployeePassword(long employeePassword)
    {
        this.employeePassword = employeePassword;
    }
}
