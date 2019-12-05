package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EditLockboxMenu.EditLockboxMenu;

public class EditLockboxMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new EditLockboxMenu();
    }

    @Override
    public String toString() {
        return "Edit a lockbox";
    }
}