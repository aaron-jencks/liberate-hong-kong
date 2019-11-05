package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Interface.IOwner;

public class AOwner extends AHRManager implements IOwner {
    public AOwner(String firstName, String lastName, String employeeUsername, UUID employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
