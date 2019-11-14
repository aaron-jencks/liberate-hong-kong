package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.CreateCreditAccountMenu.CreateCreditAccountMenu;

public class CreateCreditAccountMenuItem extends AMenuItem {

    public CreateCreditAccountMenuItem(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new CreateCreditAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Create Credit Account";
    }

}