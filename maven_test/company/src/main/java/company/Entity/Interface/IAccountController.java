package company.Entity.Interface;

import company.Entity.Customer;
import company.Entity.Interface.IAccount;

public interface IAccountController {
    public void Deposit(IAccount account, long amount);
    public void Withdrawal(IAccount account, long amount);
    public String createBankAccount(Customer c);
    public String createCreditAccount(Customer c);
}
