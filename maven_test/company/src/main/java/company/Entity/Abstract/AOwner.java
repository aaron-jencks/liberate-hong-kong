package company.Entity.Abstract;

import company.Entity.Interface.IOwner;

public class AOwner extends AHRManager implements IOwner {
    public AOwner(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }
}
