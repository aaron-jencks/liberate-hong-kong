package UI.menus.EmployeeManagementMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.EmployeeDisplayMenu.EmployeeDisplayMenu;

public class EmployeeDisplayMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new EmployeeDisplayMenu();
    }

    @Override
    public String toString() {
        return "View Employee Directory";
    }

}