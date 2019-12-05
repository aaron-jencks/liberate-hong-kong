package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.UnlockBankMenu.UnlockBankMenu;

public class UnlockBankMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new UnlockBankMenu();
    }

    @Override
    public String toString() {
        return "Unlock the Bank";
    }
    
}