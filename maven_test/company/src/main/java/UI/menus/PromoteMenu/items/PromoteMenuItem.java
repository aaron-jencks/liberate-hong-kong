package UI.menus.PromoteMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.PromoteMenu.PromoteMenu;

public class PromoteMenuItem extends AMenuItem {

    public PromoteMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new PromoteMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Promote Employee";
    }
    
}