package main.java.UI.global_menu_items;

import main.java.UI.AMenuItem;
import main.java.UI.controller.ITermController;
import main.java.UI.AMenu;

public class ExitItem extends AMenuItem {

    public ExitItem(ITermController target) { super(target); }

    /**
     * Closes the active window
     * TODO Maybe make close entire application instead.
     */
    @Override
    public AMenu activate()
    {
        if(parent != null)
            parent.close_window();

        return null;
    }

    @Override
    public String toString() { return "Exit"; }
}