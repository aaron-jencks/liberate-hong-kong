package company.Entity.Abstract;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ILoanOfficer;
import company.Entity.CreditAccount;
import company.Entity.Customer;

public abstract class ALoanOfficer extends AEmployee implements ILoanOfficer {

    public ALoanOfficer(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
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
