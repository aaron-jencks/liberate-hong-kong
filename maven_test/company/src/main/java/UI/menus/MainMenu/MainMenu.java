package UI.menus.MainMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.MainMenu.items.AccountMenuItem;
import UI.menus.MainMenu.items.EmployeeManagementMenuItem;
import UI.menus.MainMenu.items.LockBankMenuItem;
import UI.menus.MainMenu.items.UnlockBankMenuItem;
import company.Controller.EmployeeController;
import company.Entity.Enum.Position;
import company.Entity.BankLock;

public class MainMenu extends AMenu {
    public MainMenu(ITermController parent) {
        super(parent);
        title = "Hong Kong Liberation Banking System";
        items.add(new AccountMenuItem(this.parent));

        if(EmployeeController.getInstance().auth().getPosition() == Position.HR){
            items.add(new EmployeeManagementMenuItem(this.parent));
        }

        if(EmployeeController.getInstance().auth().getPosition() == Position.MANAGER){
            if(BankLock.getInstance().isBankLocked() == true) {
                items.add(new UnlockBankMenuItem(this.parent));
            }
            else {
                items.add(new LockBankMenuItem(this.parent));
            }
        }

        invalidate();
        items.add(new ExitItem(this.parent));
        // TODO Add Options here
    }
}