package main.java.company.Entity.Abstract;

import main.java.company.Entity.Abstract.AEmployee;
import main.java.company.Entity.Interface.ILoanOfficer;
import main.java.company.Entity.CreditAccount;
import main.java.company.Entity.Customer;

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
