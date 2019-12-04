package UI.menus.HireMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.HireMenu.HireMenu;

public class HireMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new HireMenu();
    }

    @Override
    public String toString() {
        return "Hire Employee";
    }
    
}