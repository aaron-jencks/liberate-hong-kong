package company.Entity;

import java.util.UUID;

import company.Entity.Abstract.AManager;

public class Manager extends AManager {

    public Manager(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }
}
