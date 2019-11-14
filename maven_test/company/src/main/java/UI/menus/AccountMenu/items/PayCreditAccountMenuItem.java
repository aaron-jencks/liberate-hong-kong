package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.PayCreditAccountMenu.PayCreditAccountMenu;

public class PayCreditAccountMenuItem extends AMenuItem {

    public PayCreditAccountMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new PayCreditAccountMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Pay on a debt";
    }

}