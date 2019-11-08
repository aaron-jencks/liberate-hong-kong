package UI.menus.LoginMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.MainMenu.MainMenu;
import company.Controller.EmployeeController;

public class MainMenuItem extends AMenuItem {
    protected EmployeeController employeeController;

    public MainMenuItem(ITermController parent, EmployeeController employeeController)
    {
        super(parent);
        this.employeeController = employeeController;
    }

    public AMenu activate()
    {
        parent.close_window();  // Close the login window
        return new MainMenu(parent, this.employeeController);
    }
}