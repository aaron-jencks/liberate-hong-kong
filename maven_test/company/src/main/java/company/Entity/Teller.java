package company.Entity;

import java.util.UUID;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ITeller;

public class Teller extends AEmployee implements ITeller {
    public Teller(String firstName, String lastName, String employeeUsername, UUID employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }

    public Teller() {
        super();
    }

}
