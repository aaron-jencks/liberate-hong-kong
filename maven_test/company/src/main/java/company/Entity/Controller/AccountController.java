package company.Entity.Controller;

import company.Entity.Customer;
import company.Entity.Interface.IAccountController;
import company.Entity.Interface.IAccount;

public class AccountController implements IAccountController {
    @Override
    public void Deposit(IAccount account, long amount)
    {
        // TODO
    }

    @Override
    public void Withdrawal(IAccount account, long amount)
    {
        // TODO
    }

    public String createBankAccount(Customer c)
    {
        return "";
    }

    public String createCreditAccount(Customer c)
    {
        return "";
    }
}
