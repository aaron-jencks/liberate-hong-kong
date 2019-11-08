package company.Entity;

import company.Entity.Abstract.ACustomer;

public class Customer extends ACustomer {
    public Customer(String firstName, String lastName) {
        super(firstName, lastName);
        this.save();
    }

    public Customer(){
        super();
    }
}
