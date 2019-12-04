package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.CloseCreditAccountMenu.CloseCreditAccountMenu;

public class CloseCreditAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new CloseCreditAccountMenu();
    }

    @Override
    public String toString() {
        return "Close Credit Account";
    }

}