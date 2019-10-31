package UI.menus.AccountMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.menus.AccountMenu.items.CreateAccountMenuItem;
import UI.menus.AccountMenu.items.DeleteAccountMenuItem;

public class AccountMenu extends AMenu {

    public AccountMenu(ITermController parent) {
        super(parent);
        title = "Create an account";
        items.add(new CreateAccountMenuItem(this.parent));
        items.add(new DeleteAccountMenuItem(this.parent));
        
    }
    
}