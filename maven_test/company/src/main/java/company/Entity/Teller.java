package company.Entity;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ITeller;

public class Teller extends AEmployee implements ITeller {
    public Teller(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public Teller(Person p, String username){
        super(p,username);
    }

    public Teller(Person p){
        super(p);
    }

    public Teller() {
        super();
    }

}
