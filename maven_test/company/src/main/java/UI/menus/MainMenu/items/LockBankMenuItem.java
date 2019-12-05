package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.IMenu;
import UI.menus.LockBankMenu.LockBankMenu;
import company.Entity.BankLock;

public class LockBankMenuItem extends AMenuItem {

    private IMenu parent;

    public LockBankMenuItem(IMenu parent)
    {
        this.parent = parent;
    }

    @Override
    public boolean is_available() {
       return !BankLock.getInstance().isBankLocked();
    }

    @Override
    public AMenu activate() {
        parent.invalidate();
        return new LockBankMenu();
    }

    @Override
    public String toString() {
        return "Lock the Bank";
    }
    
}