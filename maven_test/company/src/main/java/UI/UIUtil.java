package UI;

import UI.AlignmentType;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Contains functions necessary to interface with the console.
 */
public class UIUtil {

    /**
     * Clears the console screen
     */
    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
            {
                System.out.print("\033[H\033[2J");  
                System.out.flush();
            }
        } catch (Exception ex) {}
    }

    /**
     * Creates a line of {@code symbol} that is {@code count} in length
     * @param count Number of characters to use
     * @param symbol The symbol to use as the character
     * @return Returns a string containing {@code count} {@code symbol}s.
     */
    public static String create_bar_string(int count, char symbol)
    {
        String pr = new String();

        for(int i = 0; i < count; i++)
            pr += symbol;

        return pr;
    }

    /**
     * Aligns a given {@code str} to fit length {@code target_length} by padding the appropriate side(s) with spaces
     * corresponding to the value of {@code alignment} (left, center, right).
     * @param str The string to pad
     * @param target_length The length to pad to
     * @param alignment The kind of alignment to use (left, center, right).
     * @return Returns a string of length {@code target_length} that contains {@code str}.
     */
    public static String pad_string(String str, int target_length, AlignmentType alignment)
    {
        String result = new String();
        int diff = target_length - str.length();

        if(diff > 0)
        {
            if(alignment == AlignmentType.center)
            {
                int edge_count = diff >> 1;
                String edge = "";
                for(int i = 0; i < edge_count; i++)
                    edge += ' ';

                result += edge;
                result += str;
                result += edge;

                if(diff % 2 > 0) result += ' ';
            }
            else
            {
                String edge = "";
                for(int i = 0; i < diff; i++)
                    edge += ' ';

                if(alignment == AlignmentType.left)
                    result += str + edge;
                else
                    result += edge + str;
            }
        }
        else result += str;

        return result;
    }

    /**
     * Creates a list of Strings using the elements of {@code list} are formatted as follows:
     * If {@code is_numbered} is true, then:
     * 1: item1
     * 2: item2
     * ...
     * if {@code is_numbered} is false, then:
     * * item1
     * * item2
     * ...
     * @param list List to use for generating the strings.
     * @param is_numbered Determines whether to number the entries or to use asterisks.
     * @return Returns an arraylist of strings representing the elements of {@code list}.
     */
    public static ArrayList<String> create_string_list(Iterable list, 
                                                       boolean is_numbered)
    {
        ArrayList<String> result = new ArrayList<String>();
        int row = 1;

        for(Object o : list)
            result.add(((is_numbered) ? (row++) + ": " : "* ") + o.toString());

        return result;
    }

    /**
     * Creates a string containing the elements of {@code list} formatted as follows:
     * If {@code is_numbered} is true, then:
     * 1: item1
     * 2: item2
     * ...
     * if {@code is_numbered} is false, then:
     * * item1
     * * item2
     * ...
     * @param list The list of items to whom's strings represent the items of the list
     * @param is_numbered Determines whether the list should be numbered, or if it should use bullets
     * @return Returns a string representing the list generated from {@code list}.
     */
    public static String create_list_string(Iterable list, boolean is_numbered)
    {
        String result = new String();

        for(String element : create_string_list(list, is_numbered))
            result += element + '\n';

        return result;
    }

    /**
     * Creates a boxed string message, similar to a menu string, but without the list of items.
     * Splits the message by {@code '\n'} and uses that to determine the dimensions of the box
     * @param message The string to wrap in a box of '#'
     * @return Returns the {@code message} string surrounded with a '#' box.
     */
    public static String create_box_string(String message)
    {
        int width = 0;
        for(String l : message.split("\n"))
            if(l.length() > width) width = l.length();
        width += 4; // Room for a space and the '#' symbol

        String topBottom = create_bar_string(width - 2, '\u2500'), result = "";

        result += '\u250C' + topBottom + '\u2510' + '\n';

        for(String l : message.split("\n"))
            result += "\u2502 " + pad_string(l, width - 4, AlignmentType.center) + " \u2502\n";

        result += '\u2514' + topBottom + '\u2518';

        return result;
    }

    /**
     * Creates a string that represents a menu created using the elements from {@code items} and
     * is formatted as follows:
     * 
     * ############
     * #   title  #
     * # 1: item1 #
     * # 2: item2 #
     * # 3: item3 #
     * ############
     * prompt? 
     * 
     * The border is omitted f {@code use_borders} is false.
     * @param title Title string for the menu
     * @param use_borders Determines whether the '#' border is used.
     * @param items Items to represent the selectable items in the menu.
     * @param prompt Prompt to ask the user for their input.
     * @return Returns a string formatted into a menu as shown above.
     */
    public static String create_menu_string(String title, boolean use_borders, 
                                            Iterable items, String prompt)
    {
        String result = new String();
        int total_width = (title.length() > prompt.length()) ? title.length() : prompt.length();
        for(Object o : items)
            if(o.toString().length() > total_width) total_width = o.toString().length();

        if(use_borders)
            result += '\u250C' + create_bar_string(total_width + 2, '\u2500') + "\u2510\n";

        result += "\u2502 " + pad_string(title, total_width, AlignmentType.center) + " \u2502\n";

        for(String element : create_string_list(items, true))
        {
            if(use_borders)
                result += "# ";
            
            result += pad_string(element, total_width, AlignmentType.left);

            if(use_borders)
                result += " #\n";
        }

        if(use_borders)
            result += '\u2514' + create_bar_string(total_width + 2, '\u2500') + "\u2518\n";

        result += prompt;

        return result;
    }

    /**
	 * Prompts the user for input using the prompt and determining the type of input to collect from
	 * out.getClass().
	 * @param sc Scanner to use
	 * @param out Since it's not possible to collect the class of T without passing in a parameter, except via a really ugly workaround, this element must be in-place
	 * @param prompt Prompt to display to the user
	 * @param valid Predicate used to determine if the input from the user is correct
	 * @param <T> The type of input you would like to collect, available are Integer, Double, and String
	 * @return Returns out with the value given by the user.
	 * @throws Exception Thrown if out is not of type Integer, Double, or String
	 */
    @SuppressWarnings("unchecked")
	public static <T> T get_input(Scanner sc, T out, String prompt, Predicate<T> valid) throws Exception
	{
		do {
            System.out.print(prompt);

            while(!sc.hasNextLine());   // Waits until an entry is made

            try {
                if(out.getClass() == Integer.class)
                {
                    // while(!sc.hasNextInt());	// Waits until a valid entry was chosen
                    out = (T)(Object)sc.nextInt();
                }
                else if(out.getClass() == Double.class)
                {
                    // while(!sc.hasNextDouble());	// Waits until a valid entry was chosen
                    out = (T)(Object)sc.nextDouble();
                }
                else if(out.getClass() == String.class)
                {
                    // while(!sc.hasNextLine());
                    out = (T)(Object)sc.nextLine();
                }
                else
                {
                    throw new Exception("Unallowed input type requested");
                }
            }
            catch(InputMismatchException e)
            {
                // Invalid input by definition, continue and try again
                // System.out.println("Moving cursor to column " + (prompt.length() + 1 + out.toString().length() + 1));
                System.out.println("\033[" + 
                                   (prompt.length() + 1 + out.toString().length() + 1) + 
                                   "G\033[A\033[38;5;9mInvalid response, try again...\033[0m");

                // Read in the extra '\n'
                if(out.getClass() != String.class && sc.hasNextLine()) sc.nextLine();

                continue;
            }
            
        }while(!valid.test(out));
        
        // Read in the extra '\n'
        if(out.getClass() != String.class && sc.hasNextLine()) sc.nextLine();
		
		return out;
	}
}