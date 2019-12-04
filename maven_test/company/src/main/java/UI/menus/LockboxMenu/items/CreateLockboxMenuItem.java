package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.CreateLockboxMenu.CreateLockboxMenu;

public class CreateLockboxMenuItem extends AMenuItem{

    @Override
    public AMenu activate() {
        return new CreateLockboxMenu();
    }

    @Override
    public String toString() {
        return "Create a lockbox";
    }
}