package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.DepositAccountMenu.DepositAccountMenu;

public class DepositAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new DepositAccountMenu();
    }

    @Override
    public String toString() {
        return "Deposit";
    }

}