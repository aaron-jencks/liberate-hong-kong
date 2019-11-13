package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.WithdrawlAccountMenu.WithdrawlAccountMenu;

public class WithdrawlAccountMenuItem extends AMenuItem {
    public WithdrawlAccountMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new WithdrawlAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Withdrawl";
    }

}