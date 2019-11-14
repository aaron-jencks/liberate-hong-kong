package company.Controller.Interface;

import company.Entity.Interface.IAccount;
import company.Entity.Interface.ICreditAccount;

public interface IAccountController {
    public void deposit(IAccount account, long amount);
    public void withdrawal(IAccount account, long amount);
    public void accrueInterest();
}
