package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.EditInterestRateMenu.EditInterestRateMenu;

public class EditInterestRateMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new EditInterestRateMenu();
    }

    @Override
    public String toString() {
        return "Adjust Interest Rate";
    }

}