package company.Controller.Interface;

import company.Entity.Interface.IAccount;

public interface IAccountController {
    public void deposit(IAccount account, long amount);
    public void withdrawal(IAccount account, long amount);
}
