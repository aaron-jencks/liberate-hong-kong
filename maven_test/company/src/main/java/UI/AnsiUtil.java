package UI;

import UI.AlignmentType;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.controller.TermController;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

public class AnsiUtil {

    /**
     * Moves the cursor to the center of the current row and returns the column that it
     * ended up on
     * @return Returns the column that the cursor is on.
     */
    public static int center_cursor_horiz()
    {
        int x_coord = (TermController.get_instance().get_term_width() >> 1) + 1;
        System.out.print("\033[" + x_coord + "G");
        return x_coord;
    }

    /**
     * Displays the given String, centered in the terminal
     * @param term The terminal to use for sizing
     * @param clear Determines whether to clear the screen or not
     * @param window The string to display for the window.
     */
    public static void display_window(boolean clear, String window)
    {
        if(clear) UIUtil.clrscr();

        // Determine the dimensions of the window to be displayed
        int width = 0, height = 0;
        for(String l : window.split("\n")) {
            if(l.length() > width) width = l.length();
            height++;
        }

        // Determine the starting point of the drawing area
        // REMEMBER the cursor positions are 1-based
        TermController term = TermController.get_instance();
        int x_coord = ((term.get_term_width() - width) >> 1) + 1,
            y_coord = ((term.get_term_height() - height) >> 1) + 1;

        // Convert \n characters into ansi codes that return the cursor to the x_coord
        if(window.contains("\n"))
        {
            String temp_win = "";
            for(String l : window.split("\n")) {
                temp_win += l + "\033[1B\033[" + x_coord + "G";
            }

            window = temp_win;
        }

        // Positions the cursor to the start of the drawing region
        System.out.print("\033[" + y_coord + ";" + x_coord + "H" + window);
    }

    /**
     * Displays a message by moving the cursor to the column desired and then printing the message
     * @param column Column on the terminal to start the message at
     * @param message Message to print
     */
    public static void display_string(int column, String message)
    {
        // Convert \n characters into ansi codes that return the cursor to the x_coord
        if(message.contains("\n"))
        {
            String temp_win = "";
            for(String l : message.split("\n")) {
                temp_win += l + "\033[1B\033[" + column + "G";
            }

            message = temp_win;
        }

        // Reset the column and print the message
        System.out.print("\033[" + column + "G" + message);
    }

    /**
     * Displays a text, that is centered in the middle of the screen horizontally,
     * but not necessarily vertically
     * @param message
     */
    public static void display_centered_string(String message)
    {
        // Determine the dimensions of the window to be displayed
        int width = 0;
        for(String l : message.split("\n"))
            if(l.length() > width) width = l.length();

        // Determine the starting point of the drawing area
        // REMEMBER the cursor positions are 1-based
        int x_coord = ((TermController.get_instance().get_term_width() - width) >> 1) + 1;

        // Convert \n characters into ansi codes that return the cursor to the x_coord
        if(message.contains("\n"))
        {
            String temp_win = "";
            for(String l : message.split("\n")) {
                temp_win += l + "\033[1B\033[" + x_coord + "G";
            }

            message = temp_win;
        }

        System.out.print("\033[" + x_coord + "G" + message);
    }

    /**
     * Does the same as {@code display_string()} except that it moves the cursor down one row
     * before printing
     * @param column Column on the terminal to start the message at
     * @param message Message to print
     */
    public static void append_display_string(int column, String message)
    {
        // Move the cursor down one row
        System.out.print("\033[1B");

        display_string(column, message);
    }
}