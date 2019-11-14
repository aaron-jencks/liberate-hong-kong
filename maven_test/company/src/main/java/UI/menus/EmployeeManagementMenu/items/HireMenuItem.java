package UI.menus.EmployeeManagementMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.HireMenu.HireMenu;
import company.Controller.EmployeeController;

public class HireMenuItem extends AMenuItem {
    private EmployeeController controller;

    public HireMenuItem(ITermController parent, EmployeeController controller) {
        super(parent);
        this.controller = controller;
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        return new HireMenu(this.parent, this.controller);
    }

    @Override
    public String toString() {
        return "Hire Employee";
    }

}