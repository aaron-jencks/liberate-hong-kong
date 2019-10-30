package UI.global_menu_items;

import UI.AMenuItem;
import UI.controller.ITermController;
import UI.AMenu;

public class ExitItem extends AMenuItem {

    public ExitItem(ITermController target) { super(target); }

    /**
     * Closes the active window
     * TODO Maybe make close entire application instead.
     */
    @Override
    public AMenu Activate()
    {
        if(parent != null)
            parent.close_window();

        return null;
    }

    @Override
    public String toString() { return "Exit"; }
}