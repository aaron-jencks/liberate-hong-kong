package company.Entity;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ITeller;

public class Teller extends AEmployee implements ITeller {
    public Teller(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }
}
