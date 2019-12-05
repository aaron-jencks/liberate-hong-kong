package UI.menus.FireMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.FireMenu.FireMenu;

public class FireMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new FireMenu();
    }

    @Override
    public String toString() {
        return "Fire Employee";
    }
    
}