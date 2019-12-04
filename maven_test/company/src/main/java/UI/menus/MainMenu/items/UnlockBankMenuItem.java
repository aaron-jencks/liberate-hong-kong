package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.UnlockBankMenu.UnlockBankMenu;

public class UnlockBankMenuItem extends AMenuItem {

    public UnlockBankMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new UnlockBankMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Unlock the Bank";
    }
    
}