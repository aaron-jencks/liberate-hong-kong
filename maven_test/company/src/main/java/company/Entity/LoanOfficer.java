package company.Entity;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ILoanOfficer;

public class LoanOfficer extends AEmployee implements ILoanOfficer {
    public LoanOfficer(String firstName, String lastName, String employeeUsername, long employeeID) {
        super(firstName, lastName, employeeUsername, employeeID);
    }

    @Override
    public void CreateAccount(CreditAccount account, Customer customer) {

    }

    @Override
    public void CloseAccount(CreditAccount account) {

    }
}
