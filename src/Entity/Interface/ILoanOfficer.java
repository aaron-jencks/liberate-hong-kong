package Entity.Interface;

import Entity.Customer;

public interface ILoanOfficer {
    void CreateAccount(ICreditAccount account, Customer customer);
    void CloseAccount(ICreditAccount account);
}
