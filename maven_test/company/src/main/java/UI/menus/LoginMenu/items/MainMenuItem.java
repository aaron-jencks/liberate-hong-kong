package UI.menus.LoginMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.TermController;
import UI.menus.MainMenu.MainMenu;

public class MainMenuItem extends AMenuItem {

    public AMenu activate()
    {
        TermController.get_instance().close_window();  // Close the login window
        return new MainMenu();
    }
}