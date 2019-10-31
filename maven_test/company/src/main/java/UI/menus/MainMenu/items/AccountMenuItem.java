package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.AccountMenu.AccountMenu;

public class AccountMenuItem extends AMenuItem {

    public AccountMenuItem(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new AccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Accounts";
    }
    
}