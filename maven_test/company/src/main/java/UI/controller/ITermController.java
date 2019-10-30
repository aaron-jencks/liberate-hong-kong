package UI.controller;

import UI.IMenu;
import UI.IMenuItem;

public interface ITermController {
    /**
     * Creates a new main window on the terminal, pushing this window onto the stack of already open windows.
     * @param window The window to open.
     */
    public void set_main_window(IMenu window);

    /**
     * Interacts with the current window, prompting the user for an input
     * @return Returns the IMenu item selected by the user
     */
    public IMenuItem interact();

    /**
     * Closes the current window and opens any underlying window, if available.
     */
    public void close_window();

    /**
     * Returns the number of active windows at the given time.
     */
    public int get_window_count();

    /**
     * Returns the width of the terminal (in characters)
     */
    public int get_term_width();

    /**
     * Returns the height of the terminal (in lines)
     */
    public int get_term_height();
}
