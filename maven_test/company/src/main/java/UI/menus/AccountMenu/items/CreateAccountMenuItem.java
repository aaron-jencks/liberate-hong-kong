package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.CreateAccountMenu.CreateAccountMenu;

public class CreateAccountMenuItem extends AMenuItem {

    public CreateAccountMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new CreateAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Create Account";
    }

}