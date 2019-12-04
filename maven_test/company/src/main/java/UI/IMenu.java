package UI;

import java.util.Scanner;

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
     * Creates the Display string to be used in the {@code display()} method.
     */
    public String get_display_string();

    /**
     * Causes the menu to be re-displayed on the next prompt.
     */
    public void invalidate();

    /**
     * Prompts the user for input and returns the item they selected from the list of available items.
     */
    public IMenuItem prompt();

    /**
     * Prints a temporary window on top of this window that contains the given message
     * @param message Message to display on the screen
     */
    public void toast(String message);

    /**
     * Prompts the user with a yes/no question by printing a temporary window on top of this window
     * @param prompt The question to ask the user
     * @return Returns true if the user enters 'y' or 'Y', and false otherwise.
     */
    public boolean prompt_yesNo(String prompt);
}