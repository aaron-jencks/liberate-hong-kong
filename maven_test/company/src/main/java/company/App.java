package main.java.company;

import main.java.UI.controller.TermController;
import main.java.UI.global_menu_items.ExitItem;
import main.java.UI.menus.GreeterMenu.GreeterMenu;
import main.java.UI.IMenuItem;
import main.java.UI.AMenu;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );

        TermController term = new TermController();
        GreeterMenu splash = new GreeterMenu(term);
        term.set_main_window(splash);

        while(true)
        {
            IMenuItem response = term.interact();
            AMenu next_window = response.activate();
            if(next_window != null)
                term.set_main_window(next_window);
            else if(response instanceof ExitItem)
            {
                // term.close_window();
                if(term.get_window_count() == 0)
                    break;
            }
        }
    }
}
