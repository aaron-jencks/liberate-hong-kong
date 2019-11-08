package company.Entity.Interface;

import company.Entity.CreditAccount;
import company.Entity.Customer;

public interface ILoanOfficer extends IEmployee{
    public void CreateAccount(CreditAccount account, Customer customer);
    public void CloseAccount(CreditAccount account);
}
