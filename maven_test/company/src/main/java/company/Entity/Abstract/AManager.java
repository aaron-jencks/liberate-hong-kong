package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.IManager;
import company.Entity.Interface.IEmployee;

public abstract class AManager extends AEmployee implements IManager {


    public AManager(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public void Fire(IEmployee e)
    {
        // TODO
    }
}
