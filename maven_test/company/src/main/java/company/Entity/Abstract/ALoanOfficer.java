package company.Entity.Abstract;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ILoanOfficer;

import company.Entity.CreditAccount;
import company.Entity.Customer;
import company.Entity.Person;

public abstract class ALoanOfficer extends AEmployee implements ILoanOfficer {

    public ALoanOfficer(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public ALoanOfficer(Person person, String username){
        super(person, username);
    }

    public ALoanOfficer(AEmployee employee, String username){
        super(employee, username);
    }

    public ALoanOfficer(){
        super();
    }

    public void CreateAccount(CreditAccount account, Customer customer)
    {
        // TODO
    }

    
    public void CloseAccount(CreditAccount account)
    {
        // TODO
    }
}
