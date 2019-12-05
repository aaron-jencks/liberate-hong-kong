package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.AccountMenu.AccountMenu;

public class AccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new AccountMenu();
    }

    @Override
    public String toString() {
        return "Accounts";
    }
    
}