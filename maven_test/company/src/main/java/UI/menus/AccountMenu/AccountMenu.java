package UI.menus.AccountMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.AccountMenu.items.AccrueInterestItem;
import UI.menus.AccountMenu.items.CreateAccountMenuItem;
import UI.menus.AccountMenu.items.CreateCreditAccountMenuItem;
import UI.menus.AccountMenu.items.DeleteAccountMenuItem;
import UI.menus.AccountMenu.items.DepositAccountMenuItem;
import UI.menus.AccountMenu.items.PayCreditAccountMenuItem;
import UI.menus.AccountMenu.items.WithdrawlAccountMenuItem;
import company.Controller.EmployeeController;
import company.Entity.Enum.Position;
import UI.menus.AccountMenu.items.EditInterestRateMenuItem;

public class AccountMenu extends AMenu {

    public AccountMenu() {
        super();
        title = "Create an account";
        items.add(new CreateAccountMenuItem());
        items.add(new DeleteAccountMenuItem());
        items.add(new DepositAccountMenuItem());
        items.add(new PayCreditAccountMenuItem());
        items.add(new WithdrawlAccountMenuItem());
        items.add(new CreateCreditAccountMenuItem());
        if(EmployeeController.getInstance().auth().getPosition() == Position.LOAN_OFFICER){
            items.add(new AccrueInterestItem());
            items.add(new EditInterestRateMenuItem());
        }
        items.add(new ExitItem());
    }
    
}