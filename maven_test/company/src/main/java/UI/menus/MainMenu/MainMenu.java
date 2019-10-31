package main.java.UI.menus.MainMenu;

import main.java.UI.AMenu;
import main.java.UI.controller.ITermController;
import main.java.UI.global_menu_items.ExitItem;

public class MainMenu extends AMenu {
    public MainMenu(ITermController parent) { 
        super(parent); 
        title = "Hong Kong Liberation Banking System";
        items.add(new ExitItem(this.parent));
        // TODO Add Options here
    }
}