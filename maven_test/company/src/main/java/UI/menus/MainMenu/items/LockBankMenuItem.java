package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.LockBankMenu.LockBankMenu;

public class LockBankMenuItem extends AMenuItem {

    public LockBankMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new LockBankMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Lock the Bank";
    }
    
}