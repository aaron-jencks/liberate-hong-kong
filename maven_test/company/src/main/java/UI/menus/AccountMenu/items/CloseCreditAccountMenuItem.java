package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.CloseCreditAccountMenu.CloseCreditAccountMenu;

public class CloseCreditAccountMenuItem extends AMenuItem {

    public CloseCreditAccountMenuItem(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new CloseCreditAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Close Credit Account";
    }

}