package main.java.UI.menus.GreeterMenu;

import main.java.UI.AMenu;
import main.java.UI.controller.ITermController;
import main.java.UI.menus.GreeterMenu.items.LoginItem;
import main.java.UI.global_menu_items.ExitItem;

public class GreeterMenu extends AMenu {
    public GreeterMenu(ITermController parent)
    {
        super(parent);
        title = "Welcome to the Hong Kong Liberation Banking System";
        items.add(new LoginItem(this.parent));
        items.add(new ExitItem(this.parent));
    }
}