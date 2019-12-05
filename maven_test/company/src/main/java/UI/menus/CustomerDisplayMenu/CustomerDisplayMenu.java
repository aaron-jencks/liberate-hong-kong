package UI.menus.CustomerDisplayMenu;

import UI.menus.ADisplayMenu;
import company.Controller.CustomerController;

public class CustomerDisplayMenu extends ADisplayMenu {
    public CustomerDisplayMenu()
    {
        super(CustomerController.getInstance().getAll());
        title = "Current Customer Directory";
    }
}