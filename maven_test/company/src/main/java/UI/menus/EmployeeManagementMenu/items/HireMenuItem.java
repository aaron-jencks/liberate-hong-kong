package UI.menus.EmployeeManagementMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.HireMenu.HireMenu;

public class HireMenuItem extends AMenuItem {

    public HireMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new HireMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Hire Employee";
    }

}