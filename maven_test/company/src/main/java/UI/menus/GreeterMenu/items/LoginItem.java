package main.java.UI.menus.GreeterMenu.items;

import main.java.UI.AMenuItem;
import main.java.UI.controller.ITermController;
import main.java.UI.AMenu;
import main.java.UI.menus.LoginMenu.LoginMenu;

public class LoginItem extends AMenuItem {

    public LoginItem(ITermController parent) { super(parent); }

    @Override
    public AMenu activate()
    {
        return new LoginMenu(parent);
    }

    @Override
    public String toString() { return "Login"; }
}