package UI.menus.EmployeeManagementMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.EmployeeManagementMenu.items.HireMenuItem;
import UI.menus.FireMenu.items.FireMenuItem;
import UI.menus.PromoteMenu.items.PromoteMenuItem;

public class EmployeeManagementMenu extends AMenu {

    public EmployeeManagementMenu() {
        super();
        title = "Employee Management";
        items.add(new HireMenuItem());
        items.add(new FireMenuItem());
        items.add(new PromoteMenuItem());
        items.add(new ExitItem());
    }
    
}