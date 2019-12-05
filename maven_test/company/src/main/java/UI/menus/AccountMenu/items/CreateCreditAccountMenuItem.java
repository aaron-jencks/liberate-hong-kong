package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.CreateCreditAccountMenu.CreateCreditAccountMenu;

public class CreateCreditAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new CreateCreditAccountMenu();
    }

    @Override
    public String toString() {
        return "Create Credit Account";
    }

}