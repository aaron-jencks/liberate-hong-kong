package UI.menus.AccountMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.AccountMenu.items.AccrueInterestItem;
import UI.menus.AccountMenu.items.CreateAccountMenuItem;
import UI.menus.AccountMenu.items.CreateCreditAccountMenuItem;
import UI.menus.AccountMenu.items.DeleteAccountMenuItem;
import UI.menus.AccountMenu.items.DepositAccountMenuItem;
import UI.menus.AccountMenu.items.WithdrawlAccountMenuItem;

public class AccountMenu extends AMenu {

    public AccountMenu(ITermController parent) {
        super(parent);
        title = "Create an account";
        items.add(new CreateAccountMenuItem(this.parent));
        items.add(new DeleteAccountMenuItem(this.parent));
        items.add(new DepositAccountMenuItem(this.parent));
        items.add(new WithdrawlAccountMenuItem(this.parent));
        items.add(new CreateCreditAccountMenuItem(this.parent));
        items.add(new AccrueInterestItem(this.parent));
        items.add(new ExitItem(this.parent));
    }
    
}