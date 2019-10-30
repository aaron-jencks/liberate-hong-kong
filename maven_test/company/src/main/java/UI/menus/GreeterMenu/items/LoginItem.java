package UI.menus.GreeterMenu.items;

import UI.AMenuItem;
import UI.controller.ITermController;
import UI.AMenu;
import UI.menus.LoginMenu.LoginMenu;

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