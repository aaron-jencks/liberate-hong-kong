package UI;

import UI.IMenuItem;

/**
 * Represents a menu that can be displayed to the console
 */
public interface IMenu {

    /**
     * Prints the given menu's string out to the console
     */
    public void display();

    /**
     * Prompts the user for input and returns the item they selected from the list of available items.
     */
    public IMenuItem prompt();
}