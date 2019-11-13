package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.DepositAccountMenu.DepositAccountMenu;

public class DepositAccountMenuItem extends AMenuItem {

    public DepositAccountMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new DepositAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Deposit";
    }

}