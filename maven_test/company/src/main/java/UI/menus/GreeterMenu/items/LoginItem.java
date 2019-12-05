package UI.menus.GreeterMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.LoginMenu.LoginMenu;

public class LoginItem extends AMenuItem {

    @Override
    public AMenu activate()
    {
        return new LoginMenu();
    }

    @Override
    public String toString() { return "Login"; }
}