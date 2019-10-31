package UI.menus.GreeterMenu.items;

import UI.AMenuItem;
import UI.controller.ITermController;
import UI.AMenu;
import UI.menus.LoginMenu.LoginMenu;
import company.Entity.Controller.EmployeeController;

public class LoginItem extends AMenuItem {
    protected EmployeeController employeeController;

    public LoginItem(ITermController parent, EmployeeController employeeController)
    {
        super(parent);
        this.employeeController = employeeController;
    }

    @Override
    public AMenu activate()
    {
        return new LoginMenu(parent, this.employeeController);
    }

    @Override
    public String toString() { return "Login"; }
}