package company.Entity.Interface;

import company.Entity.CreditAccount;
import company.Entity.Customer;

public interface ILoanOfficer {
    void CreateAccount(CreditAccount account, Customer customer);
    void CloseAccount(CreditAccount account);
}
