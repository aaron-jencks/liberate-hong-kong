package UI.menus.AccountMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.menus.CustomerDisplayMenu.CustomerDisplayMenu;

public class CustomerDisplayMenuItem extends AMenuItem {

    @Override
    public AMenu activate() {
        return new CustomerDisplayMenu();
    }

    @Override
    public String toString() {
        return "View Customer Directory";
    }

}