package UI.menus.MainMenu;

import UI.AMenu;
import UI.global_menu_items.ExitItem;
import UI.menus.MainMenu.items.AccountMenuItem;
import UI.menus.MainMenu.items.EmployeeManagementMenuItem;
import UI.menus.MainMenu.items.LockboxMenuItem;
import UI.menus.MainMenu.items.LockBankMenuItem;
import UI.menus.MainMenu.items.UnlockBankMenuItem;
import company.Controller.EmployeeController;
import company.Entity.Enum.Position;

public class MainMenu extends AMenu {
    public MainMenu() {
        super();
        title = "Hong Kong Liberation Banking System";
        items.add(new AccountMenuItem());
        items.add(new LockboxMenuItem());
        if(EmployeeController.getInstance().auth().getPosition() == Position.HR){
            items.add(new EmployeeManagementMenuItem());
        }

        if(EmployeeController.getInstance().auth().getPosition() == Position.MANAGER){
            items.add(new UnlockBankMenuItem(this));
            items.add(new LockBankMenuItem(this));
        }

        items.add(new ExitItem());
        // TODO Add Options here
    }
}