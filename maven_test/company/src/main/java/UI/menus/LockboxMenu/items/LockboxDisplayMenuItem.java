package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.LockboxDisplayMenu.LockboxDisplayMenu;

public class LockboxDisplayMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new LockboxDisplayMenu();
    }

    @Override
    public String toString() {
        return "View Lockbox Directory";
    }

}