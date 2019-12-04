package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.WithdrawlAccountMenu.WithdrawlAccountMenu;

public class WithdrawlAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new WithdrawlAccountMenu();
    }

    @Override
    public String toString() {
        return "Withdrawl";
    }

}