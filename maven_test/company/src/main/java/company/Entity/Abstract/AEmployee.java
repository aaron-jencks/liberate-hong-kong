package company.Entity.Abstract;

import company.Entity.Abstract.APerson;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.ISaveable;

public abstract class AEmployee extends APerson implements IEmployee, ISaveable {

    protected long employeeID;
    protected long employeePassword;
}
