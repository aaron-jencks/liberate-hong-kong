package company.Entity;

import company.Entity.Abstract.AHRManager;

public class HRManager extends AHRManager {
    public HRManager(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
