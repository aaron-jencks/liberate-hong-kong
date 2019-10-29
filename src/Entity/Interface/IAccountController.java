package Entity.Interface;

import Entity.Interface.IAccount;

public interface IAccountController {
    void Deposit(IAccount account, long amount);
    void Withdrawl(IAccount account, long amount);
}
