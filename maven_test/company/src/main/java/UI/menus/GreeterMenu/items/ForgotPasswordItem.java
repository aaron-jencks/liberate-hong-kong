package UI.menus.GreeterMenu.items;

import UI.AMenuItem;
import UI.controller.ITermController;
import UI.AMenu;
import UI.menus.ForgotPasswordMenu.ForgotPasswordMenu;

public class ForgotPasswordItem extends AMenuItem {

    public ForgotPasswordItem(ITermController parent) { super(parent); }

    @Override
    public AMenu activate()
    {
        return new ForgotPasswordMenu(parent);
    }

    @Override
    public String toString() { return "Forgot Password"; }
}