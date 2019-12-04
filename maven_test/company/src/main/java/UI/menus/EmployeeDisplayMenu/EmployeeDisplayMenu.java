package UI.menus.EmployeeDisplayMenu;

import UI.menus.ADisplayMenu;
import company.Controller.EmployeeController;

public class EmployeeDisplayMenu extends ADisplayMenu {
    public EmployeeDisplayMenu()
    {
        super(EmployeeController.getInstance().getAll());
        title = "Current Employee Directory";
    }
}