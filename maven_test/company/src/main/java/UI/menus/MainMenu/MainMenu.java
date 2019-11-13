package UI.menus.MainMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.FireMenu.items.FireMenuItem;
import UI.menus.HireMenu.items.HireMenuItem;
import UI.menus.MainMenu.items.AccountMenuItem;
import UI.menus.PromoteMenu.items.PromoteMenuItem;
import company.Controller.EmployeeController;

public class MainMenu extends AMenu {
    public MainMenu(ITermController parent, EmployeeController employeeController) {
        super(parent, employeeController);
        title = "Hong Kong Liberation Banking System";
        items.add(new AccountMenuItem(this.parent));
        items.add(new HireMenuItem(this.parent, this.employeeController));
        items.add(new FireMenuItem(this.parent, this.employeeController));
        items.add(new PromoteMenuItem(this.parent, this.employeeController));
        items.add(new ExitItem(this.parent));
        // TODO Add Options here
    }
}