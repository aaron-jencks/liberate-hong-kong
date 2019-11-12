package UI.menus.FireMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.FireMenu.FireMenu;
import company.Controller.EmployeeController;

public class FireMenuItem extends AMenuItem {
    protected EmployeeController employeeController;

    public FireMenuItem(ITermController parent, EmployeeController employeeController) {
        super(parent);
        this.employeeController = employeeController;
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new FireMenu(this.parent, employeeController);
    }

    @Override
    public String toString() {
        return "Fire Employee";
    }
    
}