package UI.menus.GreeterMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.menus.GreeterMenu.items.*;
import UI.global_menu_items.ExitItem;

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