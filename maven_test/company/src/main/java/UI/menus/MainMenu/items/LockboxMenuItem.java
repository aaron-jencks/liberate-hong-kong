package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.LockboxMenu.LockboxMenu;

public class LockboxMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new LockboxMenu();
    }

    @Override
    public String toString() {
        return "Lockbox Menu";
    }
    
}