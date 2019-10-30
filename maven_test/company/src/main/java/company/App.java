package company;

import UI.controller.TermController;
import UI.menus.GreeterMenu.GreeterMenu;
import UI.IMenuItem;
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
        IMenuItem response = term.interact();
    }
}
