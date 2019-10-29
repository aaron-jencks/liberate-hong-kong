package Entity.Interface;

import Entity.CreditAccount;
import Entity.Customer;

public interface ILoanOfficer {
    void CreateAccount(CreditAccount account, Customer customer);
    void CloseAccount(CreditAccount account);
}
