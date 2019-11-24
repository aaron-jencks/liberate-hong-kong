package company.Controller.Interface;

import company.Entity.Interface.ICreditAccount;

public interface IAccountController {

    /**
     * Accrues interest on all credit accounts in the vault
     */
    public void accrueInterest();
}
