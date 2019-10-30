package UI;

import UI.AlignmentType;

import java.lang.Iterable;
import java.util.ArrayList;
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
                Runtime.getRuntime().exec("clear");
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
        ArrayList<String> result = new ArrayList<>();
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
            result += create_bar_string(total_width + 4, '#') + '\n';

        result += "# " + pad_string(title, total_width, AlignmentType.center) + " #\n";

        for(String element : create_string_list(items, true))
        {
            if(use_borders)
                result += "# ";
            
            result += pad_string(element, total_width, AlignmentType.left);

            if(use_borders)
                result += " #\n";
        }

        if(use_borders)
            result += create_bar_string(total_width + 4, '#') + '\n';

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
		System.out.print(prompt);
		do {
			if(out.getClass() == Integer.class)
			{
				while(!sc.hasNextInt());	// Waits until a valid entry was chosen
				out = (T)(Object)sc.nextInt();
			}
			else if(out.getClass() == Double.class)
			{
				while(!sc.hasNextDouble());	// Waits until a valid entry was chosen
				out = (T)(Object)sc.nextDouble();
			}
			else if(out.getClass() == String.class)
			{
				while(!sc.hasNextLine());	// Waits until a valid entry was chosen
				out = (T)(Object)sc.nextLine();
			}
			else
			{
				throw new Exception("Unallowed input type requested");
			}
		}while(!valid.test(out));
		
		return out;
	}
}