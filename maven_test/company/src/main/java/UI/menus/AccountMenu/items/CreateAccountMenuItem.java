package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.CreateAccountMenu.CreateAccountMenu;

public class CreateAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new CreateAccountMenu();
    }

    @Override
    public String toString() {
        return "Create Account";
    }

}