package main.java.company.Entity.Interface;

import main.java.company.Entity.Interface.IAccount;

public interface IAccountController {
    public void Deposit(IAccount account, long amount);
    public void Withdrawal(IAccount account, long amount);
}
