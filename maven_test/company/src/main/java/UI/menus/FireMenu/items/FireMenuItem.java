package UI.menus.FireMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.FireMenu.FireMenu;

public class FireMenuItem extends AMenuItem {

    public FireMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new FireMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Fire Employee";
    }
    
}