package UI.menus.MainMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.MainMenu.items.AccountMenuItem;
import UI.menus.MainMenu.items.EmployeeManagementMenuItem;
import company.Controller.EmployeeController;
import company.Entity.Enum.Position;

public class MainMenu extends AMenu {
    public MainMenu() {
        super();
        title = "Hong Kong Liberation Banking System";
        items.add(new AccountMenuItem());

        if(EmployeeController.getInstance().auth().getPosition() == Position.HR){
            items.add(new EmployeeManagementMenuItem());
        }
        
        items.add(new ExitItem());
        // TODO Add Options here
    }
}