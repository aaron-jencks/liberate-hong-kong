package UI.menus.EmployeeManagementMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.EmployeeManagementMenu.items.HireMenuItem;
import UI.menus.FireMenu.items.FireMenuItem;
import UI.menus.PromoteMenu.items.PromoteMenuItem;

public class EmployeeManagementMenu extends AMenu {

    public EmployeeManagementMenu(ITermController parent) {
        super(parent);
        title = "Employee Management";
        items.add(new HireMenuItem(this.parent));
        items.add(new FireMenuItem(this.parent));
        items.add(new PromoteMenuItem(this.parent));
        items.add(new ExitItem(this.parent));
    }
    
}