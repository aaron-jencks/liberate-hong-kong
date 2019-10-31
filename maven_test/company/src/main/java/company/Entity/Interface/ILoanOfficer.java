package main.java.company.Entity.Interface;

import main.java.company.Entity.CreditAccount;
import main.java.company.Entity.Customer;

public interface ILoanOfficer {
    public void CreateAccount(CreditAccount account, Customer customer);
    public void CloseAccount(CreditAccount account);
}
