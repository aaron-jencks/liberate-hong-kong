package Entity.Interface;

import Entity.Account;

public interface IAccountManager {
    void Deposit(Account account, long amount);
    void Withdrawl(Account account, long amount);
}
