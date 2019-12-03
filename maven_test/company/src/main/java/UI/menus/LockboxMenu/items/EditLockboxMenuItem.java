package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EditLockboxMenu.EditLockboxMenu;

public class EditLockboxMenuItem extends AMenuItem {

    public EditLockboxMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new EditLockboxMenu(parent);
    }

    @Override
    public String toString() {
        return "Edit a lockbox";
    }
}