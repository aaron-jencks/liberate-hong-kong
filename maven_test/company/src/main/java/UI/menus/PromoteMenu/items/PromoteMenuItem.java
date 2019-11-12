package UI.menus.PromoteMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.PromoteMenu.PromoteMenu;
import company.Controller.EmployeeController;

public class PromoteMenuItem extends AMenuItem {
    protected EmployeeController employeeController;

    public PromoteMenuItem(ITermController parent, EmployeeController employeeController) {
        super(parent);
        this.employeeController = employeeController;
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new PromoteMenu(this.parent, employeeController);
    }

    @Override
    public String toString() {
        return "Promote Employee";
    }
    
}