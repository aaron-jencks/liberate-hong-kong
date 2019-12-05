package UI.menus.LockboxMenu;

import UI.AMenu;
import UI.global_menu_items.ExitItem;
import UI.menus.LockboxMenu.items.CreateLockboxMenuItem;
import UI.menus.LockboxMenu.items.DeleteLockboxMenuItem;
import UI.menus.LockboxMenu.items.EditLockboxMenuItem;
import UI.menus.LockboxMenu.items.LockboxDisplayMenuItem;

public class LockboxMenu extends AMenu{
    public LockboxMenu(){
        super();
        title = "Lockboxes";
        items.add(new CreateLockboxMenuItem());
        items.add(new EditLockboxMenuItem());
        items.add(new DeleteLockboxMenuItem());
        items.add(new LockboxDisplayMenuItem());
        items.add(new ExitItem());
    }
}