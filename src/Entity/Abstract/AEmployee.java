package Entity.Abstract;

import Entity.Interface.IEmployee;
import Entity.Interface.ISaveable;

public abstract class AEmployee implements IEmployee, ISaveable {

    protected long employeeID;
    protected long employeePassword;
}
