package UI.menus.MainMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EmployeeManagementMenu.EmployeeManagementMenu;
import company.Controller.EmployeeController;

public class EmployeeManagementMenuItem extends AMenuItem {
    private EmployeeController controller;

    public EmployeeManagementMenuItem(ITermController parent, EmployeeController controller) {
        super(parent);
        this.controller = controller;
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new EmployeeManagementMenu(this.parent, this.controller);
    }

    @Override
    public String toString() {
        return "Employee Management";
    }
    
}