package UI.global_menu_items;

import UI.AMenuItem;
import UI.controller.TermController;
import UI.AMenu;

public class ExitItem extends AMenuItem {

    /**
     * Closes the active window
     * TODO Maybe make close entire application instead.
     */
    @Override
    public AMenu activate()
    {
        TermController.get_instance().close_window();
        return null;
    }

    @Override
    public String toString() { return "Exit"; }
}