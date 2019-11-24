package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EmployeeManagementMenu.EmployeeManagementMenu;

public class EmployeeManagementMenuItem extends AMenuItem {

    public EmployeeManagementMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new EmployeeManagementMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Employee Management";
    }
    
}