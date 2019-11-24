package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.EditInterestRateMenu.EditInterestRateMenu;

public class EditInterestRateMenuItem extends AMenuItem {

    public EditInterestRateMenuItem(ITermController parent) {
        super(parent);
    }

    @Override
    public AMenu activate() {
        return new EditInterestRateMenu(this.parent);
    }

    @Override
    public String toString() {
        return "Adjust Interest Rate";
    }

}