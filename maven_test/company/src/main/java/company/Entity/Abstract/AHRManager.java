package company.Entity.Abstract;

import company.Entity.Abstract.AManager;
import company.Entity.Interface.IHRManager;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.IPerson;

public abstract class AHRManager extends AManager implements IHRManager {

    public AHRManager(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }

    public long Hire(IPerson p)
    {
        // TODO
    	
        return -1;
    }
}
