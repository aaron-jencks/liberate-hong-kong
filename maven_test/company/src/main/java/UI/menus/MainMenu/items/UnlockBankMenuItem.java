package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.IMenu;
import UI.menus.UnlockBankMenu.UnlockBankMenu;
import company.Entity.BankLock;

public class UnlockBankMenuItem extends AMenuItem {

    private IMenu parent;

    public UnlockBankMenuItem(IMenu parent)
    {
        this.parent = parent;
    }

    @Override
    public boolean is_available() {
        return BankLock.getInstance().isBankLocked();
    }

    @Override
    public AMenu activate() {
        parent.invalidate();
        return new UnlockBankMenu();
    }

    @Override
    public String toString() {
        return "Unlock the Bank";
    }
    
}