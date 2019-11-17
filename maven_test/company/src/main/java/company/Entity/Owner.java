package company.Entity;

import company.Entity.Abstract.AOwner;

public class Owner extends AOwner {
    public Owner(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public Owner(){
        super();
    }
}
