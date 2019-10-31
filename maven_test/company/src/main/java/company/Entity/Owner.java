package company.Entity;

import company.Entity.Abstract.AOwner;

public class Owner extends AOwner {
    public Owner(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
