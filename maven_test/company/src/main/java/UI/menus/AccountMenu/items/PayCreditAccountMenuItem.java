package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.PayCreditAccountMenu.PayCreditAccountMenu;

public class PayCreditAccountMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new PayCreditAccountMenu();
    }

    @Override
    public String toString() {
        return "Pay on a debt";
    }

}