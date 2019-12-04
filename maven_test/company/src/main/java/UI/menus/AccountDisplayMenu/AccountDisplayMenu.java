package UI.menus.AccountDisplayMenu;

import UI.menus.ADisplayMenu;
import company.Controller.AccountController;

public class AccountDisplayMenu extends ADisplayMenu {
    public AccountDisplayMenu()
    {
        super(AccountController.getInstance().getAll());
        title = "Current Account Directory";
    }
}