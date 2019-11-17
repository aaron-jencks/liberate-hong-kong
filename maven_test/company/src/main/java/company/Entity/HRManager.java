package company.Entity;

import company.Entity.Abstract.AHRManager;

public class HRManager extends AHRManager {
    public HRManager(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public HRManager(){
        super();
    }
}
