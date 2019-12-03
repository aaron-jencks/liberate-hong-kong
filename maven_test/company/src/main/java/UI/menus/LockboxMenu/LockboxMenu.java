package UI.menus.LockboxMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.LockboxMenu.items.CreateLockboxMenuItem;
import UI.menus.LockboxMenu.items.DeleteLockboxMenuItem;
import UI.menus.LockboxMenu.items.EditLockboxMenuItem;

public class LockboxMenu extends AMenu{
    public LockboxMenu(ITermController parent){
        super(parent);
        title = "Lockboxes";
        items.add(new CreateLockboxMenuItem(parent));
        items.add(new EditLockboxMenuItem(parent));
        items.add(new DeleteLockboxMenuItem(parent));
        items.add(new ExitItem(this.parent));
    }
}