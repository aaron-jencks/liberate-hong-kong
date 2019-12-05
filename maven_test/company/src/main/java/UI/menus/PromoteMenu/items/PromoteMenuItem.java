package UI.menus.PromoteMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.PromoteMenu.PromoteMenu;

public class PromoteMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new PromoteMenu();
    }

    @Override
    public String toString() {
        return "Promote Employee";
    }
    
}