package UI.menus.GreeterMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.menus.GreeterMenu.items.*;
import UI.global_menu_items.ExitItem;
import company.Entity.Controller.EmployeeController;

public class GreeterMenu extends AMenu {
    public GreeterMenu(ITermController parent, EmployeeController employeeController)
    {
        super(parent, employeeController);
        title = "Welcome to the Hong Kong Liberation Banking System";
        items.add(new LoginItem(this.parent, this.employeeController));
        items.add(new ForgotPasswordItem(this.parent, this.employeeController));
        items.add(new ExitItem(this.parent));
    }
}