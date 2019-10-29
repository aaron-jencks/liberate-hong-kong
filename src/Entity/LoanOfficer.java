package Entity;

import Entity.Abstract.AEmployee;
import Entity.Interface.ILoanOfficer;

public class LoanOfficer extends AEmployee implements ILoanOfficer {
    @Override
    public void CreateAccount(CreditAccount account, Customer customer) {

    }

    @Override
    public void CloseAccount(CreditAccount account) {

    }
}
