package UI.menus.EmployeeManagementMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.AccountMenu.items.AccrueInterestItem;
import UI.menus.AccountMenu.items.DeleteAccountMenuItem;
import UI.menus.EmployeeManagementMenu.items.HireMenuItem;
import UI.menus.FireMenu.items.FireMenuItem;
import UI.menus.PromoteMenu.items.PromoteMenuItem;
import company.Controller.EmployeeController;

public class EmployeeManagementMenu extends AMenu {

    public EmployeeManagementMenu(ITermController parent, EmployeeController controller) {
        super(parent);
        title = "Employee Management";
        items.add(new HireMenuItem(this.parent, controller));
        items.add(new FireMenuItem(this.parent, controller));
        items.add(new PromoteMenuItem(this.parent, controller));
        items.add(new ExitItem(this.parent));
    }
    
}