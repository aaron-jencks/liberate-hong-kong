package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.CreateLockboxMenu.CreateLockboxMenu;

public class CreateLockboxMenuItem extends AMenuItem{
    public CreateLockboxMenuItem(ITermController parent){
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new CreateLockboxMenu(parent);
    }

    @Override
    public String toString() {
        return "Create a lockbox";
    }
}