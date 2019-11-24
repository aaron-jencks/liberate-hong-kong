package UI.menus.GreeterMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.GreeterMenu.items.ForgotPasswordItem;
import UI.menus.GreeterMenu.items.LoginItem;

public class GreeterMenu extends AMenu {
    public GreeterMenu(ITermController parent)
    {
        super(parent);
        title = "Welcome to the Hong Kong Liberation Banking System";
        items.add(new LoginItem(this.parent));
        items.add(new ForgotPasswordItem(this.parent));
        items.add(new ExitItem(this.parent));
    }
}