package Entity.Interface;

public interface IAccountController
{
    void Deposit(IAccount account, long amount);

    void Withdrawl(IAccount account, long amount);
}
