package UI.menus.HireMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.HireMenu.HireMenu;
import company.Controller.EmployeeController;

public class HireMenuItem extends AMenuItem {
    protected EmployeeController employeeController;

    public HireMenuItem(ITermController parent, EmployeeController employeeController) {
        super(parent);
        this.employeeController = employeeController;
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new HireMenu(this.parent, employeeController);
    }

    @Override
    public String toString() {
        return "Hire Employee";
    }
    
}