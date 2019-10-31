package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.DeleteAccountMenu.DeleteAccountMenu;

public class DeleteAccountMenuItem extends AMenuItem {

    public DeleteAccountMenuItem(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new DeleteAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Delete Account";
    }
    
}