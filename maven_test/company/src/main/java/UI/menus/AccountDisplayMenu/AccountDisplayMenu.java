package UI.menus.AccountDisplayMenu;

import UI.IMenuItem;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;
import UI.menus.ADisplayMenu;
import company.Controller.AccountController;
import company.exceptions.BankLockedException;

public class AccountDisplayMenu extends ADisplayMenu {
    public AccountDisplayMenu()
    {
        super(null);
        title = "Current Account Directory";
    }

    @Override
    public IMenuItem prompt()
    {
        try {
            items = AccountController.getInstance().getAll();

            item_count = 0;
            for(Object o : items) item_count++;

            if(item_count > 0)
            {
                page_count = item_count / (TermController.get_instance().get_term_height() - 3);

                if(page_count == 0) page_count = 1;

                page_item_count = item_count / page_count;
            }
        }
        catch(BankLockedException e)
        {
            toast("Cannot access accounts, bank is locked.");
            return new ExitItem();
        }

        return super.prompt();
    }
}