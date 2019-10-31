package UI.menus.MainMenu;

import UI.AMenu;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import UI.menus.MainMenu.items.AccountMenuItem;

public class MainMenu extends AMenu {
    public MainMenu(ITermController parent) { 
        super(parent); 
        title = "Hong Kong Liberation Banking System";
        items.add(new ExitItem(this.parent));
        items.add(new AccountMenuItem(this.parent));
        // TODO Add Options here
    }
}