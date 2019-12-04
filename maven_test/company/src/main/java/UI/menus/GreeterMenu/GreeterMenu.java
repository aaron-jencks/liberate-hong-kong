package UI.menus.GreeterMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.GreeterMenu.items.ForgotPasswordItem;
import UI.menus.GreeterMenu.items.LoginItem;
import UI.menus.GreeterMenu.items.ToasterItem;

public class GreeterMenu extends AMenu {
    public GreeterMenu()
    {
        super();
        title = "Welcome to the Hong Kong Liberation Banking System";
        items.add(new LoginItem());
        items.add(new ForgotPasswordItem());
        items.add(new ToasterItem());
        items.add(new ExitItem());
    }
}