package UI.menus.GreeterMenu.items;

import UI.AMenu;
import UI.AMenuItem;
import UI.controller.ITermController;
import UI.menus.ForgotPasswordMenu.ForgotPasswordMenu;

public class ForgotPasswordItem extends AMenuItem {

    @Override
    public AMenu activate()
    {
        return new ForgotPasswordMenu();
    }

    @Override
    public String toString() { return "Forgot Password"; }
}