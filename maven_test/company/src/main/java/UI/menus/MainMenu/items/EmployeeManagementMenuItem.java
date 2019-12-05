package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EmployeeManagementMenu.EmployeeManagementMenu;

public class EmployeeManagementMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new EmployeeManagementMenu();
    }

    @Override
    public String toString() {
        return "Employee Management";
    }
    
}