package UI.menus.AccountMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.AccountMenu.items.AccountDisplayMenuItem;
import UI.menus.AccountMenu.items.AccrueInterestItem;
import UI.menus.AccountMenu.items.CloseCreditAccountMenuItem;
import UI.menus.AccountMenu.items.CreateAccountMenuItem;
import UI.menus.AccountMenu.items.CreateCreditAccountMenuItem;
import UI.menus.AccountMenu.items.CustomerDisplayMenuItem;
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
        title = "Account Management";
        items.add(new CreateAccountMenuItem());
        items.add(new DeleteAccountMenuItem());
        items.add(new DepositAccountMenuItem());
        items.add(new PayCreditAccountMenuItem());
        items.add(new WithdrawlAccountMenuItem());
        items.add(new CreateCreditAccountMenuItem());
        if(EmployeeController.getInstance().auth().getPosition() == Position.LOAN_OFFICER){
            items.add(new CloseCreditAccountMenuItem());
            items.add(new AccrueInterestItem());
            items.add(new EditInterestRateMenuItem());
        }

        items.add(new AccountDisplayMenuItem());
        items.add(new CustomerDisplayMenuItem());

        items.add(new ExitItem());
    }
    
}