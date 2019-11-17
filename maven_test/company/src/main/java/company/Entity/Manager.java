package company.Entity;

import company.Entity.Abstract.AManager;

public class Manager extends AManager {

    public Manager(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public Manager(){
        super();
    }
}
