package main.java.UI.menus.LoginMenu.items;

import main.java.UI.AMenu;
import main.java.UI.AMenuItem;
import main.java.UI.controller.ITermController;
import main.java.UI.menus.MainMenu.MainMenu;

public class MainMenuItem extends AMenuItem {
    public MainMenuItem(ITermController parent) { super(parent); }

    public AMenu activate()
    {
        parent.close_window();  // Close the login window
        return new MainMenu(parent);
    }
}