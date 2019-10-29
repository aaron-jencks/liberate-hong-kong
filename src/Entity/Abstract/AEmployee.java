package Entity.Abstract;

import Entity.Abstract.APerson;
import Entity.Interface.IEmployee;
import Entity.Interface.ISaveable;

public abstract class AEmployee extends APerson implements IEmployee, ISaveable {

    protected long employeeID;
    protected long employeePassword;
}
