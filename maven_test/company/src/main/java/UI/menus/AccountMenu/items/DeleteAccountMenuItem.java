package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.DeleteAccountMenu.DeleteAccountMenu;

public class DeleteAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new DeleteAccountMenu();
    }

    @Override
    public String toString() {
        return "Delete Account";
    }
    
}