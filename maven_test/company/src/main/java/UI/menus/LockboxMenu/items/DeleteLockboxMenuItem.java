package UI.menus.LockboxMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.DeleteLockboxMenu.DeleteLockboxMenu;

public class DeleteLockboxMenuItem extends AMenuItem{
    
    public DeleteLockboxMenuItem(ITermController parent){
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new DeleteLockboxMenu(parent);
    }

    @Override
    public String toString() {
        return "Close a lockbox";
    }
}