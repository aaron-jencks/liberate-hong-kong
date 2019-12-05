package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.AccountDisplayMenu.AccountDisplayMenu;

public class AccountDisplayMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new AccountDisplayMenu();
    }

    @Override
    public String toString() {
        return "View Account Directory";
    }

}