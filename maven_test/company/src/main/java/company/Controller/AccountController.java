package company.Controller;

import company.Controller.Interface.IAccountController;
import company.Entity.Vault;
import company.Entity.Interface.IAccount;
import company.Entity.Interface.ICreditAccount;

public class AccountController implements IAccountController {

    private static AccountController instance = null;

    private AccountController() {}

    public static AccountController getInstance()
    {
        if(instance == null)
            instance = new AccountController();
        return instance;
    }

    @Override
    public void deposit(IAccount account, long amount)
    {
        // TODO
    }

    @Override
    public void withdrawal(IAccount account, long amount)
    {
        // TODO
    }

    public void accrueInterest()
    {
        for(IAccount acct : Vault.getInstance().getAccounts().values())
            if(ICreditAccount.class.isAssignableFrom(acct.getClass()))
                ((ICreditAccount)acct).accrueInterest();
    }
}
