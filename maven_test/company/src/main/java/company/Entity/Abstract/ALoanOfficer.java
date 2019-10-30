package company.Entity.Abstract;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.ILoanOfficer;
import company.Entity.CreditAccount;
import company.Entity.Customer;

public abstract class ALoanOfficer extends AEmployee implements ILoanOfficer {
    
    public void CreateAccount(CreditAccount account, Customer customer)
    {
        // TODO
    }

    
    public void CloseAccount(CreditAccount account)
    {
        // TODO
    }
}
