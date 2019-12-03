package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.LockboxMenu.LockboxMenu;

public class LockboxMenuItem extends AMenuItem {

    public LockboxMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new LockboxMenu(parent);
    }

    @Override
    public String toString() {
        return "Lockbox Menu";
    }
    
}