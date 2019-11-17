package company.Entity;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ILoanOfficer;

public class LoanOfficer extends AEmployee implements ILoanOfficer {
    public LoanOfficer(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName, employeeUsername);
    }

    public LoanOfficer(Person p, String username){
        super(p,username);
    }

    public LoanOfficer(){
        super();
    }

    @Override
    public void CreateAccount(CreditAccount account, Customer customer) {

    }

    @Override
    public void CloseAccount(CreditAccount account) {

    }
}
