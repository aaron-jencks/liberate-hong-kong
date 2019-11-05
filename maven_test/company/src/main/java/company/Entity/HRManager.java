package company.Entity;

import java.util.UUID;

import company.Entity.Abstract.AHRManager;

public class HRManager extends AHRManager {
    public HRManager(String firstName, String lastName, String employeeUsername, UUID employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
