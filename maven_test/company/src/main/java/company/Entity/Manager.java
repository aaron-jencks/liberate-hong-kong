package company.Entity;

import company.Entity.Abstract.AManager;

public class Manager extends AManager {

    public Manager(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
