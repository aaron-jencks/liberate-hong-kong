package UI.menus.LoginMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.MainMenu.MainMenu;

public class MainMenuItem extends AMenuItem {
    public MainMenuItem(ITermController parent) { super(parent); }

    public AMenu activate()
    {
        return new MainMenu(parent);
    }
}